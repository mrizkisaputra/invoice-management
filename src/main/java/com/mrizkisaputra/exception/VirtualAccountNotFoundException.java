package com.mrizkisaputra.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VirtualAccountNotFoundException extends Exception {
    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}
