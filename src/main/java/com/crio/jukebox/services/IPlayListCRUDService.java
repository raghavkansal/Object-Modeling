package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;

import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
public interface IPlayListCRUDService {

    //CREATE-PLAYLIST { USER_ID } { PLAYLIST_NAME } { List of Song IDs }
    //Error Message if Any of the Above Song IDs is not present in the pool.
    public PlayList createPlayList(String userId, String playListName, List<String> Songs) throws SongNotFoundException;

    //DELETE-PLAYLIST { USER_ID } { Playlist-ID }
    //Error Message if the Playlist IDs do not exist.
    public void deletePlayList(String userId, String playListId) throws PlayListNotFoundException;

    // To add Song
    // MODIFY-PLAYLIST ADD-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
    // Note:- Do not add the song again if the Song ID already exists in the playlist.
    //Error Message if Any of the Requested Song IDs is not present in the pool.
    public PlayList addSongstoPlaylist(String userId, String playlistId, List<String> songIdList) throws SongNotFoundException;

    // To delete Song
    // MODIFY-PLAYLIST DELETE-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
    //Error Message if Any of the Requested Song IDs to be deleted are not present in the playlist.
    public PlayList deleteSongsFromPlaylist(String userId, String playlistId, List<String> songIdList) throws SongNotFoundException;

}
