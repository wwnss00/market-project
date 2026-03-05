package com.example.marketproject.exception;

public class WishlistNotFoundException extends RuntimeException{
    public WishlistNotFoundException(String message) {
        super(message);
    }
}
