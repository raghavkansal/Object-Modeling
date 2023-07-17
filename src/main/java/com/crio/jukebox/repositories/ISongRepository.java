package com.crio.jukebox.repositories;

import java.util.List;

import com.crio.jukebox.entities.Song;

public interface ISongRepository {
    // SONG_NAME,GENRE,ALBUM_NAME,ALBUM_ARTIST, “#” separated Featured Artists
    public Song addSong(List<String> songDetails);

    public boolean checkSongExistsByID(String songId);

    public Song getSongById(String songId); 
}
