/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Bebida;
import Model.Lanche;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kener_000
 */
public class DaoPedido {
    private Connection conecta;

    public DaoPedido() {
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Pedido pedido){
        String sql = "INSERT INTO tb_pedidos(id_cliente, data_pedido, valor_total) "
                + "VALUES(?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, pedido.getCliente().getId_cliente());
            stmt.setString(2, pedido.getData_pedido());
            stmt.setDouble(3, pedido.getValor_total());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void vincularLanche(Pedido pedido, Lanche lanche){
        
        String sql = "INSERT INTO tb_lanches_pedido(id_pedido, id_lanche, quantidade)"
                + "VALUES(?,?,?)";
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, pedido.getId_pedido());
            stmt.setInt(2, lanche.getId_lanche());
            stmt.setInt(3, lanche.getQuantidade());

            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
    }
    
    public void vincularBebida(Pedido pedido, Bebida bebida){
        
        String sql = "INSERT INTO tb_bebidas_pedido(id_pedido, id_bebida, quantidade)"
                + "VALUES(?,?,?)";
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, pedido.getId_pedido());
            stmt.setInt(2, bebida.getId_bebida());
            stmt.setInt(3, bebida.getQuantidade());

            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
    }
        
    public Pedido pesquisaPorData(Pedido pedido){
        String sql = "SELECT * FROM tb_pedidos WHERE data_pedido='"+pedido.getData_pedido()+"'";
        ResultSet rs;
        Pedido pedidoResultado = new Pedido();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                pedidoResultado.setId_pedido(rs.getInt("id_pedido"));
                //pedidoResultado.setCliente(cliente);
                pedidoResultado.setData_pedido(rs.getString("data_pedido"));
                pedidoResultado.setValor_total(rs.getDouble("valor_total"));
                
            }
            rs.close();
            stmt.close();
            return pedidoResultado;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
}