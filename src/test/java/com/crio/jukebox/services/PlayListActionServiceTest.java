package com.crio.jukebox.services;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Tests for PlayListActionService")
@ExtendWith(MockitoExtension.class)
public class PlayListActionServiceTest {

    @Mock
    IUserRepository userRepositoryMock;
    @Mock
    ISongRepository songRepositoryMock;
    @Mock
    IPlayListRepository playListRepositoryMock;

    @InjectMocks
    PlayListActionsService playListActionService;

    @Test
    @DisplayName("Test Play PlayList functionality")
    public void test_playPlaylist() {
        //initialization
        User user = new User("1","Raghav");
        user.addPlayList("1");
        PlayList playlist = new PlayList("1", "1", "My_Playlist", new ArrayList<>(Arrays.asList(new String[]{"1","2","3","4"})));
        Song song = new Song("1", "name1", "genre1", "albumName1", "albumArtist1", new ArrayList<>(Arrays.asList(new String[]{"artist1","artist2"})));

        when(playListRepositoryMock.getPlayList("1")).thenReturn(playlist);
        when(userRepositoryMock.getUserById("1")).thenReturn(user);
        when(songRepositoryMock.getSongById("1")).thenReturn(song);
        //act
        Song actualSong = playListActionService.playPlaylist("1", "1");

        //assertion
        Assertions.assertEquals("1", user.getCurrentlyPlayingPlaylist());
        Assertions.assertEquals("1", playlist.getCurrentlyPlayingSong());
        Assertions.assertEquals(song, actualSong);
    }

    @Test
    @DisplayName("Test Play PlayList functionality")
    public void test_playPrefferedSongInPlaylist() {
        //initialization
        User user = new User("1","Raghav");
        user.addPlayList("1");
        user.setCurrentlyPlayingPlaylist("1");
        PlayList playlist = new PlayList("1", "1", "My_Playlist", new ArrayList<>(Arrays.asList(new String[]{"1","2","3","4"})));
        playlist.setCurrentlyPlayingSong("4");
        Song song = new Song("1", "name1", "genre1", "albumName1", "albumArtist1", new ArrayList<>(Arrays.asList(new String[]{"artist1","artist2"})));

        when(playListRepositoryMock.getPlayList("1")).thenReturn(playlist);
        when(userRepositoryMock.getUserById("1")).thenReturn(user);
        when(songRepositoryMock.getSongById("1")).thenReturn(song);
        //act
        Song actualSong = playListActionService.playPreferredSong("1", "1");

        //assertion
        Assertions.assertEquals("1", user.getCurrentlyPlayingPlaylist());
        Assertions.assertEquals("1", playlist.getCurrentlyPlayingSong());
        Assertions.assertEquals(song, actualSong);
    }

    @Test
    @DisplayName("Test Play PlayList functionality")
    public void test_playNextSongInPlaylist() {
        //initialization
        User user = new User("1","Raghav");
        user.addPlayList("1");
        user.setCurrentlyPlayingPlaylist("1");
        PlayList playlist = new PlayList("1", "1", "My_Playlist", new ArrayList<>(Arrays.asList(new String[]{"1","2","3","4"})));
        playlist.setCurrentlyPlayingSong("4");
        Song song = new Song("1", "name1", "genre1", "albumName1", "albumArtist1", new ArrayList<>(Arrays.asList(new String[]{"artist1","artist2"})));

        when(playListRepositoryMock.getPlayList("1")).thenReturn(playlist);
        when(userRepositoryMock.getUserById("1")).thenReturn(user);
        when(songRepositoryMock.getSongById("1")).thenReturn(song);
        //act
        Song actualSong = playListActionService.playNextSong("1");

        //assertion
        Assertions.assertEquals("1", user.getCurrentlyPlayingPlaylist());
        Assertions.assertEquals("1", playlist.getCurrentlyPlayingSong());
        Assertions.assertEquals(song, actualSong);
    }

    @Test
    @DisplayName("Test Play PlayList functionality")
    public void test_playPreviousSongInPlaylist() {
        //initialization
        User user = new User("1","Raghav");
        user.addPlayList("1");
        user.setCurrentlyPlayingPlaylist("1");
        PlayList playlist = new PlayList("1", "1", "My_Playlist", new ArrayList<>(Arrays.asList(new String[]{"1","2","3","4"})));
        playlist.setCurrentlyPlayingSong("1");
        Song song = new Song("4", "name1", "genre1", "albumName1", "albumArtist1", new ArrayList<>(Arrays.asList(new String[]{"artist1","artist2"})));

        when(playListRepositoryMock.getPlayList("1")).thenReturn(playlist);
        when(userRepositoryMock.getUserById("1")).thenReturn(user);
        when(songRepositoryMock.getSongById("4")).thenReturn(song);
        //act
        Song actualSong = playListActionService.playPreviousSong("1");

        //assertion
        Assertions.assertEquals("1", user.getCurrentlyPlayingPlaylist());
        Assertions.assertEquals("4", playlist.getCurrentlyPlayingSong());
        Assertions.assertEquals(song, actualSong);
    }
}
