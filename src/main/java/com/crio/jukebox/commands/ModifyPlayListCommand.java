package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlayListCRUDService;

public class ModifyPlayListCommand implements ICommand {

    IPlayListCRUDService playListCRUDService;

    public ModifyPlayListCommand(IPlayListCRUDService playListCRUDService) {
        this.playListCRUDService = playListCRUDService;
    }

    @Override
    public void execute(List<String> tokens) {
        //MODIFY-PLAYLIST ADD-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
        PlayList playlist;
        List<String> songs = new ArrayList<>();
        for(int i = 4; i < tokens.size(); i++) songs.add(tokens.get(i));
        try {
            if(tokens.get(1).equals("ADD-SONG")) {
                playlist = playListCRUDService.addSongstoPlaylist(tokens.get(2), tokens.get(3), songs);
            }
            else {
                playlist = playListCRUDService.deleteSongsFromPlaylist(tokens.get(2), tokens.get(3), songs);
            }
            
        } catch (PlayListNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        // Playlist ID - { Playlist ID  }
        // Playlist Name - { Playlist Name }
        // Song IDs - { List of final Song IDs}

        
        System.out.println("Playlist ID - " + playlist.getId());
        System.out.println("Playlist Name - " + playlist.getPlayListName());
        System.out.print("Song IDs -");//getPlayListSongsId
        List<String> playlistSongs = playlist.getPlayListSongsId();
        for(String songId : playlistSongs) {
            System.out.print(" " + songId);
        } 
        System.out.println();
    }
    
}
