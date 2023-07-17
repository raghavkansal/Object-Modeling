package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.EmptyPlayListException;
import com.crio.jukebox.exceptions.NoActivePlaylistException;
import com.crio.jukebox.exceptions.SongNotPartOfPlayListException;


public interface IPlayListActionsService {
    //PLAY-PLAYLIST  { USER_ID } { Playlist-ID }
    // plays first song of the playlist.
    //Error Message if Playlist is empty
    public Song playPlaylist(String userId, String playlistId) throws EmptyPlayListException;

    //for below three methods playlist should be active.

    // Switch to play a previous song in the active playlist.
    // PLAY-SONG { USER_ID }  BACK
    public Song playPreviousSong(String userId);

    // Switch to play the next song in the active playlist.
    // PLAY-SONG  { USER_ID } NEXT
    public Song playNextSong(String userId);
    // Switch to the preferred song in the playlist.
    // PLAY-SONG { USER_ID }  { Song ID }
    //Error Message if above Song ID is not part of a current active playlist.
    public Song playPreferredSong(String userId, String songId) throws SongNotPartOfPlayListException;
}
