package com.crio.jukebox.exceptions;

public class NoActivePlaylistException extends RuntimeException {
    public NoActivePlaylistException() {
        super();
    }

    public NoActivePlaylistException(String msg) {
        super(msg);
    }
}
