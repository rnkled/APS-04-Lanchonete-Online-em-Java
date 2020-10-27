/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author kener_000
 */
public class DaoToken {

    private Connection conecta;
    
    public DaoToken(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(String token){
        String sql = "INSERT INTO tb_tokens(token) "
                  + "VALUES(?)";
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, token);
            
            stmt.execute();
            stmt.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean validar(String token){
        String sql = "SELECT token FROM tb_tokens WHERE token = ?";
        boolean resultado = false;
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, token);
        
            ResultSet rs;
            rs = stmt.executeQuery();
            
            while (rs.next()){    
                resultado = true;
            }
            
            rs.close();
            stmt.close();
            
            return resultado;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return false;
    }
    
    public void remover(String token){
        String sql = "DELETE FROM tb_tokens WHERE token = ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, token);
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
    public void LimparTabela(){
        String sql = "DELETE FROM tb_tokens";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
    }
        
    }