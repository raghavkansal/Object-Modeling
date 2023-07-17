package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotPartOfPlayListException;
import com.crio.jukebox.services.IPlayListActionsService;

public class PlaySongCommad implements ICommand {

    IPlayListActionsService playListActionsService;

    public PlaySongCommad(IPlayListActionsService playListActionsService) {
        this.playListActionsService = playListActionsService;
    }

    @Override
    public void execute(List<String> tokens) {
        //PLAY-SONG { USER_ID }  BACK
        //PLAY-SONG  { USER_ID } NEXT
        //PLAY-SONG { USER_ID }  { Song ID }

        String userId = tokens.get(1);
        String commandType = tokens.get(2);
        Song song;
        if(commandType.equals("BACK")) {
            song = playListActionsService.playPreviousSong(userId);
        }
        else if(commandType.equals("NEXT")) {
            song = playListActionsService.playNextSong(userId);
        }
        else { //commandType = { Song ID }
            try {
                song = playListActionsService.playPreferredSong(userId, commandType);
            } catch (SongNotPartOfPlayListException e) {
                System.out.println(e.getMessage());
                return;
            }
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
        // Song - Save Your Tears(Remix)
        // Album -  After Hours 
        // Artists -  TheWeeknd,Ariana Grande
        // Or
        // Song Not Found in the current active playlist.

    }
    
}
