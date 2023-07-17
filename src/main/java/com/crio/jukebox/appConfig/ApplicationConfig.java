package com.crio.jukebox.appConfig;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.ModifyPlayListCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommad;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.LoadSongsCommand;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.IPlayListActionsService;
import com.crio.jukebox.services.IPlayListCRUDService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlayListActionsService;
import com.crio.jukebox.services.PlayListCRUDService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {
    private final IUserRepository userRepository = new UserRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IPlayListRepository playListRepository = new PlayListRepository();

    private final IUserService userService = new UserService(userRepository);
    private final ISongService songService = new SongService(songRepository);
    private final IPlayListCRUDService playListCRUDService = new PlayListCRUDService(userRepository,songRepository,playListRepository);
    private final IPlayListActionsService playlistActionsService = new PlayListActionsService(userRepository,songRepository,playListRepository);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadSongsCommand loadSongsCommand = new LoadSongsCommand(songService);
    private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(playListCRUDService);
    private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(playListCRUDService);
    private final ModifyPlayListCommand modifyPlayListCommand = new ModifyPlayListCommand(playListCRUDService);
    private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(playlistActionsService);
    private final PlaySongCommad playSongCommad = new PlaySongCommad(playlistActionsService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadSongsCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playPlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongCommad);
        // commandInvoker.register("CREATE_QUESTION",createQuestionCommand);
        // commandInvoker.register("LIST_QUESTION",listQuestionCommand);
        // commandInvoker.register("CREATE_CONTEST",createContestCommand);
        // commandInvoker.register("LIST_CONTEST",listContestCommand);
        // commandInvoker.register("ATTEND_CONTEST",attendContestCommand);
        // commandInvoker.register("RUN_CONTEST",runContestCommand);
        // commandInvoker.register("LEADERBOARD",leaderBoardCommand);
        // commandInvoker.register("WITHDRAW_CONTEST",withdrawContestCommand);
        return commandInvoker;
    }

    
}
