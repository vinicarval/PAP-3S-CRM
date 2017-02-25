/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;


import java.util.List;




/**
 *
 * @author icaro
 * @param <T>
 */
public interface IDaoBase<T> {
    public List list();
    public void save(T businessObject,T businessObject2);    
    public void update(T businessObject);
    public void update(T businessObject,T businessObject2);
    public void delete(T businessObject);
  
}
