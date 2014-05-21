package config;

import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * User: aconstantin
 * Date: 5/19/14 8:45 PM
 */

@Configuration
@PropertySource("classpath:database.properties")
public class DataConsoleConfig {
//    @Autowired
//    private Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
//        final ComboPooledDataSource ds = new ComboPooledDataSource();
//        ds.setDriverClass(env.getProperty("database.driverClassName"));
//        ds.setJdbcUrl(env.getProperty("database.url"));
//        ds.setUser(env.getProperty("database.username"));
//        ds.setPassword(env.getProperty("database.password"));
//
//
//        ds.setPreferredTestQuery("SELECT 1 FROM DUAL");
//        ds.setTestConnectionOnCheckout(true);
//
//        ds.setUnreturnedConnectionTimeout(Integer.parseInt(env.getProperty("database.unreturnedConnectionTimeout")
//        ));
//        ds.setDebugUnreturnedConnectionStackTraces(true);
//        ds.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("database.idleConnectionTestPeriod")));
//
//        //
//        ds.setDataSourceName("testing");
//        ds.setMinPoolSize(Integer.parseInt(env.getProperty("database.minPoolSize")));
//        ds.setMaxPoolSize(Integer.parseInt(env.getProperty("database.maxPoolSize")));
//
//
//        return ds;
        DataSource ds = new EmbeddedDatabaseBuilder().setName("testing").setType(EmbeddedDatabaseType.HSQL).build();

        return ds;
    }

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) throws SQLException {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("testdb/schema.sql"));
        populator.addScript(new ClassPathResource("testdb/data.sql"));
        populator.populate(dataSource.getConnection());
        return populator;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setDatabasePlatform(Oracle10gDialect.class.getName());
        jpaVendorAdapter.setGenerateDdl(false);
        return jpaVendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws PropertyVetoException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPersistenceUnitName("testing");
        em.setPackagesToScan("app.domain");
        em.setJpaDialect(new HibernateJpaDialect());
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.afterPropertiesSet();
        return em.getObject();
    }
}