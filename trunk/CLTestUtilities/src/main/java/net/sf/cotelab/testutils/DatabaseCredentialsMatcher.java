package net.sf.cotelab.testutils;

import java.util.Properties;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

/**
 * This is a helper class intended for use in jMock enabled jUnit tests that 
 * test JDBC connections and drivers.  The JDBC Driver Manager provides a 
 * getConnection() method that accepts a URL, username, and password but the
 * Driver interface only provides a getConnection method that takes a URL and
 * a properties object.  This matcher will allow the test code to look 
 * something like this:
 * 
 * {@code oneOf(connection).getConnection(with(url),with(databaseCredentialsMatching(user,password));}
 * 
 * The actual call to getConnection will pass in a {@link Properties} object 
 * containing a "user" property and a "password" property that are matched
 * against the user and password values specified.
 * 
 * @author USX13992
 *
 */
public class DatabaseCredentialsMatcher extends TypeSafeMatcher<Properties> {
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	
	private final String user;
	private final String password;

	public DatabaseCredentialsMatcher(String user, String password) {
		this.user = user;
		this.password = password;

	}

	@Override
	public void describeTo(Description desc) {
		desc.appendText("properties containing a user '" + user + "' and a password '" + password + "'" );
	}

	@Override
	protected boolean matchesSafely(Properties item) {
		return (item.containsKey(USER) && item.getProperty(USER).equals(user))
			&& (item.containsKey(PASSWORD) && item.getProperty(PASSWORD).equals(password));
	}
	
	@Factory
	public static TypeSafeMatcher<Properties> databaseCredentialsMatching( String user, String password ) {
		return new DatabaseCredentialsMatcher( user, password );
	}
}
