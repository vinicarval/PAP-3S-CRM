/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

/**
 *
 * @author icaro
 */
public class Endereco {
    private String rua;
    private String estado;
    private String cidade;
    private String bairro;
    private String Cep;
    private String numero;
    public Endereco()
    {
    
    
    } 
     public Endereco(String bairro,String cidade,String estado,String rua,String numero,String cep )
    {
    this.bairro=bairro;
    this.cidade=cidade;
    this.estado=estado;
    this.rua=rua;
    this.numero=numero;
    this.Cep=cep;
    
    } 
    
    
    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }
}
