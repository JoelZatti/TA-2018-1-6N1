package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Joel
 */
@Stateful
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;
        ordem = "nome"; // define a ordem padrão do DAO
        maximoObjetos = 3;
    }

    public Usuario getObjectById(Object id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        /**
         * A linha obj.getPermissoes().size(); é necessária para inicializar a coleção
         * para quando ela for exibida na tela não gerar um erro de 
         * lazyInicializationException
         */
        obj.getPermissoes().size();
        return obj;
    }
    public Usuario localizaPorNomeUsuario(String nomeUsuario){
        Query query = em.createQuery("from Usuario where upper(usuario) = :nomeUsuario");
        query.setParameter("nomeUsuario", nomeUsuario.toUpperCase());
        Usuario obj = (Usuario) query.getSingleResult();
        obj.getPermissoes().size();
        return obj;
    }
}
