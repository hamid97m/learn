package com.example.hamid.learn.Api;

/**
 * Created by Hamid on 7/16/2017.
 */

class MyResponseBody {
    private boolean error;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
