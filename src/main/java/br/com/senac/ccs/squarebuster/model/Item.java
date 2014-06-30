package br.com.senac.ccs.squarebuster.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private double price;
    
    @ManyToOne
    @JoinTable( name = "RENT_ITEMS",
                joinColumns =
            @JoinColumn( name = "ITEM_ID", referencedColumnName = "ID" ),
                inverseJoinColumns =
            @JoinColumn( name = "RENT_ID", referencedColumnName = "ID" ) )
    private Rent rent;


    public Item() {
        price = 1.0;
    }

    public Item( String description, double price ) {
        this();
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent( Rent rent ) {
        this.rent = rent;
    }
    
}
