package com.github.dreambrother.jload.exceptions;

/**
 *
 * @author nik
 */
public class TestExecutionException extends RuntimeException {

    public TestExecutionException(Throwable cause) {
        super(cause);
    }

    public TestExecutionException(String message) {
        super(message);
    }
}
