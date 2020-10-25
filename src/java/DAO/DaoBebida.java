/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Bebida;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
    
    
    
    
