/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import model.dao.ProdutoDAOImpl;
import model.dao.ProdutoDao;
import model.domain.Produto;
import model.service.ServiceLocator;
import org.jdesktop.observablecollections.ObservableCollections;
import util.ValidacaoException;

/**
 *
 * @author Emerson Gabriel
 */
public class ProdutoControl {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Produto produtoDigitado;
    private Produto produtoSelecionado;
    private List<Produto> produtosTabela;
    private final ProdutoDao produtoDAO;
    
    public ProdutoControl(){
        
        produtoDAO = ServiceLocator.getProdutoDao();
        produtosTabela = ObservableCollections.observableList(new ArrayList<Produto> ());
        novo();
        pesquisar();
        
    }

    public void novo() {
        setProdutoDigitado(new Produto()); 
    }

    public void pesquisar() {
        produtosTabela.clear();
        produtosTabela.addAll(produtoDAO.pesquisar(produtoDigitado));
    }

    public void salvar() throws ValidacaoException{
        produtoDigitado.validar(); 
        produtoDAO.salvarAtualizar(produtoDigitado);
        novo();
        pesquisar();
    }
    
     public void excluir(){
        produtoDAO.excluir(produtoDigitado);
        novo();
        pesquisar();
    }
    
    public Produto getProdutoDigitado() {
        return produtoDigitado;
    }

    public void setProdutoDigitado(Produto produtoDigitado) {
        Produto oldProdutoDigitado = this.produtoDigitado;
        this.produtoDigitado =  produtoDigitado;
        propertyChangeSupport.firePropertyChange("produtoDigitado",oldProdutoDigitado,produtoDigitado);
        this.produtoDigitado = produtoDigitado;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        
        this.produtoSelecionado = produtoSelecionado;
        if (this.produtoSelecionado != null){
            setProdutoDigitado(produtoSelecionado);
        }
    }

    public List<Produto> getProdutosTabela() {
        return produtosTabela;
    }

    public void setProdutosTabela(List<Produto> produtosTabela) {
        this.produtosTabela = produtosTabela;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener p){
        propertyChangeSupport.addPropertyChangeListener(p);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener p){
        propertyChangeSupport.removePropertyChangeListener(p);
    }
}
