/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.businessobject;

import br.com.crm.datalayer.UsuarioDao;

/**
 *
 * @author icaro
 */
public class Usuario {
    private String senha;
    private String nivelAcesso;
    private int idFunc;
    
    public Usuario()
    {
    
    }
    
    
    
    
    public Usuario(String senha, String nivelAcesso, int idFunc) {
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
        this.idFunc = idFunc;
    }
    
    //Valida o login, voltando um true ou false, e como parâmetro a senha e o código de funcionário
    public boolean validarLogin(int idFunc, String senha) throws Exception{
        this.senha = senha;
        this.idFunc = idFunc;
        
        try{
        UsuarioDao usua = new UsuarioDao();
        String senhaBanco = usua.findSenha(this.getIdFunc());
        return senhaBanco.equals(senha);
        }
        catch(Exception e){
            throw new Exception("ERRO AO ACHAR USUÁRIO",  e);
        }
        
    }
    
    //Muda a senha do usuário
    public boolean mudarSenha(int idFunc, String senhaAtual, String senhaNova) throws Exception{
        
        UsuarioDao usu = new UsuarioDao();
        try{
            if (this.validarLogin(idFunc, senhaAtual)){
                this.senha = senhaNova;
                usu.update(this);
            } 
            else{
                return false;
            }
        }
        
        catch(Exception e ){
            throw new Exception("PROBLEMA EM MUDAR SENHA!",  e);
        }
      
        return true;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
    
    
    
}
