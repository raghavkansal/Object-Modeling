package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlayListException;
import com.crio.jukebox.services.IPlayListActionsService;

public class PlayPlayListCommand implements ICommand{
    IPlayListActionsService playlistActionsService;

    public PlayPlayListCommand(IPlayListActionsService playlistActionsService) {
        this.playlistActionsService = playlistActionsService;
    }

    @Override
    public void execute(List<String> tokens) {
        //PLAY-PLAYLIST  { USER_ID } { Playlist-ID }
        Song song;
        try {
            song = playlistActionsService.playPlaylist(tokens.get(1), tokens.get(2));
        } catch (EmptyPlayListException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        System.out.println("Current Song Playing");
        System.out.println("Song - " + song.getSongName());
        System.out.println("Album - " + song.getAlbumName());
        System.out.print("Artists - " + song.getAlbumArtist());//getPlayListSongsId
        List<String> featuredArtists = song.getFeaturedArtists();
        for(String artist : featuredArtists) {
            System.out.print("," + artist);
        } 
        System.out.println();
        // Current Song Playing
        // Song  -  { Song Name  } 
        // Album - { Album Name  }  
        // Artists - { List of Artists }

        // Song - Save Your Tears(Remix)
        // Album -  After Hours 
        // Artists -  TheWeeknd,Ariana Grande
    }
    
}
