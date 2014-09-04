package net.sf.cotelab.testutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Before;

/**
 * A base class for integration test classes.
 * <p>
 * The major contribution of this class is support for configuration, via
 * property files accessible as Java "resources". This mechanism supports the
 * override of any property defined in such a property file by a like-named
 * system property.
 */
public class IntegrationTestHelper {
	protected Properties config;
	private String configResourceName;
	
	/**
	 * Construct a new object.
	 * @param configResourceName name of the Java "resource" that is the
	 * 		properties file used to load the configuration information.
	 */
	public IntegrationTestHelper(String configResourceName) {
		super();
		
		this.configResourceName = configResourceName;
	}
	
	@Before
	public void runBeforeTests() throws IOException {
		loadConfig();
		overrideConfig();
	}

	/**
	 * Return a stream from which the content of the Java resource named by
	 * <tt>configResourceName</tt> may be read.
	 * @return the stream.
	 */
	protected InputStream getResourceAsStream() {
		Class<? extends IntegrationTestHelper> clazz = getClass();
		InputStream is = clazz.getResourceAsStream(configResourceName);
		
		return is;
	}
	
	/**
	 * Get the system properties.
	 * @return the system properties.
	 */
	protected Properties getSysProperties() {
		Properties result = System.getProperties();
		
		return result;
	}
	
	/**
	 * Load the configuration from the Java resource named by
	 * <tt>configResourceName</tt>.
	 * @throws IOException
	 */
	protected void loadConfig() throws IOException {
		config = new Properties();
		
		if (configResourceName != null) {
			InputStream is = getResourceAsStream();
			
			config.load(is);
			is.close();
		}
	}
	
	/**
	 * Replace the values of all properties in <tt>config</tt> that have
	 * corresponding system properties with the corresponding system property
	 * values.
	 */
	protected void overrideConfig() {
		Properties sysProps = getSysProperties();
		Enumeration<?> propNames = config.propertyNames();
		
		while (propNames.hasMoreElements()) {
			String propName = (String) propNames.nextElement();
			String sysPropVal = sysProps.getProperty(propName);
			
			if (sysPropVal != null) {
				config.setProperty(propName, sysPropVal);
			}
		}
	}
}
