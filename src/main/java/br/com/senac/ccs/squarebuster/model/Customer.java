package br.com.senac.ccs.squarebuster.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String document;
    @OneToMany
    @JoinTable(name="CUSTOMER_RENTS",
        joinColumns=
            @JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="RENT_ID", referencedColumnName="ID")
    )
    private List<Rent> rents;

    public Customer() {
        this.rents = new ArrayList<Rent>();
    }

    public Customer( String name ) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument( String document ) {
        this.document = document;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents( List<Rent> rents ) {
        this.rents = rents;
    }

}
