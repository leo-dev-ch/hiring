package ar.com.leogaray.hiring.common.exceptions;

public class ApiClientException extends RuntimeException {
    public ApiClientException(String msg) {
        super(msg);
    }
}
