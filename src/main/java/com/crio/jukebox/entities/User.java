package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {
    private String name;
    private List<String> playListsId;
    private String currentlyPlayingPlaylist;


    public User(String id, String name) {
        this(name);
        this.id = id;
    }

    public User(String name) {
        this.name = name;
        this.playListsId = new ArrayList<>();
        this.currentlyPlayingPlaylist = "";
    }

    public String getName() {
        return this.name;
    }

    public List<String> getPlayListsId() {
        return this.playListsId;
    }

    public void addPlayList(String playListId) {
        this.playListsId.add(playListId);
    }

    public void assignNewPlaylists(List<String> playListsId) {
        this.playListsId = playListsId;
    }

    public void setCurrentlyPlayingPlaylist(String songId) {
        this.currentlyPlayingPlaylist = songId;
    }

    public String getCurrentlyPlayingPlaylist() {
        return this.currentlyPlayingPlaylist;
    }
}
