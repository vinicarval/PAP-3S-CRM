/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.SAC;



/**
 *
 * @author icaro
 */
public interface ISACDao {
      public void save(Object businessObject,Object businessObject2,Object businessObject3);  
      public SAC find(int protocolo);
}
