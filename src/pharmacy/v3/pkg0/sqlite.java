/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.v3.pkg0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author gerges Fci-H
 */
public class sqlite {
    Connection con=null;
    public static String readConnectionPath() {
        String connectionPath = null;
        try (BufferedReader br = new BufferedReader(new FileReader("ConnectionPath.txt"))) {
            connectionPath = br.readLine().trim(); // Read the first line of the file
            System.out.println(connectionPath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading connection path file: " + e.getMessage());
        }
        return connectionPath;
    }
    public static Connection connectDB()
    {
        String connectionString=readConnectionPath();
            if (connectionString == null || connectionString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Connection string is not provided.");
            return null;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con=DriverManager.getConnection(connectionString);
            
            return con;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
