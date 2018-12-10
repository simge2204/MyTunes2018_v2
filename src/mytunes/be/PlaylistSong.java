/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

/**
 *
 * @author kedde
 */
public class PlaylistSong {
    
    private int songId;
    private int playId;
    private String title;
    private String path;
    private int playOrder;
    
    
    public int getSongId()
    {
        return songId;
    }

    public int getPlayId() 
    {
        return playId;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public int getPlayOrder()
    {
        return playOrder;
    }
    
    public void setSongId(int songId)
    {
        this.songId=songId;
    }
    
    public void setPlayId(int playId)
    {
        this.playId=playId;
    }
    
    public void setTitle(String title)
    {
        this.title=title;
    }
    
    public void setPath(String path)
    {
        this.path=path;
    }
    
    public void setPlayOrder(int playOrder)
    {
        this.playOrder=playOrder;
    }
    
    @Override
    public String toString()
    {
        return playOrder + " " + title;
    }
}
