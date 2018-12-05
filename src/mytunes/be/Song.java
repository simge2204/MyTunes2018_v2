/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

/**
 *
 * @author maxim
 */
public class Song
{
    private int id;
    private int releaseyear;
    private String time;
    private String title;
    private String genre;
    private String artist;
    private String path;
//    int time = Integer.parseInt(timer);
    /**
     *
     * @param id
     * @param title
     * @param releaseyear
     * @param genre
     * @param time
     * @param artist
     * @param path
     */
    
    public int getId()
    {
        return id;
    }

    public int getReleaseyear() 
    {
        return releaseyear;
    }
    
    public String getTime()
    {
        return time;
    }
    public String getGenre()
    {
        return genre;
    }
    
    public String getTitle() 
    {
        return title;
    }
    
    public String getArtist() 
    {
        return artist;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public void setId(int id) 
    {
        this.id = id;
    }
    
    public void setReleaseyear(int releaseyear) 
    {
        this.releaseyear = releaseyear;
    }
    
    public void setTime(String time) 
    {
        this.time = time;
    }
    
    public void setGenre(String genre) 
    {
        this.genre = genre;
    }
    
    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    public void setArtist(String artist) 
    {
        this.artist = artist;
    }
    
    public void setPath(String path)
    {
        this.path = path;
    }
    
    @Override
   public String toString()
        {
        return "Song{" + "id" + id + ", releaseyear" + releaseyear + ", time" + time + ", title" + title + ", genre" + genre + ", artist" + artist + '}';
        }
}
