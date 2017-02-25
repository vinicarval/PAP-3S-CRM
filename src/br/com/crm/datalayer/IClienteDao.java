/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Endereco;
import java.util.List;

/**
 *
 * @author icaro
 */
public interface IClienteDao {

    public List<Endereco> listEnd();
    
    public List listComQtdCompras();
    
    public  void update(Object clie, Object ende);
    
    public void updateApenasCliente(Object clie);
    
    public List<Cliente> listOrdenadoClassificacao();
    
    public Cliente find(String Cpf);

    public Endereco findEndereco(String Cpf);
    
    public void save(Object businessObject,Object businessObject2);
}
