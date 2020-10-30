/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.RelatorioBebidas;
import Model.RelatorioGastos;
import Model.RelatorioLanches;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class DaoRelatorio {
    private Connection conecta;
    
    public DaoRelatorio(){
        this.conecta = new DaoUtil().conecta();
    }
    
    public List<RelatorioLanches> listarRelLanches(){
        String sql = "SELECT l.nm_lanche, i.nm_ingrediente, il.quantidade,\n" +
                "i.valor_compra * il.quantidade AS custo_ingrediente,\n" +
                "i.valor_venda * il.quantidade AS venda_ingrediente,\n" +
                "(i.valor_venda - i.valor_compra)*il.quantidade AS lucro_ingrediente,\n" +
                "l.valor_venda,\n"+
                "(SELECT SUM(i.valor_compra*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)) AS custo_total_ingredientes,\n" +
                "(SELECT SUM(i.valor_venda*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)) AS venda_total_ingredientes,\n" +
                "(SELECT SUM(i.valor_venda*il.quantidade)-SUM(i.valor_compra*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)) AS lucro_total_ingredientes,\n" +
                "l.valor_venda - (SELECT SUM(i.valor_venda*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)) AS lucro_operacional,\n" +
                "((SELECT SUM(i.valor_venda*il.quantidade)-SUM(i.valor_compra*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente))) +\n" +
                "(l.valor_venda - (SELECT SUM(i.valor_venda*il.quantidade)\n" +
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente))) AS lucro_total\n"+
                "FROM tb_ingredientes_lanche il\n" +
                "INNER JOIN tb_lanches l ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)\n" +
                "GROUP BY l.nm_lanche, i.nm_ingrediente, il.quantidade, i.valor_venda, i.valor_compra, l.valor_venda\n"+
                "ORDER BY l.nm_lanche;";
        /*String sql = "select nm_lanche, nm_lanche as nm_ingrediente, nm_lanche as quantidade,"
                + "nm_lanche as custo_ingrediente, nm_lanche as venda_ingrediente, nm_lanche as lucro_ingrediente,"
                + "nm_lanche as valor_venda, nm_lanche as custo_total_ingredientes, "
                + "nm_lanche as venda_total_ingredientes, nm_lanches as lucro_total_ingredientes, "
                + "nm_lanche as lucro_operacional, nm_lanche as lucro_total "
                + "from tb_lanches;";*/
        ResultSet rs;
        List<RelatorioLanches> relatorio = new ArrayList<RelatorioLanches>();
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                RelatorioLanches rl = new RelatorioLanches();
                rl.setLanche(rs.getString("nm_lanche"));
                rl.setIngrediente(rs.getString("nm_ingrediente"));
                rl.setQuantidade(rs.getInt("quantidade"));
                rl.setCustoIngrediente(rs.getFloat("custo_ingrediente"));
                rl.setVendaIngrediente(rs.getFloat("venda_ingrediente"));
                rl.setLucroIngrediente(rs.getFloat("lucro_ingrediente"));
                rl.setValorVenda(rs.getFloat("valor_venda"));
                rl.setCustoTotalIngredientes(rs.getFloat("custo_total_ingredientes"));
                rl.setVendaTotalIngredientes(rs.getFloat("venda_total_ingredientes"));
                rl.setLucroTotalIngredeintes(rs.getFloat("lucro_total_ingredientes"));
                rl.setLucroOperacional(rs.getFloat("lucro_operacional"));
                rl.setLucroTotal(rs.getFloat("lucro_total"));

                relatorio.add(rl);
            }
            rs.close();
            stmt.close();
            return relatorio;
        }catch(SQLException e){
            System.out.println(e);
        }
        return relatorio;
    }
    
    public List<RelatorioBebidas> listarRelBebidas(){
        String sql = "SELECT p.id_pedido, CONCAT(c.nome,' ',c.sobrenome) as cliente, b.nm_bebida, bp.quantidade,\n" +
                "SUM (b.valor_compra)*bp.quantidade AS custo_bebidas,\n" +
                "SUM(b.valor_venda)*bp.quantidade AS venda_bebidas,\n" +
                "(SUM(b.valor_venda)*bp.quantidade) - (SUM (b.valor_compra)*bp.quantidade) AS lucro_por_bebida,\n" +
                "(SELECT SUM(b.valor_compra*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida \n" +
                "AND bp.id_pedido = p.id_pedido)) AS total_custo_bebidas,\n" +
                "(SELECT SUM(b.valor_venda*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida \n" +
                "AND bp.id_pedido = p.id_pedido)) AS total_venda_bebidas,\n" +
                "(SELECT SUM(b.valor_venda*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida \n" +
                "AND bp.id_pedido = p.id_pedido)) -\n" +
                "(SELECT SUM(b.valor_compra*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida \n" +
                "AND bp.id_pedido = p.id_pedido)) AS lucro_total\n" +
                "FROM tb_pedidos p\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(p.id_pedido = bp.id_pedido)\n" +
                "INNER JOIN tb_clientes c ON(c.id_cliente = p.id_cliente)\n" +
                "INNER JOIN tb_bebidas b ON(b.id_bebida = bp.id_bebida)\n" +
                "GROUP BY p.id_pedido,c.nome, c.sobrenome, b.nm_bebida, bp.quantidade\n" +
                "ORDER BY p.id_pedido;";
        List<RelatorioBebidas> relatorio = new ArrayList<RelatorioBebidas>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                RelatorioBebidas rb = new RelatorioBebidas();
                rb.setIdPedido(rs.getInt("id_pedido"));
                rb.setCliente(rs.getString("Cliente"));
                rb.setNomeBebida(rs.getString("nm_bebida"));
                rb.setQuantidade(rs.getInt("quantidade"));
                rb.setCustoBebidas(rs.getFloat("custo_bebidas"));
                rb.setVendaBebidas(rs.getFloat("venda_bebidas"));
                rb.setLucroBebidas(rs.getFloat("lucro_por_bebida"));
                rb.setTotalCustoBebidas(rs.getFloat("total_custo_bebidas"));
                rb.setTotalVendaBebidas(rs.getFloat("total_venda_bebidas"));
                rb.setTotalLucroBebidas(rs.getFloat("lucro_total"));
                relatorio.add(rb);
            }
            rs.close();
            stmt.close();
            return relatorio;
        }catch(SQLException e){
            System.out.println(e);
        }
        return relatorio;
    }
    
    public List<RelatorioGastos> listarRelGastos(){
        String sql = "SELECT (SELECT SUM(i.valor_compra*il.quantidade*lp.quantidade)\n" +
                "FROM tb_lanches_pedido lp\n" +
                "INNER JOIN tb_lanches l ON(lp.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes_lanche il ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente))+\n" +
                "(SELECT SUM(b.valor_compra*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida)) AS custo,\n" +
                "(SELECT SUM(l.valor_venda*lp.quantidade) FROM tb_lanches l\n" +
                "INNER JOIN tb_lanches_pedido lp ON (l.id_lanche = lp.id_lanche))+\n" +
                "(SELECT SUM(b.valor_venda*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida)) AS vendas,\n" +
                "((SELECT SUM(l.valor_venda*lp.quantidade) FROM tb_lanches l\n" +
                "INNER JOIN tb_lanches_pedido lp ON (l.id_lanche = lp.id_lanche)) -\n" +
                "(SELECT SUM(i.valor_compra*il.quantidade*lp.quantidade)\n" +
                "FROM tb_lanches_pedido lp\n" +
                "INNER JOIN tb_lanches l ON(lp.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes_lanche il ON(il.id_lanche = l.id_lanche)\n" +
                "INNER JOIN tb_ingredientes i ON(il.id_ingrediente = i.id_ingrediente)))+\n" +
                "((SELECT SUM(b.valor_venda*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida))-\n" +
                "(SELECT SUM(b.valor_compra*bp.quantidade)\n" +
                "FROM tb_bebidas b\n" +
                "INNER JOIN tb_bebidas_pedido bp ON(b.id_bebida = bp.id_bebida)))AS lucro";
        List<RelatorioGastos> relatorio = new ArrayList<RelatorioGastos>();
        ResultSet rs;
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                RelatorioGastos rg = new RelatorioGastos();
                rg.setCusto(rs.getFloat("custo"));
                rg.setVenda(rs.getFloat("vendas"));
                rg.setLucro(rs.getFloat("custo"));
                relatorio.add(rg);
            }
            rs.close();
            stmt.close();
            return relatorio;
        }catch(SQLException e){
            System.out.println(e);
        }
        return relatorio;
    }
}
