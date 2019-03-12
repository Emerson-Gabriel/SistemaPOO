/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.domain.Produto;

/**
 *
 * @author Emerson Gabriel
 */
public class ProdutoDAOImpl implements ProdutoDao {

    @Override
    public void salvarAtualizar(Produto produto) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (produto.getCodigo() != null) {
            produto = em.merge(produto);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void excluir(Produto produto) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        produto = em.merge(produto);
        em.remove(produto);
        em.getTransaction().commit();
        em.close();
    }
    
    
    @Override
    public List<Produto> pesquisar(Produto produto) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Produto p where 1 = 1");
        if (produto.getCodigo() != null){
            sql.append("and p.codigo = :codigo");
        }
        
        if (produto.getDescricao() != null && !produto.getDescricao().equals("")){
            sql.append("and p.descricao like :descricao");
        }
        
        Query query = em.createQuery(sql.toString());
        if (produto.getCodigo() != null){
            query.setParameter("codigo", produto.getCodigo());
        }
        if (produto.getDescricao()!= null && !produto.getDescricao().equals("")){
            query.setParameter("descricao", "%"+ produto.getCodigo() + "%");
        }
        return query.getResultList();
    }
}
