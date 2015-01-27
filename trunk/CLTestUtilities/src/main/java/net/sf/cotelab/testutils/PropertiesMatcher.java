package net.sf.cotelab.testutils;

import java.util.Properties;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class PropertiesMatcher extends TypeSafeMatcher<Properties> {

	private final Properties props;

	public PropertiesMatcher(Properties props) {
		this.props = props;
	}

	@Override
	public void describeTo(Description desc) {

	}

	@Override
	protected boolean matchesSafely(Properties item) {
		boolean matches;
		if( props.size() == item.size()) {
			matches = true;
			for( String name : props.stringPropertyNames()) {				
				matches = ( matches && ( item.containsKey( name ) && item.getProperty( name ).equals( props.getProperty( name ))));
			}
		}
		else {
			matches = false;
		}
		return matches;
	}

	@Factory
	public static TypeSafeMatcher<Properties> aPropertiesMatching(Properties expected) {
		return new PropertiesMatcher( expected );
	}

}
