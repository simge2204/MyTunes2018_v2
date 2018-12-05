/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyTunes.be;

import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MyTunes.bll.BLLManager;
import MyTunes.be.Song;
import java.sql.SQLException;
/**
 *
 * @author simge
 */
public class SongModel
    {
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private BLLManager bllManager = new BLLManager();
    //create a new song
    public void createNewSong(int year, String title)
        {
            //Song song = new Song(2, 2016, "My Love");
            //Song.add(song);
        }
    //edit a song
    //save songlist in database
    //delete a song
    public void deleteSong()
        {
            List<Song> deletsong = bllManager.getAllSongs();
            
            songs.remove(this);
        }
    
    public ObservableList<Song> getSongs()
    {
        return songs;
    }
    
    public void loadSongs()
    {
        List<Song> loadedSongs = bllManager.getAllSongs();
        
        songs.clear();
        songs.addAll(loadedSongs);
    }
    public void addSong(String title, String artist, String genre, String time, String path) throws SQLException {
        bllManager.addSong(title, artist, genre, time, path);
    }
    }

