/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.bll;
import MyTunes.be.Playlist;
import MyTunes.be.PlaylistSong;
import java.sql.SQLException;
import MyTunes.dal.DAO;
import MyTunes.be.Song;
import java.util.List;
/**
 *
 * @author kedde
 */
public class BLLManager {
    
    DAO DAO = new DAO();
    
    public void addSong(String title, String artist, String genre, String time, String path) throws SQLException {
        DAO.addSong(title, artist, genre, time, path);
    }
    
    public List<Song> getAllSongs() {
    return DAO.getAllSongs();
    }
    
    public List<Song> getAllSongs(String search){
        return DAO.getAllSongs(search);
    }
    
    public void editSong(Song song) {
        DAO.editSong(song);
    }
    
    public void deleteSong(int id) throws SQLException
    {
        DAO.deleteSong(id);
    }
    
    public List<Playlist> getAllPlaylists()
        {
        return DAO.getAllPlaylists();
        }
    
    public void addPlaylist(String playName) throws SQLException
        {
        DAO.addPlaylist(playName);
        }
    public void deletePlaylistSong(Playlist playlist)
        {
        DAO.deletePlaylistSong(playlist);
        }
    
    public void updatePlaylist(Playlist playlist) throws SQLException
        {
        DAO.updatePlaylist(playlist);
        }
    
    public void createPlaylist(String name)
        {
          DAO.createPlaylist(name);
        }
    
    public void editPlaylist(Playlist playlist) throws SQLException
        {
        DAO.editPlaylist(playlist);
        }

    public void deletePlaylist(Playlist pl)
        {
        DAO.deletePlayList(pl);
        }
    
    public List<PlaylistSong> getSongsOnPlaylist(Playlist selectedPlaylist)
    {
        return DAO.getSongsOnPlaylist(selectedPlaylist);
    }
}