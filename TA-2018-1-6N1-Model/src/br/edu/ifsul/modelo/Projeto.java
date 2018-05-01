/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_projeto", sequenceName = "seq_projeto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_projeto", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar em branco")
    @NotNull(message = "O campo descrição não pode ser nulo")
    @Length(max = 100, message = "O descrição não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 100, nullable = false)
    private String descricao;

    @NotNull(message = "A data de início não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "inicio", nullable = false)
    private Calendar inicio;
    
    @NotNull(message = "A data final não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "fim", nullable = false)
    private Calendar fim;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
    
    @NotNull(message = "O setor deve ser informado")
    @ManyToOne
    @JoinColumn(name = "setor", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_setor")
    private Setor setor;
    
    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)    
    private List<Colaborador> colaboradores = new ArrayList<>();
    
    public Projeto(){        
    }
    
    public void adicionarColaboradores(Colaborador obj){
        obj.setProjeto(this);
        this.colaboradores.add(obj);
    }
    
    public void removerColaboradores(int index){
        this.colaboradores.remove(id);
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the inicio
     */
    public Calendar getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fim
     */
    public Calendar getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the setor
     */
    public Setor getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Projeto other = (Projeto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the colaboradores
     */
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    /**
     * @param colaboradores the colaboradores to set
     */
    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

}
