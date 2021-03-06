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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maxim
 */
public class DeleteSongController implements Initializable
{
    MyTunes.be.SongModel SongModel;
    MyTunes.gui.MainWindowController mainWindowController;
    private Song selectedSong;
    
    @FXML
    private Label Tekst;
    @FXML
    private Button ja;
    @FXML
    private Button Nej;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
     void setSongModel(SongModel songModel)
    {
        this.SongModel = songModel;
    }
    
    
    public void setMainWindowController(MainWindowController mainWindowControler) 
    {
        this.mainWindowController = mainWindowControler;
    }

    @FXML
    private void jatak(ActionEvent event) throws SQLException
    {
        SongModel.deleteSong(selectedSong.getId());
        
        Stage stage = (Stage) ja.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Nejtak(ActionEvent event)
    {
        Stage stage = (Stage) Nej.getScene().getWindow();
        stage.close();
    }

   public void setSong(Song selectedSong) 
   {
        this.selectedSong = selectedSong;
   }
   
   public void setLabel(Song song)
   {
       Tekst.setText("Are you sure you want to delete " + song.getTitle() + "?");
   }

    
}
