/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
        String sql = "INSERT INTO tb_pedidos(id_lanche, id_cliente, data_pedido, valor_total) "
                + "VALUES(?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, pedido.getLanche().getId_lanche());
            stmt.setInt(2, pedido.getCliente().getId_cliente());
            stmt.setString(3, pedido.getData_pedido());
            stmt.setDouble(4, pedido.getValor_total());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}