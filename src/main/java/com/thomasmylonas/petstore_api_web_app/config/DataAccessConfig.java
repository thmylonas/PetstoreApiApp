package com.thomasmylonas.petstore_api_web_app.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.petstore_api_web_app"})
public class DataAccessConfig {

    private static final Logger LOGGER = LogManager.getLogger(DataAccessConfig.class.getName());

    @Bean(name = "entityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setPersistenceUnitName("Petstore_PU");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);

        emfb.setPackagesToScan("com.thomasmylonas.petstore_api_web_app");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    DataSource dataSource() {

        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/petstoredb");
        jndiObjectFactoryBean.setResourceRef(true); // Default value: false
        // jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/petstoredb");
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        // jndiObjectFactoryBean.setLookupOnStartup(false); // Default value: true
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            LOGGER.error("NamingException thrown while jndiObjectFactoryBean.afterPropertiesSet");
            e.printStackTrace();
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    // Alternative solutions for retrieving DataSource from JNDI
    //@Bean
    DataSource dataSourceViaJndi() {

        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/petstoredb");
            // ds = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/petstoredb"); // Alternatively
        } catch (NamingException e) {
            LOGGER.error("NamingException thrown while lookup with JNDI");
            e.printStackTrace();
        }
        return ds;
    }

    //@Bean
    public DataSource dataSourceViaJndiEnv() {

        Properties env = new Properties();
        // env.put(Context.INITIAL_CONTEXT_FACTORY, org.apache.naming.java.javaURLContextFactory.class.getName());
        // env.put(Context.PROVIDER_URL, "localhost:8080");
        env.put(Context.INITIAL_CONTEXT_FACTORY, com.sun.jndi.rmi.registry.RegistryContextFactory.class.getName());
        env.put(Context.PROVIDER_URL, "rmi://localhost:8080");

        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext(env);
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/petstoredb");
        } catch (NamingException e) {
            LOGGER.error("NamingException thrown while lookup with JNDI");
            e.printStackTrace();
        }
        return ds;
    }
}
