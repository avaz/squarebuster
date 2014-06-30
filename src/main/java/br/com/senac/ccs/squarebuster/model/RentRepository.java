package br.com.senac.ccs.squarebuster.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RentRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Rent save(Rent rent) {
        if(rent.getId() != null) {
            rent = em.merge( rent );
        }
        else {
            em.persist( rent );
        }
        return rent;
    }
    
    public Rent findByItem(Item item) {
        return null;
    }
    
}
