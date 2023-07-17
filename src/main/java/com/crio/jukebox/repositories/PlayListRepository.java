package com.crio.jukebox.repositories;

import java.util.Map;
import java.util.HashMap;
import com.crio.jukebox.entities.PlayList;

public class PlayListRepository implements IPlayListRepository {
    private final Map<String,PlayList> playListMap;
    private int autoIncrement;

    public PlayListRepository() {
        playListMap = new HashMap<>();
        autoIncrement = 0;
    }

    @Override
    public PlayList addNewPlayList(PlayList playList) {
        if(playList.getId() == null) {
            autoIncrement++;
            playListMap.put(autoIncrement+"", new PlayList(autoIncrement+"", playList.getOwnerId(), playList.getPlayListName(), playList.getPlayListSongsId()));
            return playListMap.get(autoIncrement+"");
        }
        playListMap.put(playList.getId(), playList);
        return playList;
    }

    @Override
    public boolean checkPlayListExistById(String playListId) {
        if(playListMap.containsKey(playListId)) return true;
        return false;
    }

    @Override
    public void delete(String playListId) {
        playListMap.remove(playListId);
    }

    @Override
    public PlayList getPlayList(String playlistId) {
        return playListMap.get(playlistId);
    }
    
}
