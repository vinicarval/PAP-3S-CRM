/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import br.com.crm.datalayer.ProdutoDAO;
import java.util.List;

/**
 *
 * @author Vinícius
 */


public class ProdutoSAC {
    private int idProduto;
    private String nomeProduto;
    private String DescricaoProduto;
    private int quantidade;   
    private int protocoloSAC;
    private String tipoLigacaoSAC;
    private int avaliacaoSAC;
    private String estadoChamadaSAC;

    public ProdutoSAC() {
    }
    
    
    public ProdutoSAC(String nomeProduto, String DescricaoProduto, int idProduto, int protocoloSAC, String tipoLigacaoSAC, int avaliacaoSAC, String estadoChamadaSAC) {
        this.nomeProduto = nomeProduto;
        this.DescricaoProduto = DescricaoProduto;
        this.idProduto = idProduto;
        this.protocoloSAC = protocoloSAC;
        this.tipoLigacaoSAC = tipoLigacaoSAC;
        this.avaliacaoSAC = avaliacaoSAC;
        this.estadoChamadaSAC = estadoChamadaSAC;
    }

    public ProdutoSAC(int idProduto, String nomeProduto, String DescricaoProduto, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.DescricaoProduto = DescricaoProduto;
        this.quantidade = quantidade;
    }
    
    public Object[] carregarGrid(){
        return new Object[]{nomeProduto, DescricaoProduto, quantidade};
    }
    
    public List List() throws Exception{
        ProdutoDAO cli = new ProdutoDAO();
        try{ 
         
            return cli.ListProdutoPorSAC();
        }
        catch(Exception e){
            throw new Exception("ERRO AO LISTAR", e);
        }
    }
    
    public List ListReclamacao() throws Exception{
        ProdutoDAO cli = new ProdutoDAO();
        try{ 
         
            return cli.ListProdutoReclamacao();
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
    

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return DescricaoProduto;
    }

    public void setDescricaoProduto(String DescricaoProduto) {
        this.DescricaoProduto = DescricaoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getProtocoloSAC() {
        return protocoloSAC;
    }

    public void setProtocoloSAC(int protocoloSAC) {
        this.protocoloSAC = protocoloSAC;
    }

    public String getTipoLigacaoSAC() {
        return tipoLigacaoSAC;
    }

    public void setTipoLigacaoSAC(String tipoLigacaoSAC) {
        this.tipoLigacaoSAC = tipoLigacaoSAC;
    }

    public int getAvaliacaoSAC() {
        return avaliacaoSAC;
    }

    public void setAvaliacaoSAC(int avaliacaoSAC) {
        this.avaliacaoSAC = avaliacaoSAC;
    }

    public String getEstadoChamadaSAC() {
        return estadoChamadaSAC;
    }

    public void setEstadoChamadaSAC(String estadoChamadaSAC) {
        this.estadoChamadaSAC = estadoChamadaSAC;
    }

    
    
    
}
