package br.com.senac.ccs.squarebuster.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.DateMidnight;

@Entity
public class Rent {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String description;
    @Temporal( TemporalType.TIMESTAMP )
    private Date rentedAt;
    @Temporal( TemporalType.DATE )
    private Date returnAt;
    private Double price;
    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "RENT_ITEMS",
                joinColumns =
            @JoinColumn( name = "RENT_ID", referencedColumnName = "ID" ),
                inverseJoinColumns =
            @JoinColumn( name = "ITEM_ID", referencedColumnName = "ID" ) )
    private List<Item> items;
    @ManyToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinTable( name = "CUSTOMER_RENTS",
                joinColumns =
            @JoinColumn( name = "RENT_ID", referencedColumnName = "ID" ),
                inverseJoinColumns =
            @JoinColumn( name = "CUSTOMER_ID", referencedColumnName = "ID" ) )
    private Customer customer;

    public Rent() {
        this.items = new ArrayList<Item>();
        this.rentedAt = new Date();
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

    public Date getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt( Date rentedAt ) {
        this.rentedAt = rentedAt;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems( List<Item> items ) {
        this.items = items;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice( Double price ) {
        this.price = price;
    }

    public Date getReturnAt() {
        return returnAt;
    }

    public void setReturnAt( Date returnAt ) {
        this.returnAt = returnAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public void addItem( Item item ) {
        this.getItems().add( item );
    }

    public Date calculateReturnDate() {
        DateMidnight returnDate = new DateMidnight( this.getRentedAt() );
        int size = this.getItems().size();
        if ( !this.getItems().isEmpty() ) {
            switch ( size ) {
                case 1:
                case 2:
                    returnDate = returnDate.plusDays( 1 );
                    break;
                case 3:
                case 4:
                    returnDate = returnDate.plusDays( 2 );
                    break;
                default:
                    returnDate = returnDate.plusDays( 4 );
                    break;
            }
            this.returnAt = returnDate.toDate();
        }
        return returnAt;
    }

    public Double calculatePrice() {
        Double price = 0.0;
        for ( Item item : getItems() ) {
            price += item.getPrice();
        }
        this.price = price;
        return price;
    }
}
