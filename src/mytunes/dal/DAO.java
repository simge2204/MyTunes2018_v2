/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.dal;

import MyTunes.be.Playlist;
import MyTunes.be.PlaylistSong;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import MyTunes.be.SongModel;
import MyTunes.be.Song;

/**
 *
 * @author kedde
 */
public class DAO {
    
    ConnectionManager cM = new ConnectionManager();
    
    public void addSong(String title, String artist, String genre, String time, String path) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Song(name, artist, genre, length, path) VALUES(?,?,?,?,?)");
            stmt.setString(1, title);
            stmt.setString(2, artist);
            stmt.setString(3, genre);
            stmt.setString(4, time);
            stmt.setString(5, path);
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteSong(int id) throws SQLServerException, SQLException
    {
        try (Connection con = cM.getConnection())
        {
        PreparedStatement stmt;
        stmt = con.prepareStatement("DELETE FROM Song WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public List<Song> getAllSongs() {
        return getAllSongs("");
    
    }
       public List<Song> getAllSongs(String search) {
        
        List<Song> songs = new ArrayList();
      
        try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("SELECT * FROM Song WHERE Name like ? OR Artist like ?");
            stmt.setString(1, "%"+search+"%");
            stmt.setString(2, "%"+search+"%");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Song currentSong = new Song();
                currentSong.setId(rs.getInt("id"));
                currentSong.setTitle(rs.getString("name"));
                currentSong.setArtist(rs.getString("artist"));
                currentSong.setGenre(rs.getString("genre"));
                currentSong.setTime(rs.getString("length"));
                currentSong.setPath(rs.getString("path"));
                songs.add(currentSong);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return songs;
    }
    
    public void editSong(Song song) {
             try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE Song SET name=?, artist=?, genre=?, length=?, path=? WHERE id=?");
            stmt.setInt(6, song.getId());
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getGenre());
            stmt.setString(4, song.getTime());
            stmt.setString(5, song.getPath());
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Playlist> getAllPlaylists() {
        
        List<Playlist> playlists = new ArrayList();
      
        try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("SELECT * FROM Playlist");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Playlist currentPlaylist = new Playlist();
                currentPlaylist.setId(rs.getInt("id"));
                currentPlaylist.setName(rs.getString("name"));
                currentPlaylist.setSongs(rs.getInt("songs"));
                currentPlaylist.setTotalTime(rs.getString("length"));
                
                playlists.add(currentPlaylist);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return playlists;
    }
    
    
    //creates a new playlist with a given name
    public void createPlaylist(String name)
        {
        String sql = "INSERT INTO Playlist(name, songs) VALUES(?, 0)";
        try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.addBatch();
            stmt .executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
    
    public void addPlaylist(String playName) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Playlist(name, songs) VALUES(?, 0)");
            stmt.setString(1, playName);
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //delete a song from a playlist
    public void deletePlaylistSong(Playlist playlist)
        {
        try(Connection con = cM.getConnection())
        {
            String query = "DELETE from PlaylistModel WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, playlist.getId());
            stmt.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    //delete a playlist
    public void deletePlayList(Playlist pl)
        {
        try (Connection con = cM.getConnection())
        {
            String query = "DELETE from Playlist WHERE id = ?;" + "DELETE from Relation WHERE playId = ?;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, pl.getId());
            stmt.setInt(2, pl.getId());
            stmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    //updates playlist
    public void updatePlaylist(Playlist playlist) throws SQLException
        {
        
        try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE Playlist SET name = ? WHERE id = ?");
            stmt.setString(1, playlist.getName());
            stmt.setInt(3, playlist.getId());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    public void editPlaylist(Playlist playlist) throws SQLException
        {
        try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE Playlist SET name=? WHERE id=?");
            stmt.setInt(2, playlist.getId());
            stmt.setString(1, playlist.getName());
            stmt.executeUpdate();
        }
        }
    
    public List<PlaylistSong> getSongsOnPlaylist(Playlist selectedPlaylist) {
        List<PlaylistSong> playSongs = new ArrayList();
      
        try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("SELECT Song.id, Song.name, Song.path, Relation.playId, Relation.playOrder FROM Song INNER JOIN Relation ON Song.id = Relation.songId WHERE Relation.playId = ? ORDER BY Relation.playOrder;");
            stmt.setInt(1, selectedPlaylist.getId());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                PlaylistSong currentPlaylistSong = new PlaylistSong();
                currentPlaylistSong.setSongId(rs.getInt("id"));
                currentPlaylistSong.setPlayId(rs.getInt("playId"));
                currentPlaylistSong.setTitle(rs.getString("name"));
                currentPlaylistSong.setPlayOrder(rs.getInt("playOrder"));
                currentPlaylistSong.setPath(rs.getString("path"));
                playSongs.add(currentPlaylistSong);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return playSongs;
    }
    
    public void moveSongUp(PlaylistSong selectedPlaySong) throws SQLException
        {
        try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE Relation SET playOrder = ? where playOrder = ?;" + "UPDATE Relation SET playOrder = ? WHERE songId = ? AND playOrder = ?;");
            stmt.setInt(1, selectedPlaySong.getPlayOrder());
            stmt.setInt(2, selectedPlaySong.getPlayOrder()-1);
            stmt.setInt(3, selectedPlaySong.getPlayOrder()-1);
            stmt.setInt(4, selectedPlaySong.getSongId());
            stmt.setInt(5, selectedPlaySong.getPlayOrder());
            stmt.executeUpdate();
        }
        }
    
    public void moveSongDown(PlaylistSong selectedPlaySong) throws SQLException
        {
        try (Connection con = cM.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("UPDATE Relation SET playOrder = ? where playOrder = ?;" + "UPDATE Relation SET playOrder = ? where songId = ? AND playOrder = ?;");
            stmt.setInt(1, selectedPlaySong.getPlayOrder());
            stmt.setInt(2, selectedPlaySong.getPlayOrder()+1);
            stmt.setInt(3, selectedPlaySong.getPlayOrder()+1);
            stmt.setInt(4, selectedPlaySong.getSongId());
            stmt.setInt(5, selectedPlaySong.getPlayOrder());
            stmt.executeUpdate();
        }
        }
    
    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Relation(songId, playId, playOrder) VALUES(?,?,?);" + "UPDATE Playlist SET songs = ? WHERE id = ?");
            stmt.setInt(1, selectedSong.getId());
            stmt.setInt(2, selectedPlaylist.getId());
            stmt.setInt(3, selectedPlaylist.getSongs()+1);
            stmt.setInt(4, selectedPlaylist.getSongs()+1);
            stmt.setInt(5, selectedPlaylist.getId());
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletePlaylistSong(PlaylistSong selectedPlaySong) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM Relation WHERE playId = ? AND songId = ? AND playOrder = ?;" + "UPDATE Playlist SET songs = songs-1 WHERE id = ?;");
            stmt.setInt(1, selectedPlaySong.getPlayId());
            stmt.setInt(2, selectedPlaySong.getSongId());
            stmt.setInt(3, selectedPlaySong.getPlayOrder());
            stmt. setInt(4, selectedPlaySong.getPlayId());
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePlayOrder(PlaylistSong selectedPlaySong, int i) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("UPDATE Relation SET playOrder = ? WHERE playId = ? AND playOrder = ?;");
            stmt.setInt(1, i-1);
            stmt.setInt(2, selectedPlaySong.getPlayId());
            stmt.setInt(3, i);
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
