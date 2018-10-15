/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author Uwe-Laptop
 */
public class ImageHandler {
    
    @Resource(name="jdbc/db")
    private DataSource dataSource;
    private static final String SQL_LATEST_10 = "SELECT data FROM picture ORDER BY uploaddate ASC LIMIT 10";
    
    public void getLatestImages(){
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_LATEST_10)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int i = rs.getInt("idpicture");
                System.out.println("i");
            }
        }catch(Exception e){
            
        }
        
    }
}
