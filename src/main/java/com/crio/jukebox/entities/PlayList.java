package com.crio.jukebox.entities;

import java.util.List;

public class PlayList extends BaseEntity {
    private String playListName;
    private String ownerId;
    private List<String> playListSongsId;
    private String currentlyPlayingSong = "";

    public PlayList(String playListId, String userId, String playListName, List<String> songsId) {
        this(userId,playListName,songsId);
        this.id = playListId;
    }

    public PlayList(String userId, String playListName, List<String> songsId) {
        this.ownerId = userId;
        this.playListName = playListName;
        this.playListSongsId = songsId;
    }

    public String getPlayListName() {
        return this.playListName;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public List<String> getPlayListSongsId() {
        return this.playListSongsId;
    }

    public void setCurrentlyPlayingSong(String songId) {
        this.currentlyPlayingSong = songId;
    }

    public String getCurrentlyPlayingSong() {
        return this.currentlyPlayingSong;
    }
    
}
