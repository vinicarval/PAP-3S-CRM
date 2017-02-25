/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import java.util.Date;



/**
 *
 * @author icaro
 */
public class Funcionario {
    private String rg;
    private String CPF;
    private char sexo;
    private String nome;
    private String email;
    private Date dataNascimento;
    private String telefone;
    private String nomeDept;

   
    public Funcionario()
    {
    
    }
     public Funcionario(String rg,char sexo,Date dataNascimento,String email,String Cpf,String nome,String telefone)
    {
    this.rg=rg;
    this.sexo=sexo;
    this.dataNascimento=dataNascimento;
    this.email=email;
    this.CPF=Cpf;
    this.nome=nome;
    this.telefone=telefone;
    }
     public Funcionario(String nomeDept)
     {
     this.nomeDept=nomeDept;
     
     }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getNomeDept() {
        return nomeDept;
    }

    public void setNomeDept(String nomeDept) {
        this.nomeDept = nomeDept;
    }
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   
    
}
