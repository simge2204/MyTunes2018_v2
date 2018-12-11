/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author maxim
 */
public class MP3Player 
{
    private Queue<Path> music = new LinkedList<>();
   
    private MediaPlayer mediaPlayer;
   
    /**
     *
     * @throws java.io.IOException
     */
    public void start() throws IOException
    {
        Files.find(Paths.get(System.getProperty("user.home"), "C:/Users/maxim/Desktop/music/Steampianist - Rust - Feat. Sonata/"), 100,
                (p, a) -> p.toString().toLowerCase().endsWith(".m4a"))
                .forEach(music::add);
        
        play();
                
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
        if(music.peek() == null)
        {
            return;
        }
        mediaPlayer = new MediaPlayer(new Media(music.poll().toUri().toString()));
        mediaPlayer.setOnReady(() ->
        {
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() ->
            {
                mediaPlayer.dispose();
                play();
            });
        });
    }        
}
