package br.com.bbsys.dao.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.bbsys.model.usuario.Usuario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDao {

    private EntityManager manager;
    private final Session session; 
    
    public UsuarioDao(Session session){
            this.session = session;
    }
    /*
    public UsuarioDao (EntityManager manager) {
        this.manager = manager;
    }*/
    
    public Usuario loadByEmailAndSenha(String email, String senha) throws Exception {
		try {
			Usuario usuario = session.load(Usuario.class, email, senha);
			/*Query query = manager.createQuery("from Usuario where email = :email and senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			Usuario usuario = (Usuario) query.getSingleResult();*/
			return usuario;
		} catch (NoResultException e) {
			manager.getTransaction().rollback();
			throw new Exception("Nenhum resultado!");
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new Exception("Ocorreu um erro na busca!");
		}
	}
    
    public void salva(Usuario usuario) {
        Transaction tx = session.beginTransaction();
        session.save(usuario);
        tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listaUsuarios(){
	        return this.session.createCriteria(Usuario.class).list();
	}
	
	public Usuario carrega(Integer id) {
	        return (Usuario) this.session.load(Usuario.class, id);
	}
	
	public void atualiza(Usuario usuario) {
	        Transaction tx = session.beginTransaction();
	        session.update(usuario);
	        tx.commit();
	}
	
	public void remove(Usuario usuario) {
	        Transaction tx = session.beginTransaction();
	        this.session.delete(usuario);
	        tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> busca(String nome) {
	        return session.createCriteria(Usuario.class)
	                .add(Restrictions.ilike("name", nome, MatchMode.ANYWHERE))
	                .list();
	}
	
	public void recarrega(Usuario usuario) {
	        session.refresh(usuario); 
	}
	
}
