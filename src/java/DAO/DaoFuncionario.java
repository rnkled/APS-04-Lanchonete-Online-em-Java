/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author kener_000
 */
public class DaoFuncionario {

    private Connection conecta;
    
    public DaoFuncionario(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public void salvar(Funcionario funcionario){
        String sql = "INSERT INTO tb_funcionarios(nome, sobrenome, usuario, senha, salario, cad_por, fg_ativo) "
                + "VALUES(?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSobrenome());
            stmt.setString(3, funcionario.getUsuario());
            stmt.setString(4, funcionario.getSenha());
            stmt.setDouble(5, funcionario.getSalario());
            stmt.setInt(6, funcionario.getCad_por());
            stmt.setInt(7, funcionario.getFg_ativo());
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
}


