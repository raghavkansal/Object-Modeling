package com.crio.jukebox.exceptions;

public class PlayListNotFoundException extends RuntimeException {
    private static String msg = "Playlist Not Found";

    public PlayListNotFoundException() {
        super(msg);
    }

    public PlayListNotFoundException(String msg) {
        super(msg);
    }
}
