package org.apache.dolphinscheduler.dao.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/29 17:00
 *
 * not spring manager connection, only use for init db, and alert module for non-spring application
 * data source connection factory
 */


public class ConnectionFactory extends SpringConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    /**
     *   静态内部类创建单例
     * */
    private static class ConnectionFactoryHolder {
        private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        return ConnectionFactoryHolder.connectionFactory;
    }

    private ConnectionFactory() {
        try {
            dataSource = buildDataSource();
            sqlSessionFactory = getSqlSessionFactory();
            sqlSessionTemplate = getSqlSessionTemplate();
        } catch (Exception e) {
            logger.error("Initializing ConnectionFactory error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * sql session factory
     */
    private SqlSessionFactory sqlSessionFactory;

    /**
     * sql session template
     */
    private SqlSessionTemplate sqlSessionTemplate;

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * get the data source
     *
     * @return druid dataSource
     */
    private DataSource buildDataSource() {

        DruidDataSource druidDataSource = dataSource();
        return druidDataSource;
    }

    /**
     * * get sql session factory
     *
     * @return sqlSessionFactory
     * @throws Exception sqlSessionFactory exception
     */
    private SqlSessionFactory getSqlSessionFactory() throws Exception {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, getDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setEnvironment(environment);
        configuration.setLazyLoadingEnabled(true);
        configuration.addMappers("org.apache.dolphinscheduler.dao.mapper");
        configuration.addInterceptor(new PaginationInterceptor());

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(getDataSource());

        sqlSessionFactoryBean.setTypeEnumsPackage("org.apache.dolphinscheduler.*.enums");
        sqlSessionFactory = sqlSessionFactoryBean.getObject();

        return sqlSessionFactory;
    }

    private SqlSessionTemplate getSqlSessionTemplate() {
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    /**
     * get sql session
     *
     * @return sqlSession
     */
    public SqlSession getSqlSession() {
        return sqlSessionTemplate;
    }

    /**
     * get mapper
     *
     * @param type target class
     * @param <T>  generic
     * @return target object
     */
    public <T> T getMapper(Class<T> type) {
        try {
            return getSqlSession().getMapper(type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("get mapper failed");
        }
    }
}
