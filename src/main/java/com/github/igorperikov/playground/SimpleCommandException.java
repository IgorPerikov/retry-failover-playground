package com.github.igorperikov.playground;

public class SimpleCommandException extends RuntimeException {
    private final int num;

    public SimpleCommandException(String errorMessage, int num) {
        super(errorMessage);
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
