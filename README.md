# 중고거래 플랫폼

지역 기반 중고거래 플랫폼의 백엔드 API 서버로, JWT 인증, 실시간 채팅, 소셜 로그인 등을 구현하고 AWS에 배포한 개인 프로젝트입니다.

---

## 기술 스택

**Backend**

![Java](https://img.shields.io/badge/Java_17-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5.7-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security_6.x-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

**Database**

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![H2](https://img.shields.io/badge/H2-1021FF?style=for-the-badge&logo=h2&logoColor=white)

**Infra**

![AWS EC2](https://img.shields.io/badge/AWS_EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)
![AWS RDS](https://img.shields.io/badge/AWS_RDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white)
![AWS S3](https://img.shields.io/badge/AWS_S3-569A31?style=for-the-badge&logo=amazons3&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)

**Frontend**

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)

**Communication**

![WebSocket](https://img.shields.io/badge/WebSocket-010101?style=for-the-badge&logo=websocket&logoColor=white)
![STOMP](https://img.shields.io/badge/STOMP-010101?style=for-the-badge)

**Test & Docs**

![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---

## 시스템 아키텍처

<img width="601" height="391" alt="시스템 아키텍처 drawio" src="https://github.com/user-attachments/assets/f8d89cf6-6f23-4731-8368-c75411a9ecad" />

---

## ERD

<img width="2080" height="1362" alt="ERD다이어그램" src="https://github.com/user-attachments/assets/b1bd3982-eb37-4914-9423-d77f88f37eb9" />

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

<img width="1343" height="918" alt="로그인 페이지" src="https://github.com/user-attachments/assets/7ae20135-54d5-4e94-9bf8-a2b50d1fabe0" />
<img width="1495" height="924" alt="게시글 목록" src="https://github.com/user-attachments/assets/2b9a15e3-cb7a-4c0d-bd7c-5237578d46c2" />
<img width="1395" height="888" alt="게시글 상세" src="https://github.com/user-attachments/assets/298dec5a-4925-4d8d-a635-63ec41601522" />
<img width="1770" height="918" alt="채팅 화면" src="https://github.com/user-attachments/assets/235d988b-5b38-4d27-b27d-3510dba178f8" />
<img width="1568" height="768" alt="관리자 페이지" src="https://github.com/user-attachments/assets/114682d1-7b6a-4ef4-b2a7-1109d9e91960" />
<img width="1656" height="924" alt="스웨거" src="https://github.com/user-attachments/assets/9f9a0403-90e6-4b8c-bd63-2b22f59435a7" />

---

## 트러블슈팅

# 중고거래 플랫폼 트러블슈팅

## 1. N+1을 해결했더니 페이징이 메모리에서 처리되던 문제 ([PR #62](https://github.com/wwnss00/market-project/pull/62))

**문제**
게시글 목록 10개를 조회할 때 쿼리가 22번 실행됨(목록 1 + 작성자 10 + 이미지 10 + 전체 개수 1). `@EntityGraph(attributePaths = {"user", "images"})`로 2회까지 줄였지만, 로그에 `HHH90003004: firstResult/maxResults specified with collection fetch; applying in memory` 경고가 발생함.

**원인**
게시글 1개에 이미지가 여러 장(1:N)이라, 이미지까지 조인하면 결과 행이 게시글 수 × 이미지 수만큼 늘어남. DB가 "게시글 10개"를 행 단위로 자를 수 없어서, Hibernate가 조건에 맞는 게시글 전체를 가져온 뒤 메모리에서 10개를 잘라냄. 게시글이 늘수록 메모리 사용량과 응답 시간이 함께 늘어나는 구조.

**대안 비교**
- 컬렉션 fetch 유지: 쿼리 수는 가장 적지만 메모리 페이징이 그대로 남음
- DTO 직접 조회: 효율은 좋지만 조회 코드와 응답 변환 로직을 크게 바꿔야 함
- 작성자(N:1)만 `@EntityGraph`, 이미지(1:N)는 batch fetch: 쿼리가 1회 늘지만 DB 페이징 유지

**선택**
세 번째 방법. N:1 조인은 행 수가 늘지 않아 페이징에 영향이 없고, 이미지는 `default_batch_fetch_size: 100`으로 "이 게시글들의 이미지를 한 번에" IN 쿼리 1회로 가져옴. 게시글 수가 늘어도 쿼리 수가 고정됨.

**결과**
쿼리 22회 → 3회(목록 1 + 이미지 1 + 개수 1), `HHH90003004` 경고 제거, DB 레벨 페이징 적용 (게시글 10개 기준)

---

## 2. 탈퇴한 회원이 토큰을 재발급받을 수 있던 문제 ([PR #59](https://github.com/wwnss00/market-project/pull/59))

**문제**
탈퇴 시 Access Token을 Redis 블랙리스트에 등록해 차단했지만, 브라우저에 남은 Refresh Token 쿠키로 `/api/auth/refresh`를 호출하면 새 Access Token이 발급됨. 새 토큰은 블랙리스트에 없으므로 탈퇴한 회원이 다시 API를 사용할 수 있었음.

**원인**
- 탈퇴 시 Redis에 저장된 Refresh Token을 삭제하지 않음
- 재발급 API가 회원의 탈퇴 여부를 확인하지 않음 (`/api/auth/**`는 JWT 필터도 거치지 않음)

**해결**
- 탈퇴 시 Redis의 Refresh Token 삭제
- 재발급 시 DB에서 탈퇴 여부를 다시 확인 (Redis 삭제가 누락·실패해도 막는 두 번째 방어선, 로그인과 같은 기준)
- 유효하지 않은 Refresh Token 응답을 400에서 401로 통일 (테스트 작성 중 발견)

**검증**
회원가입 → 로그인 → 탈퇴 → 남은 Refresh Token으로 재발급 시도 시 401을 확인하는 통합 테스트 추가

**한계**
블랙리스트는 탈퇴 요청에 사용한 토큰만 등록하므로, 다른 기기의 Access Token은 만료 시간(30분)까지 유효함

---

## 3. 이미지 저장 실패 시 게시글만 저장되던 문제 ([PR #61](https://github.com/wwnss00/market-project/pull/61))

**문제**
게시글 작성 중 이미지 저장이 실패하면 사용자는 오류(500)를 받지만, 실제로는 이미지가 빠진 게시글이 저장됨. 사용자가 다시 등록하면 중복 게시글이 생김.

**원인**
`@Transactional`은 기본적으로 RuntimeException과 Error에서만 롤백함. 파일 저장 실패인 `IOException`은 checked 예외라 롤백되지 않고 커밋됨.

**대안 비교**
- `rollbackFor = Exception.class` 지정
- `IOException`을 RuntimeException으로 감싸서 던지기
- 파일 업로드를 트랜잭션 시작 전에 수행

**선택**
`rollbackFor` 지정. 기존 메서드 구조를 유지하면서 "어떤 예외든 전부 취소"라는 의도가 코드에 드러남.

**검증**
파일 저장이 항상 실패하도록 Mock을 설정하고, 게시글 수가 변하지 않는지 확인하는 통합 테스트 추가

**한계**
여러 장 중 앞서 S3에 올라간 파일은 DB 롤백과 별개로 남음. 실패 시 업로드한 파일을 삭제하는 보상 처리가 필요함

---

## 4. 인증·인가 검증 누락 ([PR #60](https://github.com/wwnss00/market-project/pull/60), [PR #63](https://github.com/wwnss00/market-project/pull/63))

**문제**
- 비로그인 상태의 게시글 작성·삭제 요청이 401이 아니라 500으로 응답됨
- 로그인한 사용자가 주소의 채팅방 번호만 바꾸면 참여하지 않은 채팅방의 메시지를 조회할 수 있었음

**원인**
- 게시글 조회를 열어두려고 `/api/posts` 경로를 HTTP 메서드 구분 없이 `permitAll`로 설정해, 쓰기 요청도 인증 없이 컨트롤러까지 도달함
- 메시지 전송에는 참여자 검증이 있었지만 조회 API에는 빠져 있었음

**해결**
- 게시글·댓글은 `GET`만 비로그인 허용, `/api/` 경로의 미인증 요청은 로그인 페이지 이동 대신 401 응답
- 채팅 메시지 조회 시 구매자·판매자인지 확인하고, 아니면 403 응답

**검증**
비로그인 삭제 401, 비로그인 목록 조회 200, 비참여자 채팅 조회 차단, 참여자 조회 허용 테스트 추가

**정리**
"로그인했는가(인증, 401)"와 "이 자원에 권한이 있는가(인가, 403)"를 구분해 점검함

---

## 5. CI/CD 파이프라인에서 JAR 전송 실패

(문제·대안 비교·선택은 기존 내용 유지)

**결과**
GitHub Actions에서 Docker 이미지 빌드 → Docker Hub push → EC2에서 pull → Docker Compose 실행 파이프라인을 구축해 main merge 시 자동 배포되도록 운영함. 현재는 비용 문제로 EC2 인스턴스를 중지하고 배포 트리거를 비활성화한 상태.

---

## 회고 - 아쉬운 점 (교체안)

기능을 빠르게 완성한 뒤 코드를 다시 검토하면서 토큰 재발급 우회, 메모리 페이징, 트랜잭션 롤백 누락, 권한 검증 누락을 발견함. 이후로는 문제를 재현하는 테스트를 먼저 작성해 실패를 확인한 뒤 수정하는 방식으로 작업함. 채팅 캐시가 비었을 때와 찼을 때 반환 개수가 다른 문제, 조회수 동시 증가 시 갱신 손실, S3 고아 파일 정리는 다음 개선 과제로 남겨둠.
---

## 로컬 실행 가이드

### 사전 준비
- Java 17
- Docker (Redis 컨테이너 실행용)

### 실행 방법

1. 프로젝트 클론
```bash
git clone https://github.com/wwnss00/market-project.git
cd market-project
```

2. 환경 설정

   민감 정보는 보안을 위해 저장소에서 제외되어 있습니다. `src/main/resources/application-secret.yml` 파일을 생성하고 아래 값을 설정합니다.
```yaml
jwt:
  secret: "(JWT 서명 키 - 임의의 긴 문자열)"
  access-token-expire: 1800000
  refresh-token-expire: 1209600000

GOOGLE_CLIENT_ID: "(Google OAuth2 Client ID)"
GOOGLE_CLIENT_SECRET: "(Google OAuth2 Client Secret)"
NAVER_CLIENT_ID: "(Naver OAuth2 Client ID)"
NAVER_CLIENT_SECRET: "(Naver OAuth2 Client Secret)"
MAIL_USERNAME: "(Gmail 주소)"
MAIL_PASSWORD: "(Gmail 앱 비밀번호)"
```

3. Redis 컨테이너 실행
```bash
docker run -d -p 6379:6379 redis:latest
```

4. 애플리케이션 실행 (local 프로필)
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

5. 접속
   - API 서버: http://localhost:8080
   - Swagger 문서: http://localhost:8080/swagger-ui.html
   - H2 콘솔: http://localhost:8080/h2-console

> **참고**
> - 로컬 환경에서는 H2 인메모리 DB를 사용하며, 실행 시 샘플 데이터가 자동으로 생성됩니다.
> - OAuth2(Google/Naver)와 이메일 인증 기능을 사용하려면 본인의 키를 발급받아 설정해야 합니다.
> - 이미지 업로드(S3)는 외부 서비스 의존으로 로컬에서는 동작하지 않습니다.
> - AWS 배포 환경은 비용 사유로 중단된 상태이며, 전체 기능 시연은 상단 스크린샷을 참고해주세요.

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
