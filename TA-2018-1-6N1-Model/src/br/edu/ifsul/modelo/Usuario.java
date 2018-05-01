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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 40, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não ser em branco")
    @Length(max = 50, message = "O email não pode ter mais de {max} caracteres")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotNull(message = "O nome de usuario não pode ser nulo")
    @NotBlank(message = "O nome de usuario não ser em branco")
    @Length(max = 20, message = "O nome de usuario não pode ter mais de {max} caracteres")
    @Column(name = "usuario", length = 20, nullable = false, unique = true)
    private String usuario;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não ser em branco")
    @Length(max = 10, message = "A senha não pode ter mais de {max} caracteres")
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;

    @NotNull(message = "O setor deve ser informado")
    @ManyToOne
    @JoinColumn(name = "setor", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_setor")
    private Setor setor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permissoes",
            joinColumns
            = @JoinColumn(name = "usuario", referencedColumnName = "usuario", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario", "permissao"})})
    private List<Permissao> permissoes = new ArrayList<>();

    public Usuario() {
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nascimento
     */
    public Calendar getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
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
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the permissoes
     */
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    /**
     * @param permissoes the permissoes to set
     */
    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

}
