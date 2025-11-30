package com.assesment.sarvika.error;

public class InvalidRequest extends RuntimeException{

    public InvalidRequest(String message){
        super(message);
    }
}
