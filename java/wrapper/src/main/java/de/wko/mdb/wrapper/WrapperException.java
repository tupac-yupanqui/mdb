package de.wko.mdb.wrapper;

import de.wko.mdb.types.ErrorResponse;

public class WrapperException extends Exception {
    public WrapperException() {

    }


    public WrapperException(String msg) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(msg);
    }



}
