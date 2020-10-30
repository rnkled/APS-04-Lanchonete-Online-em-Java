/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gabriel
 */
public class RelatorioLanches {
    private String lanche;
    private String ingrediente;
    private int quantidade;
    private float custoIngrediente;
    private float vendaIngrediente;
    private float lucroIngrediente;
    private float custoTotalIngredientes;
    private float vendaTotalIngredientes;
    private float lucroTotalIngredeintes;
    private float lucroOperacional;
    private float valorVenda;
    private float lucroTotal;

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getCustoIngrediente() {
        return custoIngrediente;
    }

    public void setCustoIngrediente(float custoIngrediente) {
        this.custoIngrediente = custoIngrediente;
    }

    public float getVendaIngrediente() {
        return vendaIngrediente;
    }

    public void setVendaIngrediente(float vendaIngrediente) {
        this.vendaIngrediente = vendaIngrediente;
    }

    public float getLucroIngrediente() {
        return lucroIngrediente;
    }

    public void setLucroIngrediente(float lucroIngrediente) {
        this.lucroIngrediente = lucroIngrediente;
    }

    public float getCustoTotalIngredientes() {
        return custoTotalIngredientes;
    }

    public void setCustoTotalIngredientes(float custoTotalIngredientes) {
        this.custoTotalIngredientes = custoTotalIngredientes;
    }

    public float getVendaTotalIngredientes() {
        return vendaTotalIngredientes;
    }

    public void setVendaTotalIngredientes(float vendaTotalIngredientes) {
        this.vendaTotalIngredientes = vendaTotalIngredientes;
    }

    public float getLucroTotalIngredeintes() {
        return lucroTotalIngredeintes;
    }

    public void setLucroTotalIngredeintes(float lucroTotalIngredeintes) {
        this.lucroTotalIngredeintes = lucroTotalIngredeintes;
    }

    public float getLucroOperacional() {
        return lucroOperacional;
    }

    public void setLucroOperacional(float lucroOperacional) {
        this.lucroOperacional = lucroOperacional;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public float getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(float lucroTotal) {
        this.lucroTotal = lucroTotal;
    }
}
