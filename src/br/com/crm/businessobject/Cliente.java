/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import br.com.crm.datalayer.ClienteDao;
import br.com.crm.datalayer.VendaDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author icaro
 */
public class Cliente {
    private String codCliente;
    private String nome;    
    private String CPF;
    private Date dataNascimento;
    private char sexo;
    private String email;
    private String classificacao;
    private String telefone;
    private String redeSocial;
    private int qtdCompra;

   

    
    
    public Cliente()
    {
    
    }
    
    public Cliente(String classificacao,Date datanascimento,char sexo,String email,String CPF,String nome,String telefone)
    {
    this.classificacao=classificacao;
    this.dataNascimento=datanascimento;
    this.sexo=sexo;
    this.email=email;
    this.CPF=CPF;
    this.nome=nome;
    this.telefone=telefone;
    }
     public Cliente(String classificacao,Date datanascimento,char sexo,String email,String CPF,String nome,String telefone,String redeSocial, String codCLiente)
    {
    this.classificacao=classificacao;
    this.dataNascimento=datanascimento;
    this.sexo=sexo;
    this.email=email;
    this.CPF=CPF;
    this.nome=nome;
    this.telefone=telefone;
    this.redeSocial=redeSocial;
    this.codCliente = codCLiente;
    }
     
     public Cliente(String classificacao,Date datanascimento,char sexo,String email,String CPF,String nome,String telefone,String redeSocial)
    {
    this.classificacao=classificacao;
    this.dataNascimento=datanascimento;
    this.sexo=sexo;
    this.email=email;
    this.CPF=CPF;
    this.nome=nome;
    this.telefone=telefone;
    this.redeSocial=redeSocial;
    }
    public Cliente(String redeSocial)
    {
    this.redeSocial=redeSocial;   
    }
    
    //VOLTA UM LISTA ORDENADA POR ORDEM DE CLASSIFICAÇÃO 
    public List List() throws Exception{
        ClienteDao cli = new ClienteDao();
        try{ 
         
            return cli.listOrdenadoClassificacao();
        }
        catch(Exception e){
            throw new Exception("ERRO AO LISTAR", e);
        }
    }

    
    
    //VOLTA UM CLIENTE 
    public Cliente findCliente(String CPF) throws Exception{
        ClienteDao cli = new ClienteDao();
        
        try{
            return cli.find(CPF);
        }
        catch(Exception e){
            throw new Exception("ERRO NA PROCURA", e);
        }
    }
    
    public void updateApenasCliente(Cliente cli) throws Exception{
        ClienteDao clis = new ClienteDao();
        
        try{
            clis.updateApenasCliente(cli);
        }
        catch(Exception e){
            throw new Exception("ERRO NA ATUALIZAÇÃO", e);
        }
    }
    
    public int quantidadeVendas(Cliente cli) throws Exception{
        VendaDao v = new VendaDao();
        
        try{
            return v.qtdVendas(cli);
        }
        catch(Exception e){
            throw new Exception("ERRO NA PROCURA", e);
        }
    }
    
    public Object[] carregarGrid(){
        return new Object[]{nome, CPF, classificacao};
    }
    
    public Object[] carregarGridCompras(){
        return new Object[]{codCliente ,nome, CPF, qtdCompra, classificacao};
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
     public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public int getQtdCompra() {
        return qtdCompra;
    }

    public void setQtdCompra(int qtdCompra) {
        this.qtdCompra = qtdCompra;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getdataNascimento() {
        return dataNascimento;
    }

    public void setdataNascimento(Date DataNascimento) {
        this.dataNascimento = DataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }
}
