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

/**
 *
 * @author simge
 */
public class PlaylistModel
    {
    PlaylistModel playm = new PlaylistModel();
    ConnectionManager cM = new ConnectionManager();
    List<Playlist> pl;
    private int currentSong;
    private boolean isPlaying;
    private MediaPlayer mediaPlayer;
    private String totaltime;
    SQLServerDataSource ds;

    //file that contains song list
    File songlist = new File("");
//    String path = songlist.getPath();

    /*public List<Playlist> getAllPlaylists() throws SQLServerException, SQLException
        {
        List<Playlist> pllist = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM Playlist";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) 
            {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                List<Song> allSongs = playm.getSongs(id); //Puts all songs into the playlist
                Playlist pl = new Playlist(allSongs.size(), countTotalTime(allSongs), name, id); //Creates a new playlist object
                pl.setSongs(allSongs); // Sets up the song list
                pllist.add(pl); // Adds the playlist to the playlist array
            }
            return pllist; // Returns the playlists
        } 
        catch (SQLServerException ex) 
        {
            System.out.println(ex);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return null;
        }
*/
    
    //creates a new playlist with a given name
    public Playlist createPlayList(String name)
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
        return play;
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
    
    public void addPlaylist(String playName) throws SQLException
        {
        try (Connection con = cM.getConnection())
        {
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Playlist(Name) VALUES(?)");
            stmt.setString(1, playName);
            stmt.executeUpdate();
        }
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
    
    //updates playlist
    public void updatePlaylist(Playlist playlist) throws SQLException
        {
        int id = playlist.getId();

        try (Connection con = ds.getConnection()) 
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM Playlist;");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    PreparedStatement ps = con.prepareStatement(
                            "UPDATE Playlist SET name = (?), songs= (?) WHERE id = (?)");
                    ps.setString(1, playlist.getName());
                    ps.setInt(2, playlist.songs.size());
                    ps.setString(3, playlist.getTotalTime());
                    ps.setInt(4, id);
                    ps.execute();
                    ps.close();
                }
            }
        }
        }
    }
