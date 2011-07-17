package br.com.bbsys.dao.generic;

import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import br.com.caelum.vraptor.ioc.Component;
import br.com.k2studio.vraptor.jpa.BaseEntity;
import br.com.k2studio.vraptor.jpa.JPAFactory;
 
@Component
public class GenericDAO {
 
     protected EntityManager manager;
 
     public GenericDAO() {
          manager = JPAFactory.getEntityManager();
     }
 
     public BaseEntity insert(BaseEntity entity) {
          manager.getTransaction().begin();
          manager.persist(entity);
          manager.getTransaction().commit();
          return entity;
     }
 
     public BaseEntity update(BaseEntity entity) {
          manager.getTransaction().begin();
          manager.merge(entity);
          manager.getTransaction().commit();
          return entity;
     }
 
     public void remove(BaseEntity entity) {
          entity = selectById(entity);
          manager.getTransaction().begin();
          manager.remove(entity);
          manager.getTransaction().commit();
     }
 
     public BaseEntity selectById(BaseEntity entity) {
          entity = manager.find(entity.getClass(), entity.getId());
 
          return entity;
     }
 
     public List<BaseEntity> selectByNamedQuery(String namedQuery) {
          return selectByNamedQuery(namedQuery, new Object[0]);
     }
 
     public List<BaseEntity> selectByNamedQuery(String namedQuery, Object ... parameters) {
          try {
               Query query = manager.createNamedQuery(namedQuery);
               if(parameters != null && parameters.length > 0) {
                    for (int i = 0; i < parameters.length; i++) {
                         query.setParameter(i + 1, parameters[i]);
                    }
               }
               List list = query.getResultList();
               return list;
          } catch (Exception ex) {
               ex.printStackTrace();
          }
          return null;
     }
 
}