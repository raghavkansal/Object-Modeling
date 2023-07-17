package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {
    private String songName;
    private String genre;
    private String albumName;
    private String albumArtist;
    private List<String> featuredArtists;

    public Song(String id, String songName, String genre, String albumName, String albumArtist, List<String> featuredArtists) {
        this.id = id;
        this.songName = songName;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featuredArtists = featuredArtists;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public List<String> getFeaturedArtists() {
        return this.featuredArtists;
    }
}
