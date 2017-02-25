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
 * 
 */
public abstract class DaoBase<T> implements IDaoBase<T> {
    public static final long serialVersionUID = 0L;
    @Override
    public void delete(T businessObject)
    {
    
    }

    @Override
    public void save(T businessObject,T businessObject2)
    {
    
    
    }
    @Override
    public void update(T businessObject)
    {
    
    
    }
    @Override
    public void update(T businessObject,T businessObject2)
    {
    
    
    }
    @Override
     public List list()
     {
        return null;
     
     }
   
    
   
}
