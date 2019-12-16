package com.thomasmylonas.petstore_api_web_app.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.petstore_api_web_app"})
public class DataAccessConfig {

    @Bean(name = "entityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setPersistenceUnitName("Petstore_PU");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);

        emfb.setPackagesToScan("com.thomasmylonas.petstore_api_web_app");
        return emfb;
    }

    @Bean(name = "dataSource1")
    DataSource dataSource1() {
        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/petstoredb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase("ORACLE");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        return adapter;
    }

    DataSource a() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, org.apache.naming.java.javaURLContextFactory.class.getName());
        env.put(Context.PROVIDER_URL, "localhost:8080");
        env.put(Context.INITIAL_CONTEXT_FACTORY, com.sun.jndi.rmi.registry.RegistryContextFactory.class.getName());
        env.put(Context.PROVIDER_URL, "rmi://localhost:8080");

        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext(env);
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/petstoredb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }

    @Bean(name = "dataSource")
    DataSource dataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("jdbc/petstoredb");
        jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/petstoredb");
//        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//        jndiObjectFactoryBean.setLookupOnStartup(false);
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    DataSource c() {
        DataSource ds = null;
        try {
            ds = (DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/petstoredb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
//        emfb.setDataSource(ds);
    }
}
