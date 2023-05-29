/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dataacess;
import java.sql.*;
/**
 *
 * @author Felipe Firmino
 */
public class ConnectionModule {
    public static Connection Conector(){
    Connection connection = null;
     //the path for the driver in the lib, in this app it was used the one below
    String driver = "com.mysql.cj.jdbc.Driver";
    //where the data base is installed, in my case it was in my own machine 
    String url = "jdbc:mysql://localhost:3306/dbinfox";
    String user = "root";
    String password = "";
    
    try {
     Class.forName(driver);
     connection = DriverManager.getConnection(url,user,password);
     return connection;
    } catch (Exception e) {
        System.out.println("erro na conexao");
            System.out.println(e.getMessage());                 
            return null;
        }
    }
}
