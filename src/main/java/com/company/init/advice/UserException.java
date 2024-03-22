package com.company.init.advice;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class UserException {
    private String messsage;
    private Throwable throwable;
    private HttpStatus httpStatus;

    public UserException() {
    }

    public UserException(String messsage, Throwable throwable, HttpStatus httpStatus) {
        this.messsage = messsage;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserException that = (UserException) o;
        return Objects.equals(messsage, that.messsage) && Objects.equals(throwable, that.throwable) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messsage, throwable, httpStatus);
    }

    @Override
    public String toString() {
        return "UserException{" +
                "messsage='" + messsage + '\'' +
                ", throwable=" + throwable +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
