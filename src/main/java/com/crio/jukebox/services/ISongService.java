package com.crio.jukebox.services;

import java.io.IOException;

public interface ISongService {

    // CSV File Input format:
	// SONG_NAME,GENRE,ALBUM_NAME,ALBUM_ARTIST, “#” separated Featured Artists
    public void loadSongs(String csvFileName) throws IOException;
}
