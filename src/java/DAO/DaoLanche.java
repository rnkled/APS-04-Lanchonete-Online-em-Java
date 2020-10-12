/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Lanche;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author kener_000
 */
public class DaoLanche {
    private Connection conecta;

    public DaoLanche() {
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Lanche lanche){
        String sql = "INSERT INTO tb_lanches(nm_lanche, descricao, valor_venda, fg_ativo, valor_lanche) "
                + "VALUES(?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, lanche.getNome());
            stmt.setString(2, lanche.getDescricao());
            stmt.setDouble(3, lanche.getValor_lanche());
            stmt.setInt(4, lanche.getFg_ativo());
            stmt.setDouble(5, lanche.getValor_lanche());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
    
    
    
    
