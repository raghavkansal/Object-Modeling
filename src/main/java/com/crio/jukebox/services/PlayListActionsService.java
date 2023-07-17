package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.EmptyPlayListException;
import com.crio.jukebox.exceptions.SongNotPartOfPlayListException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlayListActionsService implements IPlayListActionsService {

    IUserRepository userRepository;
    ISongRepository songRepository;
    IPlayListRepository playListRepository;

    public PlayListActionsService(IUserRepository userRepository, ISongRepository songRepository, IPlayListRepository playListRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playListRepository = playListRepository;
    }

    @Override
    public Song playPlaylist(String userId, String playlistId) throws EmptyPlayListException {
        PlayList playlist = playListRepository.getPlayList(playlistId);
        List<String> playlistSongs = playlist.getPlayListSongsId();
        int songsInPlaylist = playlistSongs.size();
        if(songsInPlaylist == 0) throw new EmptyPlayListException("Playlist is empty.");
        String songId = playlistSongs.get(0);
        User user = userRepository.getUserById(userId);
        user.setCurrentlyPlayingPlaylist(playlistId);
        playlist.setCurrentlyPlayingSong(songId);
        return songRepository.getSongById(songId);
    }

    @Override
    public Song playPreviousSong(String userId){
        return play_prev_next_song_helper(userId,"prev");
    }

    @Override
    public Song playNextSong(String userId){
        return play_prev_next_song_helper(userId,"next");
    }

    private Song play_prev_next_song_helper(String userId, String command) {
        User user = userRepository.getUserById(userId);
        String playlistId = user.getCurrentlyPlayingPlaylist();
        PlayList playlist = playListRepository.getPlayList(playlistId);
        String currentPlayingSong = playlist.getCurrentlyPlayingSong();
        List<String> songlist = playlist.getPlayListSongsId();
        int indexOfCurrentSongInsonglist = songlist.indexOf(currentPlayingSong);
        if(command.equals("prev")) {
            int indexOfPreviousSong = indexOfCurrentSongInsonglist - 1;
            if(indexOfPreviousSong == -1) indexOfPreviousSong = songlist.size()-1;
            String prevSongId = songlist.get(indexOfPreviousSong);
            playlist.setCurrentlyPlayingSong(prevSongId);
            return songRepository.getSongById(prevSongId);
        }else if(command.equals("next")){
            int indexOfNextSong = indexOfCurrentSongInsonglist + 1;
            if(indexOfNextSong == songlist.size()) indexOfNextSong = 0;
            //System.out.println("indexOfNextSong:"+indexOfNextSong);
            String nextSongId = songlist.get(indexOfNextSong);
            playlist.setCurrentlyPlayingSong(nextSongId);
            return songRepository.getSongById(nextSongId);
        }
        else{
            return songRepository.getSongById(currentPlayingSong);
        }

    }

    @Override
    public Song playPreferredSong(String userId, String songId)
            throws SongNotPartOfPlayListException {
        
        User user = userRepository.getUserById(userId);
        String currPlayingPlaylist = user.getCurrentlyPlayingPlaylist();
        PlayList playList = playListRepository.getPlayList(currPlayingPlaylist);
        List<String> songs = playList.getPlayListSongsId();
        if(!songs.contains(songId)) throw new SongNotPartOfPlayListException("Given song id is not a part of the active playlist");
        playList.setCurrentlyPlayingSong(songId);
        return songRepository.getSongById(songId);
    }
    
}
