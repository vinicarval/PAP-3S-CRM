/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import br.com.crm.datalayer.ProdutoDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vinícius
 */
public class ProdutoVenda {

    private int idProduto;
    private String nomeProd;
    private String descricaoProduto;
    private int quantidade;
    private String modoPagamentoVenda;
    private String codVenda;
    private String origemVenda;
    private Date dataCompraVenda;

    private String nomeProduto;

    public ProdutoVenda() {
    }
    
    

    public ProdutoVenda(int idProduto, String nomeProd, String DescricaoProduto, int quantidade, String modoPagamentoVenda, String codVenda, String origemVenda, Date dataCompraVenda, String nomeProduto) {
        this.idProduto = idProduto;
        this.nomeProd = nomeProd;
        this.descricaoProduto = DescricaoProduto;
        this.quantidade = quantidade;
        this.modoPagamentoVenda = modoPagamentoVenda;
        this.codVenda = codVenda;
        this.origemVenda = origemVenda;
        this.dataCompraVenda = dataCompraVenda;
        this.nomeProduto = nomeProduto;
    }

    public ProdutoVenda(int idProduto, String nomeProd, String DescricaoProduto, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProd = nomeProd;
        this.descricaoProduto = DescricaoProduto;
        this.quantidade = quantidade;
    }
    
    public Object[] carregarGrid(){
        return new Object[]{nomeProd, descricaoProduto, quantidade};
    }
    
    public List List() throws Exception{
        ProdutoDAO cli = new ProdutoDAO();
        try{ 
         
            return cli.ListProdVenda();
        }
        catch(Exception e){
            throw new Exception("ERRO AO LISTAR", e);
        }
    }
 public static void quickSort(List<Integer> vetor, int inicio, int fim) {
        if (inicio < fim) {
            Integer divisao = particaoQuickSort(vetor, inicio, fim);
            
        quickSort(vetor, inicio, divisao - 1 );
        quickSort(vetor, divisao + 1, fim);
        }
    }    
        
    private static int particaoQuickSort(List<Integer> vetor, int inicio, int fim) {
        int pivo = vetor.get(inicio);
        
        int posPivo = inicio;
            for (int i = inicio + 1; i <= fim; i++) {
                if (vetor.get(i) < pivo) {
                    vetor.set(posPivo, vetor.get(i));
                    vetor.set(i, vetor.get(posPivo)+1);
                    posPivo++;
                }
            }
        vetor.set(posPivo, pivo);
        return posPivo;
    }
    

    public String getModoPagamentoVenda() {
        return modoPagamentoVenda;
    }

    public void setModoPagamentoVenda(String modoPagamentoVenda) {
        this.modoPagamentoVenda = modoPagamentoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(String codVenda) {
        this.codVenda = codVenda;
    }

    public String getOrigemVenda() {
        return origemVenda;
    }

    public void setOrigemVenda(String origemVenda) {
        this.origemVenda = origemVenda;
    }

    public Date getDataCompraVenda() {
        return dataCompraVenda;
    }

    public void setDataCompraVenda(Date dataCompraVenda) {
        this.dataCompraVenda = dataCompraVenda;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String DescricaoProduto) {
        this.descricaoProduto = DescricaoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

}
