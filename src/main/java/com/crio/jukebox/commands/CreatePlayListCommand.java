package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlayListCRUDService;

public class CreatePlayListCommand implements ICommand{

    IPlayListCRUDService playListCRUDService;

    public CreatePlayListCommand(IPlayListCRUDService playListCRUDService) {
        this.playListCRUDService = playListCRUDService;
    }

    @Override
    public void execute(List<String> tokens) {
        //CREATE-PLAYLIST { USER_ID } { PLAYLIST_NAME } { List of Song IDs }
        PlayList playList;
        List<String> songs = new ArrayList<>();
        for(int i = 3; i < tokens.size(); i++) songs.add(tokens.get(i));

        try {
            playList = playListCRUDService.createPlayList(tokens.get(1), tokens.get(2), songs);
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Playlist ID - " + playList.getId());
    }
    
}
