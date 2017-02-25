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
 * @author icaro
 */
public class Produto {
    private String nome;
    private String Descricao;
    private int idProd;

    public Produto() {
    }
    
    
    public Produto(int idProd,String nome, String Descricao) {
        this.nome = nome;
        this.Descricao = Descricao;
        this.idProd=idProd;
    }

    public Produto(String nome, String Descricao) {
        this.nome = nome;
        this.Descricao = Descricao;
    }
    
    public List List() throws Exception{
        ProdutoDAO cli = new ProdutoDAO();
        try{ 
         
            return cli.ListTudo();
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
    
    public Object[] carregarGrid(){
        return new Object[]{nome, Descricao, 0};
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    
}
