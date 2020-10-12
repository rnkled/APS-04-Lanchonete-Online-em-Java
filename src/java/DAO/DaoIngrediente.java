/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
                + " fg_ativo) "
                + "VALUES(?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNome());
            stmt.setString(2, ingrediente.getDescricao());
            stmt.setInt(3, ingrediente.getQuantidade());
            stmt.setDouble(4, ingrediente.getValor_compra());
            stmt.setDouble(5, ingrediente.getValor_venda());
            stmt.setInt(6, ingrediente.getFg_ativo());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
    
    
    
    
