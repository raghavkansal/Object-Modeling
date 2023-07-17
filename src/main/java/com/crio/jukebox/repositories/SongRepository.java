package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {

    private final Map<String,Song> songsMap;
    private int autoIncrement;

    public SongRepository() {
        songsMap = new HashMap<>();
        autoIncrement = 1;
    }

    @Override
    public Song addSong(List<String> songDetails) {
        //SONG_NAME,GENRE,ALBUM_NAME,ALBUM_ARTIST, “#” separated Featured Artists

        List<String> featureArtists;
        if(songDetails.size() > 4) {
            featureArtists = new ArrayList<>(Arrays.asList(songDetails.get(4).split("#")));
            featureArtists.remove(0);
        } else featureArtists = new ArrayList<>();
        
        Song song = new Song(autoIncrement+"", songDetails.get(0), songDetails.get(1), songDetails.get(2), songDetails.get(3), featureArtists);
        songsMap.put(autoIncrement + "", song);
        autoIncrement++;
        return songsMap.get(autoIncrement - 1 + "");
    }

    @Override
    public boolean checkSongExistsByID(String songId) {
        if(songsMap.containsKey(songId)) return true;
        return false;
    }

    @Override
    public Song getSongById(String songId) {
        return songsMap.get(songId);
    }
    
}
