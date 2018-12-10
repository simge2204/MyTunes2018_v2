/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.gui;

import MyTunes.be.Playlist;
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

    MyTunes.be.PlaylistModel PlaylistModel;
    MyTunes.gui.MainWindowController mainWindowController;
    private Playlist selectedPlaylist;
    int type;
    
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
        String playName = txtInput.getText();
        Stage stage = (Stage) SavePlaylist.getScene().getWindow();
        switch (type) {
            case 1:
                PlaylistModel.addPlaylist(playName);
                break;
            case 2:
                selectedPlaylist.setName(playName);
                PlaylistModel.editPlaylist(selectedPlaylist);
                break;
            default:
                System.out.println("Something went wrong");
                stage.close();
                break;
        }
        stage.close();
        }

    public void setPlaylistModel(PlaylistModel PlaylistModel)
        {
        this.PlaylistModel = PlaylistModel;  
        
        }
    
    public void setMainWindowController(MainWindowController mainWindowController) 
        {
        this.mainWindowController = mainWindowController;
        }

    public void setEdit() throws SQLException
        {
        //txtInput.setText(selectedPlaylist.getName());
//        PlaylistModel.editPlaylist(selectedPlaylist);
        type = 2;
        }

    public void setNew()
        {
        type = 1;
        }

    public void setPlaylist(Playlist selectedPlaylist)
        {
        this.selectedPlaylist = selectedPlaylist;
        txtInput.setText(selectedPlaylist.getName());
        }
    }
