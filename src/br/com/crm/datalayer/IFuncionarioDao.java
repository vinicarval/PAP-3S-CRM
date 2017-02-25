/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Endereco;
import br.com.crm.businessobject.Funcionario;
import java.util.List;

/**
 *
 * @author icaro
 */
public interface IFuncionarioDao {
    public List<Endereco> ListEnd();
    public Funcionario find(String Cpf);
    public Endereco findEndereco(String Cpf);
}
