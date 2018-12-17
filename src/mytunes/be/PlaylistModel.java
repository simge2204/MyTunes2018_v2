/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

import java.util.List;
import MyTunes.bll.BLLManager;
import MyTunes.dal.ConnectionManager;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simge
 */
public class PlaylistModel
    {
    private BLLManager bllManager = new BLLManager();
    ConnectionManager cM = new ConnectionManager();
    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    List<Playlist> pl;
    private String totaltime;
    SQLServerDataSource ds;

    public List<Playlist> getAllPlaylists() throws SQLServerException, SQLException
        {
        return bllManager.getAllPlaylists();
        }
    
    public ObservableList<Playlist> getPlaylists()
    {
        return playlists;
    }
    
    public void loadPlaylists() throws SQLException
    {
        List<Playlist> loadedPlaylists = bllManager.getAllPlaylists();

        playlists.clear();
        playlists.addAll(loadedPlaylists);
    }
    
    //creates a new playlist with a given name
    public void createPlayList(String name)
        {
        bllManager.createPlaylist(name);
        }
//delete a song from a playlist
    public void deletePlaylistSong(Playlist playlist) throws SQLException
        {
        List<Playlist> deletePlaylistSong = bllManager.getAllPlaylists();
        
        playlists.remove(this);
//        pl.getSongs().remove(song);
        }

    //delete a playlist
    public void deletePlayList(Playlist pl)
        {
        bllManager.deletePlaylist(pl);
//        pl.getSongs().remove(song);
        }
    
    //updates playlist
    public void updatePlaylist(Playlist playlist) throws SQLException
        {
        bllManager.updatePlaylist(playlist);
        }
    
    public void addPlaylist(String playName) throws SQLException
        {
        bllManager.addPlaylist(playName);
        }
    
    public void editPlaylist(Playlist playlist) throws SQLException
        {
        bllManager.editPlaylist(playlist);
        }
    
    //total time of songs in a playlist
    public int countTotalTime(List<Playlist> songs)
        {
        int totalTime = Integer.parseInt(totaltime);
        totalTime = 0;
        for(Playlist song : songs)
        {
            totaltime += song.getTotalTime();
        }
        return totalTime;
        }
    }