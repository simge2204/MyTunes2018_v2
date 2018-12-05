/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.dal;

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
    
     public void deleteSong() throws SQLServerException, SQLException
    {
        try (Connection con = cM.getConnection())
        {
        PreparedStatement stmt;
        stmt = con.prepareStatement("DELETE FROM Song WEHERE id = ?");
        stmt.setString(1, "id");
        stmt.executeUpdate();
        }
    }    
    
    
    public void addPlaylist(String playName) throws SQLServerException, SQLException
    {
            try (Connection con = cM.getConnection()){
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Playlist(Name) VALUES(?)");
            stmt.setString(1, playName);
            stmt.executeUpdate();
        }
        
        catch (SQLException ex) {
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
            ResultSet rs =
                    stmt.executeQuery();
            
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
}
