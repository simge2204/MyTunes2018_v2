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
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author simge
 */
public class PlaylistModel
    {
//    PlaylistModel playm = new PlaylistModel();
    List<Playlist> pl;
    private int currentSong;
    private boolean isPlaying;
    private MediaPlayer mediaPlayer;
    private String totaltime;
    SQLServerDataSource ds;

    //file that contains song list
    File songlist = new File("");
//    String path = songlist.getPath();

    //creates a new playlist with a given name
    public void createPlayList(String name)
        {
        String sql = "INSERT INTO Playlist(name) VALUES(?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        Playlist play = new Playlist(name);
        }

    //add a song to a playlist
    public void addSong(Playlist playlist, Song song)
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
        }

    //play a song in a playlist
    public void playSong(Song song)
        {
        Media hit = new Media(new File(songlist.getPath()).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        }

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
    /*public int countTotalTime(List<Song> songs)
        {
        int totalTime = Integer.parseInt(totaltime);
        totalTime = 0;
        for(Song song : songs)
        {
            totaltime += song.getTime();
        }
        return totalTime;
        }
    */
    //delete a song from a playlist
    public void deleteSong(Song song) throws FileNotFoundException
        {
        try(Connection con = ds.getConnection())
        {
            String query = "DELETE from PlaylistModel WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, song.getId());
            preparedStmt.execute();
        } 
        catch (SQLServerException ex) 
        {
            System.out.println(ex);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
//        pl.getSongs().remove(song);
        }

    //delete a playlist
    public void deletePlayList(Playlist pl)
        {
        try (Connection con = ds.getConnection())
        {
            String query = "DELETE from Playlist WHERE name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, pl.getName());
            preparedStmt.execute();
        } catch (SQLServerException ex) 
        {
            System.out.println(ex);
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
//        pl.getSongs().remove(song);

        }
    }
