package com.github.igorperikov.playground;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HCommand extends HystrixCommand<CommandResult> {
    private final int number;

    public HCommand(HystrixCommandGroupKey group, int number) {
        super(group);
        this.number = number;
    }

    @Override
    protected CommandResult run() throws Exception {
        return null;
    }

    @Override
    protected CommandResult getFallback() {
        throw new RemoteServiceNotAvailableException();
    }
}
