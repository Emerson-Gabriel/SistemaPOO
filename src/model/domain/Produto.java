/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import util.ValidacaoException;

/**
 *
 * @author Emerson Gabriel
 */
@Entity
@Table(name="TB_PRODUTO")

public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_PRODUTO", nullable = false)
    private Integer codigo;
    
    @Column(name="DESCRICAO",length = 255,nullable = false)
    private String descricao;
    
    public Produto(){
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (this.codigo != null)
            hash = 23 * hash + this.codigo;
        else
            hash = 23 * hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void validar() throws ValidacaoException{
        if(this.descricao == null || this.descricao.equals("")){
            throw new ValidacaoException("Campo descrição não preenchido");
        }
    }
}
