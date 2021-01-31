package de.wko.mdb.rcl;

import de.wko.mdb.types.ErrorResponse;

public class MdbRestException extends Exception {
    private ErrorResponse response;

    public MdbRestException() {

    }

    public MdbRestException(ErrorResponse r) {
        response = r;
    }

    public ErrorResponse getResponse() {
        return response;
    }

    public void setResponse(ErrorResponse response) {
        this.response = response;
    }

    public String getMessage() {
        return response.getMessage();
    }
    public String getError() {
        return response.getError();
    }
}
