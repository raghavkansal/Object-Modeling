package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.PlayList;

public interface IPlayListRepository {
    public PlayList addNewPlayList(PlayList playList);

    public boolean checkPlayListExistById(String playListId);

    public void delete(String playListId);

    public PlayList getPlayList(String playlistId);
    
}
