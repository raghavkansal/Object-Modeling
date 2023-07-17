package com.crio.jukebox.exceptions;

public class SongNotPartOfPlayListException extends RuntimeException {
    public SongNotPartOfPlayListException() {
        super();
    }

    public SongNotPartOfPlayListException(String msg) {
        super(msg);
    }
}
