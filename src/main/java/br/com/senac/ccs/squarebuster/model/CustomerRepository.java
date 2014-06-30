package br.com.senac.ccs.squarebuster.model;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Customer save( Customer customer ) {
        if ( customer.getId() != null ) {
            customer = em.merge( customer );
        }
        else {
            em.persist( customer );
        }
        return customer;
    }

    public List<Customer> findByRentsRentedAtBetween( Date rentAtStart, Date rentAtEnd ) {
        JPAQuery select = new JPAQuery(em);
        QCustomer customer = QCustomer.customer;
        QRent rent = QRent.rent;
        List<Customer> customers = new ArrayList<Customer>();
        customers = select.from( customer )
                .innerJoin( customer.rents, rent)
                .where(rent.rentedAt.between( rentAtStart, rentAtEnd))
                .list( customer );
        return customers;
    }
}
