package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadSongsCommand implements ICommand {

    public ISongService songService;

    public LoadSongsCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        //LOAD-DATA songs.csv
        String csvFileName = tokens.get(1);
        try {
            songService.loadSongs(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(
            "Songs Loaded successfully");
    }
    
}
