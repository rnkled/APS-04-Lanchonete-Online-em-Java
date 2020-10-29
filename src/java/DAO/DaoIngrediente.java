/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kener_000
 */
public class DaoIngrediente {
    private Connection conecta;

    public DaoIngrediente() {
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Ingrediente ingrediente){
        String sql = "INSERT INTO tb_ingredientes(nm_ingrediente, descricao, quantidade, valor_compra, valor_venda,"
                + " tipo, fg_ativo) "
                + "VALUES(?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNome());
            stmt.setString(2, ingrediente.getDescricao());
            stmt.setInt(3, ingrediente.getQuantidade());
            stmt.setDouble(4, ingrediente.getValor_compra());
            stmt.setDouble(5, ingrediente.getValor_venda());
            stmt.setString(6, ingrediente.getTipo());
            stmt.setInt(7, ingrediente.getFg_ativo());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Ingrediente> listarTodos(){
        String sql = "SELECT * FROM tb_ingredientes WHERE fg_Ativo='1' ORDER BY id_ingrediente";
        ResultSet rs;
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
                ingrediente.setNome(rs.getString("nm_ingrediente"));
                ingrediente.setDescricao(rs.getString("descricao"));
                ingrediente.setQuantidade(rs.getInt("quantidade"));
                ingrediente.setValor_compra(rs.getDouble("valor_compra"));
                ingrediente.setValor_venda(rs.getDouble("valor_venda"));
                ingrediente.setTipo(rs.getString("tipo"));
                ingrediente.setFg_ativo(1);
                
                ingredientes.add(ingrediente);
            }
            rs.close();
            stmt.close();
            return ingredientes;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
    
    public List<Ingrediente> listarTodosPorLanche(int id){
        String sql = "SELECT i.id_ingrediente, i.nm_ingrediente, i.descricao, il.quantidade, "
                    + "i.valor_compra, i.valor_venda, i.tipo, i.fg_ativo "
                    + "FROM tb_ingredientes i "
                    + "INNER JOIN tb_ingredientes_lanche il "
                    + "ON (i.id_ingrediente = il.id_ingrediente)"
                    + "WHERE il.id_lanche = ?";

        ResultSet rs;
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
                ingrediente.setNome(rs.getString("nm_ingrediente"));
                ingrediente.setDescricao(rs.getString("descricao"));
                ingrediente.setQuantidade(rs.getInt("quantidade"));
                ingrediente.setValor_compra(rs.getDouble("valor_compra"));
                ingrediente.setValor_venda(rs.getDouble("valor_venda"));
                ingrediente.setTipo(rs.getString("tipo"));
                ingrediente.setFg_ativo(1);
                
                ingredientes.add(ingrediente);
            }
            rs.close();
            stmt.close();
            return ingredientes;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
    
    public void alterar(Ingrediente ingrediente){
        String sql = "UPDATE tb_ingredientes SET nm_ingrediente=?, descricao=?, quantidade=?, valor_compra=?, valor_venda=?,"
                + " tipo=? "
                + "WHERE id_ingrediente =?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNome());
            stmt.setString(2, ingrediente.getDescricao());
            stmt.setInt(3, ingrediente.getQuantidade());
            stmt.setDouble(4, ingrediente.getValor_compra());
            stmt.setDouble(5, ingrediente.getValor_venda());
            stmt.setString(6, ingrediente.getTipo());
            stmt.setInt(7, ingrediente.getId_ingrediente());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void remover(Ingrediente ingrediente){
        
            
        String sql = "DELETE FROM tb_ingredientes"
                +" WHERE id_ingrediente=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, ingrediente.getId_ingrediente());
            
            stmt.execute();
            stmt.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Ingrediente pesquisaPorNome(Ingrediente ingrediente){
        String sql = "SELECT * FROM tb_ingredientes WHERE nm_ingrediente='"+ingrediente.getNome()+"'";
        ResultSet rs;
        Ingrediente ingredienteResultado = new Ingrediente();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                ingredienteResultado.setId_ingrediente(rs.getInt("id_ingrediente"));
                ingredienteResultado.setNome(rs.getString("nm_ingrediente"));
                ingredienteResultado.setDescricao(rs.getString("descricao"));
                ingredienteResultado.setQuantidade(rs.getInt("quantidade"));
                ingredienteResultado.setValor_compra(rs.getDouble("valor_compra"));
                ingredienteResultado.setValor_venda(rs.getDouble("valor_venda"));
                ingredienteResultado.setTipo(rs.getString("tipo"));
                ingredienteResultado.setFg_ativo(1);

            }
            rs.close();
            stmt.close();
            return ingredienteResultado;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
}