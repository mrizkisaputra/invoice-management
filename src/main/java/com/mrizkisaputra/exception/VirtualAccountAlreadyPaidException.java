package com.mrizkisaputra.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VirtualAccountAlreadyPaidException extends Exception {
    public VirtualAccountAlreadyPaidException(String message) {
        super(message);
    }
}
