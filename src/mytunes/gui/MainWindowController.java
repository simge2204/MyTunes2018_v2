/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import MyTunes.be.PlaylistModel;
import MyTunes.be.Song;
import MyTunes.bll.BLLManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MyTunes.be.Playlist;
import MyTunes.be.PlaylistSong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.StringBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer; 

/**
 *
 * @author Kristian Bertelsen
 */
public class MainWindowController implements Initializable {
    
    MyTunes.bll.BLLManager BLL = new MyTunes.bll.BLLManager();
    MyTunes.be.SongModel SongModel = new MyTunes.be.SongModel();
    MyTunes.be.PlaylistModel PlaylistModel = new MyTunes.be.PlaylistModel();
    MyTunes.be.PlaylistSongModel PlaylistSongModel = new MyTunes.be.PlaylistSongModel();
    private Song selectedSong;
    private Playlist selectedPlaylist;
    private PlaylistSong selectedPlaySong;
    MediaPlayer mediaPlayer = null;
    String songPath = "http://www.freexmasmp3.com/files/silent-night-disco.mp3";
    private boolean isPlaying = false;
    private int currentSong;

    @FXML
    private Label label;
    @FXML
    private Button PlaylistsNew;
    @FXML
    private Button PlaylistsEdit;
    @FXML
    private Button PlaylistsDelete;
    @FXML
    private Button SongsonPlaylistUp;
    @FXML
    private Button SongsonPlaylistNed;
    @FXML
    private Button SongsDelete;
    @FXML
    private Button SongsEdit;
    @FXML
    private Button SongsNew;
    @FXML
    private Button SongsonPlaylistDelete;
    @FXML
    private Button tilbage;
    @FXML
    private Button spil;
    @FXML
    private Button næste;
    @FXML
    private TableView<Playlist> playlistfelt;
    @FXML
    private TableColumn<Playlist, String> name;
    @FXML
    private TableColumn<Playlist, String> songs;
    @FXML
    private TableColumn<Playlist, String> playTime;
    @FXML
    private TableView<Song> songsfelt;
    @FXML
    private TableColumn<Song, String> titleColumn;
    @FXML
    private TableColumn<Song, String> artistColumn;
    @FXML
    private TableColumn<Song, String> genreColumn;
    @FXML
    private TableColumn<Song, String> timeColumn;
    @FXML
    private TableView<PlaylistSong> PlaylistSongsFelt;
    @FXML
    private TableColumn<PlaylistSong, String> playlistSongs;
    @FXML
    private TableColumn<PlaylistSong, String> PlayOrder;
    @FXML
    private Button tilVentes;
    @FXML
    private Button søge;
    @FXML
    private TextField søgefelt;
    @FXML
    private Button SongsClose;
    @FXML
    private Button Pause;
    @FXML
    private Button Stop;
    @FXML
    private Label nuværderSang;
    @FXML
    private Slider Volume;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException
    {
        selectedSong = songsfelt.getSelectionModel().getSelectedItem();
        selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
        PlaylistSongModel.addSongToPlaylist(selectedSong, selectedPlaylist);
        selectedPlaylist.setSongs(selectedPlaylist.getSongs()+1);
        reload();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory("artist"));
        genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        songs.setCellValueFactory(new PropertyValueFactory("songs"));
        playTime.setCellValueFactory(new PropertyValueFactory("time"));
        PlayOrder.setCellValueFactory(new PropertyValueFactory("playOrder"));
        playlistSongs.setCellValueFactory(new PropertyValueFactory("title"));
        songsfelt.setItems(SongModel.getSongs());
        SongModel.loadSongs();
        playlistfelt.setItems(PlaylistModel.getPlaylists());
        try {
            PlaylistModel.loadPlaylists();
//        playlistfelt.setItems(PlaylistModel.getAllPlaylists());
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            URI songURI = new URI(songPath);
            mediaPlayer = new MediaPlayer(new Media(songURI.toString()));
            bindPlayerToGUI();
        }
        catch (URISyntaxException|UnsupportedOperationException ex)
        {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            
            Alert alert = 
                new Alert(Alert.AlertType.ERROR, 
                            songPath + " not a usable URI" + ex.getMessage()){};
            alert.show();
        }

    }    

    @FXML
    private void newPlaylist(ActionEvent event) throws IOException
        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreatePlaylist.fxml"));
        Parent root2 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreatePlaylistController cpController = fxmlLoader.getController();
        cpController.setPlaylistModel(PlaylistModel);
        cpController.setMainWindowController(this);
        cpController.setNew();
        stage.setTitle("CreatePlaylist");
        stage.setScene(new Scene(root2));
        stage.show();
        }

    @FXML
    private void editPlaylist(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreatePlaylist.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreatePlaylistController cpController=fxmlLoader.getController();
        cpController.setPlaylistModel(PlaylistModel);
        cpController.setMainWindowController(this);
        cpController.setEdit();
        selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
        cpController.setPlaylist(selectedPlaylist);
        stage.setTitle("EditPlaylist");
        stage.setScene(new Scene(root2));
        stage.show();
    }

    @FXML
    private void deletePlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeletePlaylist.fxml"));
        Parent root4 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.DeletePlaylistController DPController = fxmlLoader.getController();
        DPController.setPlaylistModel(PlaylistModel);
        DPController.setMainWindowController(this);
        selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
        DPController.setPlaylist(selectedPlaylist);
        DPController.setPlLabel(selectedPlaylist);
        stage.setTitle("DeletePlaylist");
        stage.setScene(new Scene(root4));
        stage.show();
        
    }

    @FXML
    private void playlistSongUp(ActionEvent event) throws SQLException {
        selectedPlaySong = PlaylistSongsFelt.getSelectionModel().getSelectedItem();
        PlaylistSongModel.moveSongUp(selectedPlaySong);
        reload();
    }

    @FXML
    private void playlistSongDown(ActionEvent event) throws SQLException {
        selectedPlaySong = PlaylistSongsFelt.getSelectionModel().getSelectedItem();
        PlaylistSongModel.moveSongDown(selectedPlaySong);
        reload();
    }

    @FXML
    private void deleteSong(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSong.fxml"));
        Parent root3 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.DeleteSongController DSController = fxmlLoader.getController();
        DSController.setSongModel(SongModel);
        DSController.setMainWindowController(this);
        selectedSong = songsfelt.getSelectionModel().getSelectedItem();
        DSController.setSong(selectedSong);
        DSController.setLabel(selectedSong);
        stage.setTitle("DeleteSong");
        stage.setScene(new Scene(root3));
        stage.show();
    }

    @FXML
    private void editSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreateSongController apwController=fxmlLoader.getController();
        apwController.setSongModel(SongModel);
        apwController.setMainWindowController(this);
        apwController.setEdit();
        selectedSong = songsfelt.getSelectionModel().getSelectedItem();
        apwController.setSong(selectedSong);
        stage.setTitle("CreateSong");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    private void newSong(ActionEvent event) throws SQLException, IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreateSongController apwController=fxmlLoader.getController();
        apwController.setSongModel(SongModel);
        apwController.setMainWindowController(this);
        apwController.setNew();
        stage.setTitle("CreateSong");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void deleteFromPlaylist(ActionEvent event) throws SQLException {
        selectedPlaySong = PlaylistSongsFelt.getSelectionModel().getSelectedItem();
        selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
        PlaylistSongModel.deletePlaylistSong(selectedPlaySong);
        for (int i = selectedPlaySong.getPlayOrder()+1; i < selectedPlaylist.getSongs()+3; i++) {
            PlaylistSongModel.updatePlayOrder(selectedPlaySong, i);
        }
        selectedPlaylist.setSongs(selectedPlaylist.getSongs()-1);
        reload();
    }

    @FXML
    private void prevSong(ActionEvent event) throws URISyntaxException 
    {
        mediaPlayer.getOnEndOfMedia();
        songsfelt.getSelectionModel().getSelectedItem();
        PlaylistSongsFelt.getSelectionModel().getSelectedItem();
        
        if(songsfelt.getSelectionModel().getSelectedItem() == null) 
        {
        PlaylistSongsFelt.getSelectionModel().selectPrevious();
        PlaylistSongSelect(); 
        }
        else if(songsfelt.getSelectionModel().getSelectedItem() != null) 
        {
        songsfelt.getSelectionModel().selectPrevious();
        SongSelect(); 
        }    
            mediaPlayer.stop();
            playSong(toMedia());
    }

    @FXML
    private void playPauseSong(ActionEvent event) throws URISyntaxException 
    {
         if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)
        { mediaPlayer.play(); }
        else
        { 
            mediaPlayer.stop(); 
            playSong(toMedia());
            mediaPlayer.setOnEndOfMedia(new Runnable() 
            {
                @Override
                public void run() 
                {
                    try 
                    {
                        getNextSong();
                    } catch (URISyntaxException ex) 
                    {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }  
//        if(!isPlaying)
//        {
//        selectedPlaySong = PlaylistSongsFelt.getSelectionModel().getSelectedItem();
//        MP3Player.updatePlayerList(PlaylistSongsFelt.getItems());
//        MP3Player.play();
//        }
//        if(isPlaying)
//        {
//        MP3Player.stop();
//        }
    }
    public void playSong(Media songMedia) {
        //This method starts running the current song, and also gives the Label a name to put on the window.//
            mediaPlayer = new MediaPlayer(songMedia);
            songsfelt.getSelectionModel().getSelectedItem();
            if(songsfelt.getSelectionModel().getSelectedItem() == null) {
            nuværderSang.setText(PlaylistSongsFelt.getSelectionModel().getSelectedItem().getTitle());}
            else {
            nuværderSang.setText(songsfelt.getSelectionModel().getSelectedItem().getTitle()); }
            bindPlayerToGUI();
            mediaPlayer.play();
        } 
    
    private void bindPlayerToGUI() 
    {
        label.textProperty().bind(
            new StringBinding()
            {
               
                { 
                    super.bind(mediaPlayer.currentTimeProperty());
                }
                @Override
                protected String computeValue()
                {   
                    String form = String.format("%d min, %d sec", 
                        TimeUnit.MILLISECONDS.toMinutes((long)mediaPlayer.getCurrentTime().toMillis()),
                        TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getCurrentTime().toMillis()) - 
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(
                                (long)mediaPlayer.getCurrentTime().toMillis()
                            )));
                    return form; }});
    }
    @FXML
    private void nextSong(ActionEvent event) throws URISyntaxException 
    {
        getNextSong();
    }

    private void getNextSong() throws URISyntaxException
    {
        mediaPlayer.getOnEndOfMedia();
        songsfelt.getSelectionModel().getSelectedItem();
        PlaylistSongsFelt.getSelectionModel().getSelectedItem();

        if(songsfelt.getSelectionModel().getSelectedItem() == null) 
        {
        PlaylistSongsFelt.getSelectionModel().selectNext();
        PlaylistSongSelect(); 
        }
        else if(songsfelt.getSelectionModel().getSelectedItem() != null) 
        {
        songsfelt.getSelectionModel().selectNext();    
        SongSelect(); 
        }
            mediaPlayer.stop();
            
            playSong(toMedia());
    }
    
    public void SongSelect() 
    {
    PlaylistSongsFelt.getSelectionModel().clearSelection();
    songPath = songsfelt.getSelectionModel().getSelectedItem().getPath();
    }
    
    public void PlaylistSongSelect() 
    {
    songsfelt.getSelectionModel().clearSelection();
    songPath = PlaylistSongsFelt.getSelectionModel().getSelectedItem().getPath();
    }
    @FXML
    private void searchForSong(ActionEvent event) throws SQLException {
        reload();
        
    }

    @FXML
    private void closeMyTunes(ActionEvent event) {
        System.exit(0);
    }
    
    public void reload() throws SQLException {
        selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
        songsfelt.setItems(SongModel.getSongs());
        SongModel.loadSongs(søgefelt.getText());
        if(selectedPlaylist!=null)
        {
            PlaylistSongsFelt.setItems(PlaylistSongModel.getPlaySongs());
            PlaylistSongModel.loadPlaySongs(selectedPlaylist);
        }
        playlistfelt.setItems(PlaylistModel.getPlaylists());
        PlaylistModel.loadPlaylists();
        playlistfelt.getSelectionModel().select(selectedPlaylist);
    }
    
    @FXML public void clickPlaylist(MouseEvent click)
            //is used to quickly reload whenever needed. (Happens on Playlists)
        {
            if(click.getClickCount()==2)
            {
                selectedPlaylist = playlistfelt.getSelectionModel().getSelectedItem();
                PlaylistSongsFelt.setItems(PlaylistSongModel.getPlaySongs());
                PlaylistSongModel.loadPlaySongs(selectedPlaylist);
                
            }
         }

    @FXML
    private void Pause(ActionEvent event)
    {
         if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void Stop(ActionEvent event)
    {
        mediaPlayer.stop();
    }

    @FXML
    private void VolumeUpandDown(MouseEvent event)
    {
        Volume.setValue(mediaPlayer.getVolume() * 100);
        Volume.valueProperty().addListener(new InvalidationListener() 
        {        
        @Override
        public void invalidated(Observable observable) 
        {
        mediaPlayer.setVolume(Volume.getValue() /100);
        }});
    }
    
    private Media toMedia()
    {
        String file = new File(songPath).toURI().toString();
        Media songMedia = new Media(file);
        return songMedia;
    }
    
}
