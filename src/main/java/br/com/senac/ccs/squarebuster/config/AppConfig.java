package br.com.senac.ccs.squarebuster.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories( basePackages = { "br.com.senac" } )
@ComponentScan( basePackages = { "br.com.senac" } )
public class AppConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean b = new LocalContainerEntityManagerFactoryBean();
        b.setDataSource( dataSource() );
        b.setPackagesToScan( "br.com.senac" );
        b.setPersistenceProviderClass( org.hibernate.ejb.HibernatePersistence.class );
        b.setJpaDialect( new org.springframework.orm.jpa.vendor.HibernateJpaDialect() );
        Properties props = new Properties();
        props.setProperty( "hibernate.format_sql", "false" );
        props.setProperty( "hibernate.show_sql", "false" );
        props.setProperty( "hibernate.hbm2ddl.auto", "update" );
        b.setJpaProperties( props );
        return b;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
        transactionManager.setDataSource( dataSource() );
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl( "jdbc:hsqldb:mem:db" );
        dataSource.setUsername( "db" );
        dataSource.setPassword( "db" );
        dataSource.setMaxActive( 10 );
        dataSource.setMinIdle( 10 );
        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
    
}
