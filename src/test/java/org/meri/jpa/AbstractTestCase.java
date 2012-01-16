package org.meri.jpa;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.meri.jpa.util.InMemoryDBUtil;
import org.meri.jpa.util.JNDIUtil;

public abstract class AbstractTestCase {

	private JNDIUtil jndiUtil = new JNDIUtil();
	private InMemoryDBUtil databaseUtil = new InMemoryDBUtil();
	
	@BeforeClass
	public static void beforeClass() {
		(new JNDIUtil()).initializeJNDI();
		(new InMemoryDBUtil()).removeDatabase();
	}

	@Before
	public void beforeEachTest() {
		jndiUtil.initializeJNDI();
		databaseUtil.initializeDatabase(getInitialChangeLog());
	}

	protected abstract String getInitialChangeLog();

	protected JNDIUtil getJndiUtil() {
		return jndiUtil;
	}
	
	protected InMemoryDBUtil getDatabaseUtil() {
		return databaseUtil;
	}

	protected Connection getConnection() {
		try {
			DataSource dataSource = databaseUtil.getDataSource();
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create db connection.", e);
		}
	}
	
	protected void removeDatabase() {
		databaseUtil.removeDatabase();
	}
	
}
