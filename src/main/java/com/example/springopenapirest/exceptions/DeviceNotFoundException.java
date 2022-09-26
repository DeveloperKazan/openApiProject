package com.example.springopenapirest.exceptions;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException() {
        super(String.format("No book found"));
    }

}
