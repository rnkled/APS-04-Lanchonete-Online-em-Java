/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoUtil {
    
    public Connection conecta(){
        try{
            String url = "jdbc:postgresql://localhost:5432/lanchonete";
            String usuario = "postgres";
            String senha = "123456";
            return DriverManager.getConnection(url, usuario, senha);
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
    }
    
}
