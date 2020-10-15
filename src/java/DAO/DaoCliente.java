/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
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
public class DaoCliente {

    private Connection conecta;
    
    public DaoCliente(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Cliente cliente){
        //String sql = "INSERT INTO tb_clientes(nome, sobrenome, telefone, usuario, senha, fg_ativo, id_endereco) "
        //        + "VALUES(?,?,?,?,?,?,?)";
        String sql = "INSERT INTO tb_clientes(nome, sobrenome, telefone, usuario, senha, fg_ativo) "
                  + "VALUES(?,?,?,?,?,?)";
        
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getUsuario());
            stmt.setString(5, cliente.getSenha());
            stmt.setInt(6, cliente.getFg_ativo());
            //stmt.setInt(7, cliente.getEndereco().getId_endereco());
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Cliente> listarTodos(){
        String sql = "SELECT * FROM tb_clientes WHERE fg_Ativo='1' ORDER BY id_cliente";
        ResultSet rs;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setFg_ativo(1);
                
                
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            return clientes;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
    
}


