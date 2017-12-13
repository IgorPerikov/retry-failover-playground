package com.github.igorperikov.playground;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

public class Launcher {
    private static final int SIZE = 50000;

    public static void main(String[] args) {
        Integer integer = build(1, SIZE).blockFirst();
        System.out.println("result: " + integer);
    }

    public static Flux<Integer> build(int start, int count) {
        return Flux.range(start, count)
                .map(SimpleCommand::new)
                .map(SimpleCommand::execute)
                .doOnEach(signal -> {
                    if (signal.hasValue()) {
                        System.out.println(signal.get());
                    } else if (signal.hasError()) {
                        System.out.println(signal.getThrowable().getMessage());
                    }
                })
                .retry(1)
                .onErrorResume(SimpleCommandException.class, buildFlux())
                .timeout(Duration.ofMillis(1500));
    }

    public static Function<SimpleCommandException, Flux<Integer>> buildFlux() {
        return e -> build(e.getNum() + 1, SIZE - 1 - e.getNum());
    }
}
