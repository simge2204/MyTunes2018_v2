/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author maxim
 */
public class MP3Player 
{
    int countSong = 0;
    private List<PlaylistSong> music = new LinkedList<>();
   
    private MediaPlayer mediaPlayer;
    
    public void updatePlayerList(List<PlaylistSong> songlist)
    {
        music = songlist;
        countSong = 0;
    }
                  
    public void stop()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }       
    public void play()
    {
        if(music.isEmpty() || countSong > music.size())
        {
            return;
        }
        mediaPlayer = new MediaPlayer(new Media(music.get(countSong).getPath()));
        mediaPlayer.setOnReady(() ->
        {
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() ->
            {
                countSong ++;
                mediaPlayer.dispose();
                play();
            });
        });
    }        
}
