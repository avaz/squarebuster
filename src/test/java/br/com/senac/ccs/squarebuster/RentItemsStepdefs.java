package br.com.senac.ccs.squarebuster;

import br.com.senac.ccs.squarebuster.config.AppConfig;
import br.com.senac.ccs.squarebuster.model.Customer;
import br.com.senac.ccs.squarebuster.model.CustomerRepository;
import br.com.senac.ccs.squarebuster.model.Item;
import br.com.senac.ccs.squarebuster.model.Rent;
import cucumber.api.Format;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Date;
import java.util.List;
import org.springframework.test.context.ContextConfiguration;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
@ContextConfiguration( classes = AppConfig.class )
public class RentItemsStepdefs {
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    private DateTime today;
    private Rent rent;

    @Given( "^a Customer \"([^\"]*)\" fan of Star Wars$" )
    public void a_Customer_fan_of_Star_Wars( String customerName ) {
        customer = customerRepository.save( new Customer( customerName ) );
    }

    @And( "^today is \"([^\"]*)\"$" )
    public void today_is( @Format( "yyyy-MM-dd" ) Date today ) {
        this.today = new DateTime( today );
    }

    @When( "^the Customer Rent the follow Items:$" )
    public void the_Customer_Rent_the_follow_items( List<Item> items ) {
        this.rent = new Rent();
        this.rent.setCustomer( customer );
        this.rent.setRentedAt( today.toDate() );
        for ( Item item : items ) {
            rent.addItem( item );
        }
        rent.calculateReturnDate();
        rent.calculatePrice();
    }

    @Then( "^I should see the return date as \"([^\"]*)\" and the total price as \"(.+)\"$" )
    public void I_should_see_the_return_date_and_total_price( @Format( "yyyy-MM-dd" ) Date returnDate, double totalPrice ) throws Exception {
        assertThat( rent.getReturnAt(), is( not( nullValue() ) ) );
        assertThat( rent.getReturnAt(), is( returnDate ) );
        assertThat( rent.getPrice(), is( not( nullValue() ) ) );
        assertThat( rent.getPrice(), is( totalPrice ) );
    }


}
