package net.sf.cotelab.testutils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.sf.cotelab.testutils.IntegrationTestHelper;

import org.junit.Test;

public class IntegrationTestHelperTest {
	private class Fixture extends IntegrationTestHelper {
		public Fixture() {
			super(CONFIG_RSRC);
		}
		
		public String getConfigProp(String propName) {
			return config.getProperty(propName);
		}

		@Override
		protected InputStream getResourceAsStream() {
			return propFileRsrcStream;
		}

		@Override
		protected Properties getSysProperties() {
			return sysProps;
		}
	}
	
	public static final String CONFIG_RSRC =
			"/com/xerox/testing/IntegrationTestHelperTest.properties";
	public static final String PROP1_EXPECTED = "prop1FromFile";
	public static final String PROP1_NAME = "prop1";
	public static final String PROP2_EXPECTED = "prop2FromFile";
	public static final String PROP2_NAME = "prop2";
	public static final String SYS_PROP2_EXPECTED = "prop2FromSystem";
	
	protected Properties sysProps = new Properties();
	protected InputStream propFileRsrcStream;
	
	public void prepResourceStream() throws IOException {
		Properties props = new Properties();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		props.setProperty(PROP1_NAME, PROP1_EXPECTED);
		props.setProperty(PROP2_NAME, PROP2_EXPECTED);
		
		props.store(baos, "props");
		baos.close();
		
		propFileRsrcStream = new ByteArrayInputStream(baos.toByteArray());
	}
	
	@Test
	public void testLoadConfig() throws IOException {
		Fixture fixture;
		String prop1Actual;
		String prop2Actual;
		
		sysProps = new Properties();
		
		prepResourceStream();
		fixture = new Fixture();
		fixture.loadConfig();
		prop1Actual = fixture.getConfigProp(PROP1_NAME);
		prop2Actual = fixture.getConfigProp(PROP2_NAME);
		
		assertEquals(PROP1_EXPECTED, prop1Actual);
		assertEquals(PROP2_EXPECTED, prop2Actual);
	}
	
	@Test
	public void testOverrideConfig() throws IOException {
		Fixture fixture;
		String prop1Actual;
		String prop2Actual;

		sysProps = new Properties();
		sysProps.setProperty(PROP2_NAME, SYS_PROP2_EXPECTED);

		prepResourceStream();
		fixture = new Fixture();
		fixture.loadConfig();
		fixture.overrideConfig();
		prop1Actual = fixture.getConfigProp(PROP1_NAME);
		prop2Actual = fixture.getConfigProp(PROP2_NAME);
		
		assertEquals(PROP1_EXPECTED, prop1Actual);
		assertEquals(SYS_PROP2_EXPECTED, prop2Actual);
	}
	
	@Test
	public void testRunBeforeTests() throws IOException {
		Fixture fixture;
		String prop1Actual;
		String prop2Actual;

		prepResourceStream();
		fixture = new Fixture();
		fixture.runBeforeTests();
		prop1Actual = fixture.getConfigProp(PROP1_NAME);
		prop2Actual = fixture.getConfigProp(PROP2_NAME);
		
		assertEquals(PROP1_EXPECTED, prop1Actual);
		assertEquals(PROP2_EXPECTED, prop2Actual);

		sysProps = new Properties();
		sysProps.setProperty(PROP2_NAME, SYS_PROP2_EXPECTED);

		prepResourceStream();
		fixture = new Fixture();
		fixture.runBeforeTests();
		prop1Actual = fixture.getConfigProp(PROP1_NAME);
		prop2Actual = fixture.getConfigProp(PROP2_NAME);
		
		assertEquals(PROP1_EXPECTED, prop1Actual);
		assertEquals(SYS_PROP2_EXPECTED, prop2Actual);
	}
}
