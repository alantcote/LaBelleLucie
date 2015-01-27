package net.sf.cotelab.testutils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;

public class JDBCTestHelper extends jMockTestHelper {
	protected final String URL = "jdbc:test://" + getClass().getName() + "/testing";
	protected Driver driver;
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement preparedStatement;
	protected ResultSet results;
	
	public void addDatabaseURL(String url) {
		((JdbcTestDriver)driver).addURL(url);
	}
	@Before 
	public void runBeforeTests() throws Exception {
		super.runBeforeTests();
		if( driver != null ) {
			// tries to insure that old drivers are unregistered
			DriverManager.deregisterDriver( driver );
		}
		connection = context.mock( Connection.class );
		statement = context.mock( Statement.class );
		preparedStatement = context.mock( PreparedStatement.class );
		results = context.mock( ResultSet.class );
		driver = new JdbcTestDriver( URL, connection );
		DriverManager.registerDriver( driver );
	}
	
	@After
	public void runAfterTests() throws Exception {
		DriverManager.deregisterDriver(driver);
		//needs to run after deregistration to prevent failed assertions
		//from breaking subsequent tests
		super.runAfterTests();
	}
	
	@Factory
	protected static TypeSafeMatcher<Properties> databaseCredentialsMatching( String user, String password ) {
		return DatabaseCredentialsMatcher.databaseCredentialsMatching( user, password );
	}
}
