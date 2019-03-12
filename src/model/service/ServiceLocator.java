/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.dao.ProdutoDAOImpl;
import model.dao.ProdutoDao;

/**
 *
 * @author Emerson Gabriel
 */
public class ServiceLocator {

    public static ProdutoDao getProdutoDao() {
        return new ProdutoDAOImpl();
    }
    
}
