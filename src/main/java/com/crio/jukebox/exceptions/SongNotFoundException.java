package com.crio.jukebox.exceptions;

public class SongNotFoundException extends RuntimeException {
    
    private static String msg = "Some Requested Songs Not Available. Please try again.";
    public SongNotFoundException() {
        super(msg);
    }

    public SongNotFoundException(String msg) {
        super(msg);
    }
}
