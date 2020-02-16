package ru.university.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String[] strings;

    public ErrorInfo(CharSequence url, ErrorType type, String... detail) {
        this.url = url.toString();
        this.type = type;
        this.strings = detail;
    }
}