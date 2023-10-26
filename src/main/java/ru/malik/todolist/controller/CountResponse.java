package ru.malik.todolist.controller;

public class CountResponse {
    private long countResponse;

    public CountResponse(long countResponse) {
        this.countResponse = countResponse;
    }

    protected CountResponse() {
    }

    public long getCountResponse() {
        return countResponse;
    }

    public void setCountResponse(long countResponse) {
        this.countResponse = countResponse;
    }
}
