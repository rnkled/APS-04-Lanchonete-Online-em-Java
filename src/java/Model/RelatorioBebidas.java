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
public class RelatorioBebidas {
    private int idPedido;
    private String cliente;
    private String nomeBebida;
    private int quantidade;
    private float custoBebidas;
    private float vendaBebidas;
    private float lucroBebidas;
    private float totalCustoBebidas;
    private float totalVendaBebidas;
    private float totalLucroBebidas;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getCustoBebidas() {
        return custoBebidas;
    }

    public void setCustoBebidas(float custoBebidas) {
        this.custoBebidas = custoBebidas;
    }

    public float getVendaBebidas() {
        return vendaBebidas;
    }

    public void setVendaBebidas(float vendaBebidas) {
        this.vendaBebidas = vendaBebidas;
    }

    public float getLucroBebidas() {
        return lucroBebidas;
    }

    public void setLucroBebidas(float lucroBebidas) {
        this.lucroBebidas = lucroBebidas;
    }

    public float getTotalCustoBebidas() {
        return totalCustoBebidas;
    }

    public void setTotalCustoBebidas(float totalCustoBebidas) {
        this.totalCustoBebidas = totalCustoBebidas;
    }

    public float getTotalVendaBebidas() {
        return totalVendaBebidas;
    }

    public void setTotalVendaBebidas(float totalVendaBebidas) {
        this.totalVendaBebidas = totalVendaBebidas;
    }

    public float getTotalLucroBebidas() {
        return totalLucroBebidas;
    }

    public void setTotalLucroBebidas(float totalLucroBebidas) {
        this.totalLucroBebidas = totalLucroBebidas;
    }
}
