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

/**
 *
 * @author Kristian Bertelsen
 */
public class MainWindowController implements Initializable {
    
    MyTunes.bll.BLLManager BLL = new MyTunes.bll.BLLManager();
    MyTunes.be.SongModel SongModel = new MyTunes.be.SongModel();
    MyTunes.be.PlaylistModel playlistModel = new MyTunes.be.PlaylistModel();
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
    private TableColumn<?, ?> SongsonPlaylistfelt;
    @FXML
    private Button tilVentes;
    @FXML
    private Button søge;
    @FXML
    private TextField søgefelt;
    @FXML
    private Button SongsClose;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
        {
        PlaylistModel model = new PlaylistModel();
//        model.createPlayList("happy");
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
        songsfelt.setItems(SongModel.getSongs());
        SongModel.loadSongs();
//        playlistfelt.setItems(PlaylistModel.getAllPlaylists());

    }    

    @FXML
    private void newPlaylist(ActionEvent event) throws IOException
        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreatePlaylist.fxml"));
        Parent root2 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreatePlaylistController cpController = fxmlLoader.getController();
        cpController.setPlaylistModel(playlistModel);
        cpController.setMainWindowController(this);
        stage.setTitle("CreatePlaylist");
        stage.setScene(new Scene(root2));
        stage.show();
        }

    @FXML
    private void editPlaylist(ActionEvent event) {
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
    }

    @FXML
    private void playlistSongUp(ActionEvent event) {
    }

    @FXML
    private void playlistSongDown(ActionEvent event) {
    }

    @FXML
    private void deleteSong(ActionEvent event) {
    }

    @FXML
    private void editSong(ActionEvent event) {
    }

    @FXML
    private void newSong(ActionEvent event) throws SQLException, IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateSong.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        MyTunes.gui.CreateSongController apwController=fxmlLoader.getController();
        apwController.setSongModel(SongModel);
        apwController.setMainWindowController(this);
        stage.setTitle("CreateSong");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void deleteFromPlaylist(ActionEvent event) {
    }

    @FXML
    private void prevSong(ActionEvent event) {
    }

    @FXML
    private void playPauseSong(ActionEvent event) {
    }

    @FXML
    private void nextSong(ActionEvent event) {
    }

    @FXML
    private void searchForSong(ActionEvent event) {
        songsfelt.setItems(SongModel.getSongs());
        SongModel.loadSongs(søgefelt.getText());
        
    }

    @FXML
    private void closeMyTunes(ActionEvent event) {
        System.exit(0);
    }
    
}
