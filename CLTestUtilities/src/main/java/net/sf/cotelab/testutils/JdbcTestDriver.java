package net.sf.cotelab.testutils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcTestDriver implements Driver {
	
	private List<String> url;
	private Connection connection;

	public JdbcTestDriver (String url, Connection connection){
		this.url = new ArrayList<String>();
		this.url.add(url);
		this.connection = connection;
		
	}
	
	public void addURL(String url) {
		this.url.add(url);
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		for (String aURL : this.url) {
			if (url.equals(aURL)){
				return connection;
			}
		}
		throw new SQLException();
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.equals(this.url);
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
