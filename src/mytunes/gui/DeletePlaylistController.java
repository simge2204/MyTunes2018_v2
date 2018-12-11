/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.Playlist;
import MyTunes.be.PlaylistModel;
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
 * @author simge
 */
public class DeletePlaylistController implements Initializable
    {
    private Playlist selectedPlaylist;
    MyTunes.be.PlaylistModel PlaylistModel;
    MyTunes.gui.MainWindowController mainWindowController;

    @FXML
    private Button Ja;
    @FXML
    private Button Nej;
    @FXML
    private Label Tekst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        // TODO
        }    

    @FXML
    private void pressJa(ActionEvent event) throws SQLException
        {
        PlaylistModel.deletePlayList(selectedPlaylist);
        Stage stage = (Stage) Ja.getScene().getWindow();
        mainWindowController.reload();
        stage.close();
        }

    @FXML
    private void PressNej(ActionEvent event)
        {
        Stage stage = (Stage) Nej.getScene().getWindow();
        stage.close();
        }
    
    public void setMainWindowController(MyTunes.gui.MainWindowController mainWindowControler) 
    {
        this.mainWindowController = mainWindowControler;
    }
    
    public void setPlaylist(Playlist selectedPlaylist)
        {
        this.selectedPlaylist = selectedPlaylist;
        }

    public void setPlLabel(Playlist playlist)
        {
        Tekst.setText("Are you sure you want to delete " + playlist.getName() + "?");
        }

    public void setPlaylistModel(PlaylistModel PlaylistModel)
        {
        this.PlaylistModel = PlaylistModel;
        }
    
    }
