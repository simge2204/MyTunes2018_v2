/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.Song;
import MyTunes.be.SongModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author kedde
 */
public class CreateSongController implements Initializable {
    MyTunes.be.SongModel songModel;
    MyTunes.gui.MainWindowController mainWindowController;
    private Song selectedSong;
    int type;
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
    @FXML
    private Button AddSongBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void PressAddSong(ActionEvent event) throws SQLException {
        String title=TitleField.getText();
        String artist=ArtistField.getText();
        String genre=GenreField.getText();
        String time=TimeField.getText();
        String path=PathField.getText();
        Stage stage = (Stage) AddSongBtn.getScene().getWindow();
        switch (type) {
            case 1:
                songModel.addSong(title, artist, genre, time, path);
                break;
            case 2:
                selectedSong.setTitle(title);
                selectedSong.setArtist(artist);
                selectedSong.setGenre(genre);
                selectedSong.setTime(time);
                selectedSong.setPath(path);
                songModel.editSong(selectedSong);
                break;
            default:
                System.out.println("Something went wrong");
                stage.close();
                break;
        }
        
        mainWindowController.reload();
        stage.close();
    }
    
    
    public void setSongModel(SongModel SongModel) {
        this.songModel = SongModel;
    }
    public void setMainWindowController(MainWindowController mainWindowControler) {
        this.mainWindowController = mainWindowControler;
    }
    public void setEdit() {
        type = 2;
    }
    public void setNew() {
        type = 1;
    }
    public void setSong(Song selectedSong) {
        this.selectedSong = selectedSong;
        TitleField.setText(selectedSong.getTitle());
        ArtistField.setText(selectedSong.getArtist());
        GenreField.setText(selectedSong.getGenre());
        TimeField.setText(selectedSong.getTime());
        PathField.setText(selectedSong.getPath());
    }
    
    @FXML
    private void clickbtnChoose(ActionEvent event) {
        JFileChooser c = new JFileChooser();
            c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int rVal = c.showSaveDialog(c);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                PathField.setText("");
                PathField.setText(c.getSelectedFile().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                PathField.setText("");
            }
    }
}
