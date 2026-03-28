# 중고거래 플랫폼 (Market Project)

지역 기반 중고거래 플랫폼의 백엔드 API 서버로, JWT 인증, 실시간 채팅, 소셜 로그인 등을 구현하고 AWS에 배포한 개인 프로젝트입니다.

> 프로젝트 이름은 추후 변경 예정

---

## 기술 스택

| 구분 | 기술 |
|------|------|
| **Backend** | Java 17, Spring Boot 3.5.7, Spring Security 6.x, Spring Data JPA, JWT (jjwt) |
| **Database** | MySQL, Redis, H2 (개발용) |
| **Infra** | AWS EC2, RDS, S3, Docker, Docker Compose, GitHub Actions (CI/CD) |
| **Frontend** | Thymeleaf, JavaScript, HTML/CSS, Bootstrap 5 |
| **Communication** | WebSocket, STOMP, SockJS |
| **Test** | JUnit 5, Mockito |
| **Docs** | Swagger |

---

## ERD

<!-- ERDCloud 스크린샷 이미지 추가 -->
![ERD](이미지 경로 추가 예정)

---

## 주요 기능

**인증/보안**
- JWT 기반 로그인 / 회원가입 (Access Token + Refresh Token HttpOnly 쿠키)
- OAuth2 소셜 로그인 (Google, Naver)
- 이메일 인증 기반 아이디 찾기 / 비밀번호 재설정
- 회원 탈퇴 (Soft Delete + Redis JWT 블랙리스트)

**게시글**
- 게시글 CRUD + 이미지 업로드 (S3)
- 동적 검색 (키워드, 가격범위, 지역, 판매상태) + 페이지네이션
- 판매상태 관리 (판매중 / 예약중 / 판매완료)
- 찜하기

**채팅**
- WebSocket + STOMP 기반 실시간 1:1 채팅
- Redis 캐싱으로 최근 메시지 조회 성능 개선
- StompHandler를 통한 WebSocket 연결 시 JWT 인증

**댓글**
- 자기참조 구조를 활용한 댓글 / 대댓글
- Soft Delete 적용

**관리자**
- 유저 관리 (강제 탈퇴, 활동 정지)
- 게시글 관리 (강제 삭제)
- 통계 (가입자 수, 게시글 수, 정지된 유저 수)

---

## 시스템 아키텍처

<!-- 아키텍처 다이어그램 추가 예정 -->

---

## 트러블슈팅

### 1. 게시글 목록 조회 시 N+1 문제 해결

**문제**
게시글 목록을 조회할 때 게시글마다 작성자 정보를 가져오기 위한 추가 쿼리가 발생하여 N+1 문제가 발생했다.

**시도**
Fetch Join으로 해결하려 했으나, Pageable과 함께 사용하면 JPA가 전체 데이터를 메모리에 로드한 후 애플리케이션 레벨에서 페이징하는 문제를 발견했다. 데이터가 많아질수록 OutOfMemoryError 위험이 있었다.

**해결**
@EntityGraph를 사용하여 연관 엔티티를 한 번에 조회하면서도 DB 레벨 페이징을 유지했다. Fetch Join 대비 페이지네이션과의 호환성이 좋고, 기존 Repository에 어노테이션 추가만으로 적용 가능했다.

### 2. JWT 환경에서 회원 탈퇴 시 토큰 즉시 무효화

**문제**
회원 탈퇴를 처리해도 발급된 Access Token이 만료 시간까지 유효한 상태로 남아있었다. JWT는 Stateless 방식이라 서버에서 특정 토큰을 강제로 무효화할 수 없었다.

**시도**
Access Token 만료 시간을 짧게 설정하는 방법은 정상 사용자의 경험을 해치고, DB에 블랙리스트 테이블을 만드는 방법은 모든 API 요청마다 DB 조회가 추가되어 성능 부담이 컸다.

**해결**
Redis에 블랙리스트를 저장하는 방식을 선택했다. 메모리 기반이라 조회 속도가 빠르고, TTL을 토큰 남은 만료 시간으로 설정하면 만료 후 자동 삭제되어 별도 정리 작업이 필요 없었다. 프로젝트에서 이미 Redis를 사용하고 있어 추가 인프라 없이 적용할 수 있었다.

### 3. 채팅 메시지 조회 시 DB 부하 개선

**문제**
채팅방에 입장할 때마다 MySQL에서 메시지를 조회해야 했다. 채팅 특성상 방 입장이 빈번하게 일어나는데, 매번 DB 쿼리를 실행하면 사용자가 많아질수록 DB에 부하가 집중되는 구조였다.

**시도**
로컬 캐시(Ehcache 등)를 고려했으나, 서버가 여러 대일 때 각 서버마다 캐시가 따로 존재하여 데이터 정합성 문제가 발생할 수 있었다.

**해결**
Redis를 캐시 계층으로 도입했다. 채팅방별로 최근 50개 메시지를 Redis List에 저장하고, 채팅방 입장 시 Redis를 먼저 조회하여 MySQL 부하를 줄였다. 전체 메시지는 MySQL에 영구 보관하여 데이터 유실 없이 캐시 계층과 영속 계층을 분리했다.

### 4. CI/CD 파이프라인에서 JAR 전송 실패

**문제**
GitHub Actions에서 빌드한 JAR 파일(약 79MB)을 SCP로 EC2에 직접 전송하려 했으나, 네트워크 속도 문제로 전송이 타임아웃되거나 실패하는 상황이 반복 발생했다.

**시도**
S3를 경유하여 JAR를 업로드/다운로드하는 방식을 검토했으나, EC2에 Java를 직접 설치해야 하고 서버 환경 차이로 인한 문제가 여전히 남았다.

**해결**
Docker 이미지 방식으로 전환했다. GitHub Actions에서 Docker 이미지를 빌드하여 Docker Hub에 push하고, EC2에서 pull하여 실행하는 구조로 변경했다. Docker Compose로 Spring Boot와 Redis 컨테이너를 함께 관리하여 배포 구성이 단순해졌고, 컨테이너 기반이라 환경 차이로 인한 문제도 방지할 수 있었다.

---

## 시퀀스 다이어그램

### JWT 인증 흐름

<!-- 시퀀스 다이어그램 추가 예정 -->

---

## 프로젝트 구조

```
src/main/java/com/example/marketproject/
├── config/          # Security, WebSocket, Swagger 설정
├── controller/      # REST API 컨트롤러
├── service/         # 비즈니스 로직
├── repository/      # JPA Repository
├── entity/          # JPA 엔티티
├── dto/             # 요청/응답 DTO
├── security/        # JWT, OAuth2 관련
└── util/            # 유틸리티 (DataInitializer 등)
```
