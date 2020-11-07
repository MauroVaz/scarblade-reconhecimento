package com.unip.tcc.scarblade.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

public class HikariCP3ConnectionFactory {

	private static HikariCP3ConnectionFactory hikariConnectionFactory;
	private HikariDataSource hikariDatasource;

	protected HikariCP3ConnectionFactory() {
		try {

			InputStream inputStream = HikariCP3ConnectionFactory.class.getClassLoader().getResourceAsStream("database_pool.properties");
			Properties propertiesDatabaseProperties = new Properties();
			propertiesDatabaseProperties.load(inputStream);

			if (propertiesDatabaseProperties != null) {

				hikariDatasource = new HikariDataSource();

				hikariDatasource.setPoolName(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.poolName"));
				hikariDatasource.setUsername(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.username"));
				hikariDatasource.setPassword(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.password"));
				hikariDatasource.setJdbcUrl(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.jdbcUrl"));
				hikariDatasource.setDriverClassName(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.driverClassName"));

				hikariDatasource.setAutoCommit(Boolean.parseBoolean(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.autoCommit")));
				hikariDatasource.setReadOnly(Boolean.parseBoolean(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.readOnly")));
				hikariDatasource.setTransactionIsolation(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.transactionIsolation"));
				hikariDatasource.setCatalog(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.catalog"));
				hikariDatasource.setConnectionTimeout(Long.parseLong(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.connectionTimeout")));

				hikariDatasource.setMinimumIdle(Integer.parseInt(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.minimumIdle")));
				hikariDatasource.setMaximumPoolSize(Integer.parseInt(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.maximumPoolSize")));
				hikariDatasource.setCatalog(propertiesDatabaseProperties.getProperty("HikariConnectionFactory.catalog"));

				/*
				 * hikariConfig.addDataSourceProperty("cachePrepStmts", conf.getBoolean("cachePrepStmts"));
				 * hikariConfig.addDataSourceProperty("prepStmtCacheSize", conf.getInt("prepStmtCacheSize"));
				 * hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", conf.getInt("prepStmtCacheSqlLimit"));
				 * hikariConfig.addDataSourceProperty("useServerPrepStmts", conf.getBoolean("useServerPrepStmts"));
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static HikariCP3ConnectionFactory getInstance() {
		if (hikariConnectionFactory == null) {
			hikariConnectionFactory = new HikariCP3ConnectionFactory();
			return hikariConnectionFactory;
		} else {
			return hikariConnectionFactory;
		}
	}

	public Connection getConnection() {
		try {
			return this.hikariDatasource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}