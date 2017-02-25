/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import br.com.crm.datalayer.VendaDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author icaro
 */
public class Venda {

    String modoPagamento;
    String codVenda;
    String origemVenda;
    Date dataCompra;
    String nomeProd;

    public Venda() {

    }

    public Venda(String modoPagamento, String codVenda, String origemVenda, Date dataCompra) {
        this.modoPagamento = modoPagamento;
        this.codVenda = codVenda;
        this.origemVenda = origemVenda;
        this.dataCompra = dataCompra;
    }

    public Venda(String modoPagamento, String origemVenda, Date dataCompra, String nomeProd, String codVenda) {
        this.modoPagamento = modoPagamento;
        this.origemVenda = origemVenda;
        this.dataCompra = dataCompra;
        this.nomeProd = nomeProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public Venda findVenda(String codVenda) throws Exception {
        VendaDao v = new VendaDao();

        try {
            return v.find(codVenda);
        } catch (Exception e) {
            throw new Exception("ERRO NA PROCURA", e);
        }
    }
    
    public List ListPorCliente(Cliente cli) throws Exception{
        VendaDao v = new VendaDao();
        try{ 
         
            return v.ListVendaPorCliente(cli);
        }
        catch(Exception e){
            throw new Exception("ERRO AO LISTAR", e);
        }
    }
    
    public Object[] carregarGrid(){
        return new Object[]{ nomeProd, dataCompra, modoPagamento,origemVenda};
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(String modoPagamento) {
        this.modoPagamento = modoPagamento;
    }

    public String getOrigemVenda() {
        return origemVenda;
    }

    public void setOrigemVenda(String origemVenda) {
        this.origemVenda = origemVenda;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

}
