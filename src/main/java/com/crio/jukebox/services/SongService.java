package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService {

    ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void loadSongs(String csvFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
        String line = reader.readLine();
        while(line != null) {
            ArrayList<String> songDetails = new ArrayList<>(Arrays.asList(line.split(",")));
            songDetails.remove(0);
            line = reader.readLine();
            songRepository.addSong(songDetails);
        }
        reader.close();
    }

    
    
}
