/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_colaborador", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O carga horária não pode ser nula")
    @Column(name = "cargaHoraria", nullable = false)
    private Integer cargaHoraria;

    @NotNull(message = "O gestor não pode ser nulo")
    private Boolean gestor;
    
    @NotNull(message = "O projeto deve ser informado")
    @ManyToOne
    @JoinColumn(name = "projeto", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_projeto")
    private Projeto projeto;
    
    @NotNull(message = "O usuario deve ser informado")
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_usuario")
    private Usuario usuario;

    public Colaborador() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the cargaHoraria
     */
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the gestor
     */
    public Boolean getGestor() {
        return gestor;
    }

    /**
     * @param gestor the gestor to set
     */
    public void setGestor(Boolean gestor) {
        this.gestor = gestor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the projeto
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
