/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyTunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 * @choi
 * 
 */
public class ConnectionManager {
    // Our connection Manager so you wont have to read through these exact lines of code a billion times!
    private SQLServerDataSource ds = new SQLServerDataSource();
    
    public ConnectionManager() {
        ds.setDatabaseName("MyTunes17");
        ds.setUser("CS2018A_17");
        ds.setPassword("CS2018A_17");
        ds.setServerName("EASV-DB2");
        ds.setPortNumber(1433);
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
}
