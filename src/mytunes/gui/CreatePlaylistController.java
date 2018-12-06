/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.PlaylistModel;
import MyTunes.dal.DAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class CreatePlaylistController implements Initializable
    {

    MyTunes.be.PlaylistModel playlistModel;
    MyTunes.gui.MainWindowController mainWindowController;
    @FXML
    private Label PlaylistName;
    @FXML
    private TextField txtInput;
    @FXML
    private Button CancelPlaylist;
    @FXML
    private Button SavePlaylist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        // TODO
        }    

    @FXML
    private void cancelNewPlaylist(ActionEvent event)
        {
        Stage stage = (Stage)CancelPlaylist.getScene().getWindow();
        stage.close();
        }

    @FXML
    private void saveNewPlaylist(ActionEvent event) throws SQLException
        {
        DAO DAO = new DAO();
        String playName = txtInput.getText();
        
        playlistModel.addPlaylist(playName);
        }
    
    public void setPlaylistModel(PlaylistModel playlistModel)
        {
        this.playlistModel = playlistModel;
        }
    public void setMainWindowController(MainWindowController mainWindowController) 
        {
        this.mainWindowController = mainWindowController;
        }
    }
