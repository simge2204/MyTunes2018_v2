/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import MyTunes.be.Playlist;
import MyTunes.be.Song;
import MyTunes.bll.BLLManager;
import MyTunes.dal.ConnectionManager;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int currentSong;
    private boolean isPlaying;
    private MediaPlayer mediaPlayer;
    private String totaltime;
    SQLServerDataSource ds;
    
    //file that contains song list
    File songlist = new File("");
//    String path = songlist.getPath();

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
        {
        bllManager.updatePlaylist(playlist);
        }
    }

    //add a song to a playlist
    /*public void addSong(Playlist playlist, Song song)
        {
        String sql = "INSERT INTO PlaylistModel(name, id, id) VALUES (?,?,?)";
        int id = -1;
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playlist.getName());
            ps.setInt(2, song.getId());
            ps.setInt(3, id);
            ps.addBatch();
            ps.executeBatch();
            song.setId(id);
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        //pl.getSongs().add(song);
        }*/
    
    public void addPlaylist(String playName) throws SQLException
        {
        bllManager.addPlaylist(playName);
        }
    
    public void editPlaylist(Playlist playlist) throws SQLException
        {
        bllManager.editPlaylist(playlist);
        }

    //play a song in a playlist
    /*
    public void playSong(Song song)
        {
        Media hit = new Media(new File(songlist.getPath()).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        }
    */
    //play next song in a playlist
    public void playNextSong(Song song)
        {
        if (!isPlaying) {
            currentSong = 0;
        }
        if (isPlaying) {
            currentSong++;
        }
        mediaPlayer.play();
        }

    //move a song in a playlist
    public void moveSong()
        {

        }
    
    //total time of songs in a playlist
    public int countTotalTime(List<Song> songs)
        {
        int totalTime = Integer.parseInt(totaltime);
        totalTime = 0;
        for(Song song : songs)
        {
            totaltime += song.getTime();
        }
        return totalTime;
        }
    }