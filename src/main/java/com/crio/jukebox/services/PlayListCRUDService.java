package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;

public class PlayListCRUDService implements IPlayListCRUDService {

    IUserRepository userRepository;
    ISongRepository songRepository;
    IPlayListRepository playListRepository;

    public PlayListCRUDService(IUserRepository userRepository, ISongRepository songRepository, IPlayListRepository playListRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playListRepository = playListRepository;
    }

    @Override
    public PlayList createPlayList(String userId, String playListName, List<String> songsId)
            throws SongNotFoundException {
        
        for(String songId : songsId) {
            if (!songRepository.checkSongExistsByID(songId)) throw new SongNotFoundException();
        }

        PlayList playList = new PlayList(userId, playListName, songsId);
        playList = playListRepository.addNewPlayList(playList);

        User user = userRepository.getUserById(playList.getOwnerId());
        user.addPlayList(playList.getId());
        
        return playList;
    }

    @Override
    public void deletePlayList(String userId, String playListId) throws PlayListNotFoundException {
        if(!playListRepository.checkPlayListExistById(playListId)) throw new PlayListNotFoundException();
        playListRepository.delete(playListId);
        User user = userRepository.getUserById(userId);
        List<String> playLists = user.getPlayListsId();
        List<String> updatedList = new ArrayList<>();
        for(String id : playLists) {
            if(id.equals(playListId)) continue;
            updatedList.add(id);
        }
        user.assignNewPlaylists(updatedList);
    }

    @Override
    public PlayList addSongstoPlaylist(String userId, String playlistId, List<String> songIdList)
            throws SongNotFoundException, PlayListNotFoundException {

        if(!playListRepository.checkPlayListExistById(playlistId)) 
        for(String songId : songIdList) {
            if(!songRepository.checkSongExistsByID(songId)) throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
        }

        User user = userRepository.getUserById(userId);
        List<String> currPlayLists = user.getPlayListsId();
        if(!currPlayLists.contains(playlistId)) throw new PlayListNotFoundException();

        PlayList playList = playListRepository.getPlayList(playlistId);
        List<String> currPlayListSongs = playList.getPlayListSongsId();
        for(String songId : songIdList) {
            if(currPlayListSongs.contains(songId)) continue;
            currPlayListSongs.add(songId);
        }

        return playListRepository.getPlayList(playlistId);
    }

    @Override
    public PlayList deleteSongsFromPlaylist(String userId, String playlistId, List<String> songIdList) throws SongNotFoundException, PlayListNotFoundException {
        if(!playListRepository.checkPlayListExistById(playlistId)) throw new PlayListNotFoundException();
        for(String songId : songIdList) {
            if(!songRepository.checkSongExistsByID(songId)) throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
        }

        User user = userRepository.getUserById(userId);
        List<String> currPlayLists = user.getPlayListsId();
        if(!currPlayLists.contains(playlistId)) throw new PlayListNotFoundException();

        PlayList playList = playListRepository.getPlayList(playlistId);
        List<String> currPlayListSongs = playList.getPlayListSongsId();
        for(String songId : songIdList) {
            if(!currPlayListSongs.contains(songId)) throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
        }
        for(String songId : songIdList) {
            if(currPlayListSongs.contains(songId)) currPlayListSongs.remove(songId);
        }

        return playListRepository.getPlayList(playlistId);
    }
    
}
