package br.com.senac.ccs.squarebuster;

import br.com.senac.ccs.squarebuster.model.Customer;
import br.com.senac.ccs.squarebuster.model.CustomerRepository;
import br.com.senac.ccs.squarebuster.config.AppConfig;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        CustomerRepository repository = context.getBean( CustomerRepository.class );
        DateTime orderedAtStart = new DateTime().withDate( 2013, 1, 1 );
        DateTime orderedAtEnd = new DateTime().withDate( 2013, 2, 1 );
        List<Customer> customers = repository.findByRentsRentedAtBetween( orderedAtStart.toDate(), orderedAtEnd.toDate() );
        System.out.println( customers.size() );
    }
}
