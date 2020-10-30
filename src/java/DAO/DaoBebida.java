/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Bebida;
import Model.Ingrediente;
import Model.Lanche;
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
public class DaoBebida {
    private Connection conecta;

    public DaoBebida() {
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Bebida bebida){
        String sql = "INSERT INTO tb_bebidas(nm_bebida, descricao, quantidade, valor_compra, valor_venda,"
                + " tipo, fg_ativo) "
                + "VALUES(?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, bebida.getNome());
            stmt.setString(2, bebida.getDescricao());
            stmt.setInt(3, bebida.getQuantidade());
            stmt.setDouble(4, bebida.getValor_compra());
            stmt.setDouble(5, bebida.getValor_venda());
            stmt.setString(6, bebida.getTipo());
            stmt.setInt(7, bebida.getFg_ativo());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Bebida> listarTodos(){
        String sql = "SELECT * FROM tb_bebidas WHERE fg_Ativo='1' ORDER BY id_bebida";
        ResultSet rs;
        List<Bebida> bebidas = new ArrayList<Bebida>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Bebida bebida = new Bebida();
                bebida.setId_bebida(rs.getInt("id_bebida"));
                bebida.setNome(rs.getString("nm_bebida"));
                bebida.setDescricao(rs.getString("descricao"));
                bebida.setQuantidade(rs.getInt("quantidade"));
                bebida.setValor_compra(rs.getDouble("valor_compra"));
                bebida.setValor_venda(rs.getDouble("valor_venda"));
                bebida.setTipo(rs.getString("tipo"));
                bebida.setFg_ativo(1);
                
                bebidas.add(bebida);
            }
            rs.close();
            stmt.close();
            return bebidas;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
    
    public void alterar(Bebida bebida){
        String sql = "UPDATE tb_bebidas SET nm_bebida=?, descricao=?, quantidade=?, valor_compra=?, valor_venda=?,"
                + " tipo=? "
                + "WHERE id_bebida =?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, bebida.getNome());
            stmt.setString(2, bebida.getDescricao());
            stmt.setInt(3, bebida.getQuantidade());
            stmt.setDouble(4, bebida.getValor_compra());
            stmt.setDouble(5, bebida.getValor_venda());
            stmt.setString(6, bebida.getTipo());
            stmt.setInt(7, bebida.getId_bebida());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void remover(Bebida bebida){
        String sql = "DELETE FROM tb_bebidas"
                +" WHERE id_bebida=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, bebida.getId_bebida());
            
            stmt.execute();
            stmt.close();
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public Bebida pesquisaPorNome(String nome){
        String sql = "SELECT * FROM tb_bebidas WHERE nm_bebida='"+nome+"'";
        ResultSet rs;
        Bebida bebidaResultado = new Bebida();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                bebidaResultado.setId_bebida(rs.getInt("id_bebida"));
                bebidaResultado.setNome(rs.getString("nm_bebida"));
                bebidaResultado.setDescricao(rs.getString("descricao"));
                bebidaResultado.setQuantidade(rs.getInt("quantidade"));
                bebidaResultado.setValor_compra(rs.getDouble("valor_compra"));
                bebidaResultado.setValor_venda(rs.getDouble("valor_venda"));
                bebidaResultado.setTipo(rs.getString("tipo"));
                bebidaResultado.setFg_ativo(1);
                
            }
            rs.close();
            stmt.close();
            return bebidaResultado;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
}
    
    
    
    
