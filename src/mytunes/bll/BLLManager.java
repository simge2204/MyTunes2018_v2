/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.bll;
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
}
