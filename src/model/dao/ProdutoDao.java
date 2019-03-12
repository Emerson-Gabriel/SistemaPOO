/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.domain.Produto;

/**
 *
 * @author Emerson Gabriel
 */
public interface ProdutoDao {

    void excluir(Produto produto);

    List<Produto> pesquisar(Produto produto);

    void salvarAtualizar(Produto produto);
    
}
