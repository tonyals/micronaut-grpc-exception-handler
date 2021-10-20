package br.com.tony.constants;

public enum LoggerConstants {
    REQUEST_RECEIVED("Requisição recebida");

    private final String description;

    LoggerConstants(String s) {
        this.description = s;
    }

    public String getDescription() {
        return description;
    }
}
