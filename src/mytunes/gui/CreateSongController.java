/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.SongModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kedde
 */
public class CreateSongController implements Initializable {
MyTunes.be.SongModel songModel;
MyTunes.gui.MainWindowController mainWindowController;
    @FXML
    private TextField TimeField;
    @FXML
    private TextField GenreField;
    @FXML
    private TextField TitleField;
    @FXML
    private TextField ArtistField;
    @FXML
    private TextField PathField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void PressAddSong(ActionEvent event) throws SQLException {
        String title=TitleField.getText();
        String artist=ArtistField.getText();
        String genre=GenreField.getText();
        String time=TimeField.getText();
        String path=PathField.getText();
        
        songModel.addSong(title, artist, genre, time, path);
        
    }
    
    
    public void setSongModel(SongModel SongModel) {
        this.songModel = SongModel;
    }
    public void setMainWindowController(MainWindowController mainWindowControler) {
        this.mainWindowController =mainWindowControler;
    }
}
