/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Usuario;

/**
 *
 * @author icaro
 */
public interface IDaoUsuario {
     public String findSenha(int idFunc);
}
