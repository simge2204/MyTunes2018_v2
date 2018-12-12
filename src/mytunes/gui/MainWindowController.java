/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.MP3Player;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    MyTunes.be.MP3Player MP3Player = new MyTunes.be.MP3Player(); 
    private Song selectedSong;
    private Playlist selectedPlaylist;
    private PlaylistSong selectedPlaySong;
    private MediaPlayer mediaPlayer;
    File songlist = new File("C:\\Users\\maxim\\Desktop\\music");
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
    private void prevSong(ActionEvent event) {
    }

    @FXML
    private void playPauseSong(ActionEvent event) 
    {
        if(!isPlaying)
        {
        selectedPlaySong = PlaylistSongsFelt.getSelectionModel().getSelectedItem();
        MP3Player.updatePlayerList(PlaylistSongsFelt.getItems());
        MP3Player.play();
        }
        if(isPlaying)
        {
        MP3Player.stop();
        }
    }

    @FXML
    private void nextSong(ActionEvent event) 
    {
        if (!isPlaying) {
            currentSong = 0;
        }
        if (isPlaying) {
            currentSong++;
        }
        mediaPlayer.play();
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
    
}
