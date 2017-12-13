package com.github.igorperikov.playground;

import java.util.Random;

public class SimpleCommand {
    public static final Random RANDOM = new Random();

    private final int num;

    public SimpleCommand(int num) {
        this.num = num;
    }

    public int execute() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (RANDOM.nextDouble() < 0.97) {
            throw new SimpleCommandException("Command " + num + " failed!", num);
        } else {
            return num;
        }
    }
}
