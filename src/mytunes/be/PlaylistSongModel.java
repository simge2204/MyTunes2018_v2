/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import MyTunes.bll.BLLManager;
import java.sql.SQLException;
/**
 *
 * @author kedde
 */
public class PlaylistSongModel {
    private ObservableList<PlaylistSong> playSongs = FXCollections.observableArrayList();
    private BLLManager bllManager = new BLLManager();
    
    
    public ObservableList<PlaylistSong> getPlaySongs()
    {
        return playSongs;
    }
    
    public void loadPlaySongs(Playlist selectedPlaylist)
    {
        List<PlaylistSong> loadedSongs = bllManager.getSongsOnPlaylist(selectedPlaylist);
        
        playSongs.clear();
        playSongs.addAll(loadedSongs);
    }
    
    public void moveSongUp(PlaylistSong selectedPlaySong) throws SQLException
    {
        bllManager.moveSongUp(selectedPlaySong); 
    }
    
    public void moveSongDown(PlaylistSong selectedPlaySong) throws SQLException
    {
        bllManager.moveSongDown(selectedPlaySong); 
    }
    
    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLException 
    {
        bllManager.addSongToPlaylist(selectedSong, selectedPlaylist);
    }
    
    public void deletePlaylistSong(PlaylistSong selectedPlaySong) throws SQLException 
    {
        bllManager.deletePlaylistSong(selectedPlaySong);
    }
    
    public void updatePlayOrder(PlaylistSong selectedPlaySong, int i) throws SQLException
    {
        bllManager.updatePlayOrder(selectedPlaySong, i);
    }
}
