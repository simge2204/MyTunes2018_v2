/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.be;

import java.util.List;

/**
 *
 * @author Kristian Bertelsen
 */
public class Playlist
    {
    private String name;
    public List<Song> songs;
    private String totaltime;
    private int id;

    
    public Playlist()
        {

        }
    public String getName()
        {
        return name;
        }
    public void setName(String name)
        {
        this.name = name;
        }
    public int getId()
        {
        return id;
        }  
    public void setId(int id)
        {
        this.id = id;
        }
    public List<Song> getSongs()
        {
        return songs;
        }
    public void setSongs(List<Song> songs)
        {
        this.songs = songs;
        }
    // beregner tiden fra listen af sange
    public String getTotalTime()
        {
        return totaltime;
        }
    public void setTotalTime(String totaltime)
        {
        this.totaltime = totaltime;
        }
    @Override
    public String toString()
        {
        return "Playlist{" + "name=" + name + ", songs=" + songs.size() + "time=" + getTotalTime() + '}';
        }
    }
