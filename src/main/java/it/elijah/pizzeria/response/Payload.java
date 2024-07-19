package it.elijah.pizzeria.response;

import org.springframework.http.HttpStatus;

public class Payload<T> {

  private T payload;
  private String errorMessage;
  private HttpStatus status;

    public Payload() {
    }

    public Payload(String errorMessage, T payload, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.payload = payload;
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
