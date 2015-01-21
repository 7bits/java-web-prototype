package it.sevenbits.project.application.config.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;


/**
 * MyBatis Configuration (for database)
 */
@Configuration
@PropertySource("file:${project.config}/configurations/application/jdbc-mysql-template.properties")

/*****************************************************************/
/* @MapperScan - myBatis annotation. Shows path to data mappers  */
/* if you want use another ORM then you should erase this string */
/* and switch another ORM (See documentation for it)             */
/*****************************************************************/
@MapperScan("it.sevenbits.project.application.repositories")

@EnableTransactionManagement
public class MyBatisConfig {

    /**
     * Timeout in seconds before an abandoned connection can be removed.
     * Defaults to 300 seconds.
     */

    @Inject
    private Environment environment;

    /**
     * DataSource Bean setup
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        ds.setUrl(environment.getProperty("jdbc.url"));
        ds.setUsername(environment.getProperty("jdbc.username"));
        ds.setPassword(environment.getProperty("jdbc.password"));
        ds.setMaxActive(Integer.valueOf(environment.getProperty("jdbc.max-active")));
        ds.setMaxWait(Integer.valueOf(environment.getProperty("jdbc.max-wait")));
        ds.setInitialSize(Integer.valueOf(environment.getProperty("jdbc.connections-number")));
        ds.setValidationQuery(environment.getProperty("jdbc.validation-query"));
        ds.setPoolPreparedStatements(true);
        ds.setDefaultAutoCommit(true);
        ds.setTestOnBorrow(true);
        ds.setRemoveAbandonedTimeout(Integer.valueOf(environment.getProperty("jdbc.remove-abandoned-timeout")));
        ds.setRemoveAbandoned(true);

        return ds;
    }

    /**
     * SQL Session Factory setup
     * @return SQL Session Factory
     * @throws Exception if fails
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());

        /************************************/
        /* MyBatis configuration            */
        /* Set default value for NULL type  */
        /* Used for Oracle DB               */
        /************************************/
        Resource resource = new ClassPathResource("mvn-profile-name/mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);

        return sqlSessionFactory.getObject();
    }

    /**
     * Transaction Manager Setup
     * @return transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());
    }
}
