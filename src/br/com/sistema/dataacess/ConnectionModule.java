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
    String driver = "com.mysql.cj.jdbc.Driver";
    
    String url = "jdbc:mysql://localhost:3306/dbinfox";
    String user = "root";
    String password = "FlF03052002";
    
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
