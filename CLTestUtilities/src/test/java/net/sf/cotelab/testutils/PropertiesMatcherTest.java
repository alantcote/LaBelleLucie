package net.sf.cotelab.testutils;

import static org.junit.Assert.*;

import java.util.Properties;

import net.sf.cotelab.testutils.PropertiesMatcher;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

public class PropertiesMatcherTest {

	@Test
	public void create() {
		Properties props = new Properties();
		TypeSafeMatcher<Properties> matcher = new PropertiesMatcher( props );
		
		assertNotNull( matcher );
	}
	
	@Test
	public void createWithFactory() {
		Properties expected = new Properties();
		expected.setProperty( "a", "1" );
		expected.setProperty( "b", "2" );
		expected.setProperty( "c", "3" );
		
		Properties actual = new Properties();
		actual.setProperty( "a", "1" );
		actual.setProperty( "b", "2" );
		actual.setProperty( "c", "3" );
		TypeSafeMatcher<Properties> matcher = PropertiesMatcher.aPropertiesMatching( expected );
		
		assertNotNull( matcher );
		boolean matches = matcher.matches( actual );
		assertTrue( matches );
	}
	
	@Test
	public void matchesTrue() {
		Properties expected = new Properties();
		expected.setProperty( "a", "1" );
		expected.setProperty( "b", "2" );
		expected.setProperty( "c", "3" );
		
		Properties actual = new Properties();
		actual.setProperty( "a", "1" );
		actual.setProperty( "b", "2" );
		actual.setProperty( "c", "3" );
		
		PropertiesMatcher matcher = new PropertiesMatcher( expected );
		boolean matches = matcher.matchesSafely( actual );
		
		assertTrue( matches );
	}
	
	@Test
	public void matchesFalse() {
		Properties expected = new Properties();
		expected.setProperty( "a", "1" );
		expected.setProperty( "b", "2" );
		expected.setProperty( "c", "3" );
		
		Properties actual = new Properties();
		actual.setProperty( "a", "3" );
		actual.setProperty( "b", "2" );
		
		PropertiesMatcher matcher = new PropertiesMatcher( expected );
		boolean matches = matcher.matchesSafely( actual );
		
		assertFalse( matches );
	}

}
