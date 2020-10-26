/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.EncryptadorMD5;
import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
        String sql = "INSERT INTO tb_funcionarios(nome, sobrenome, usuario, senha, cargo, salario, cad_por, fg_ativo) "
                + "VALUES(?,?,?,MD5(?),?,?,?,?)";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSobrenome());
            stmt.setString(3, funcionario.getUsuario());
            stmt.setString(4, funcionario.getSenha());
            stmt.setString(5, funcionario.getCargo());
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setInt(7, funcionario.getCad_por());
            stmt.setInt(8, funcionario.getFg_ativo());
            stmt.execute();
            stmt.close();
            
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Funcionario pesquisaPorUsuario(Funcionario funcionario){
        String sql = "SELECT * FROM tb_funcionarios WHERE usuario='"+funcionario.getUsuario()+"'";
        ResultSet rs;
        Funcionario funcionarioResultado = new Funcionario();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                funcionarioResultado.setId(rs.getInt("id_funcionario"));
                funcionarioResultado.setNome(rs.getString("nome"));
                funcionarioResultado.setSobrenome(rs.getString("sobrenome"));
                funcionarioResultado.setUsuario(rs.getString("usuario"));
                funcionarioResultado.setSenha(rs.getString("senha"));
                funcionarioResultado.setSalario(rs.getDouble("salario"));
                funcionarioResultado.setCad_por(rs.getInt("cad_por"));
                funcionarioResultado.setFg_ativo(1);

            }
            rs.close();
            stmt.close();
            return funcionarioResultado;
        
            
        } catch(SQLException e){
            
             throw new RuntimeException(e);
        }
        
    }
    
    public boolean login(Funcionario funcionario){
        String sql = "SELECT usuario, senha, fg_ativo FROM tb_funcionarios WHERE usuario = ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, funcionario.getUsuario());
        
            ResultSet rs;
            rs = stmt.executeQuery();
            Funcionario validFuncionario = new Funcionario();
            EncryptadorMD5 md5 = new EncryptadorMD5();
            
            while (rs.next()){    
                validFuncionario.setUsuario(rs.getString("usuario"));
                validFuncionario.setSenha(rs.getString("senha"));
                validFuncionario.setFg_ativo(rs.getInt("fg_ativo"));
            }
            
            rs.close();
            stmt.close();
            
            System.out.println(md5.encryptar(funcionario.getSenha()));
            System.out.println(validFuncionario.getSenha());
            
            System.out.println((md5.encryptar(funcionario.getSenha()).equals(validFuncionario.getSenha())));
            
            if((md5.encryptar(funcionario.getSenha()).equals(validFuncionario.getSenha())) && (validFuncionario.getFg_ativo() == 1)){
                return true;
            } else { return false; }
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }
    
}


