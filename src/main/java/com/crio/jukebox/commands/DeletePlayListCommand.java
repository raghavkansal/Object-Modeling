package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IPlayListCRUDService;

public class DeletePlayListCommand implements ICommand {

    IPlayListCRUDService playListCRUDService;

    public DeletePlayListCommand(IPlayListCRUDService playListCRUDService) {
        this.playListCRUDService = playListCRUDService;
    }

    @Override
    public void execute(List<String> tokens) {
        //DELETE-PLAYLIST { USER_ID } { Playlist-ID }
        try {
            playListCRUDService.deletePlayList(tokens.get(1), tokens.get(2));
        } catch (PlayListNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Delete Successful");
    }
    
}
