package net.sf.cotelab.testutils;

import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class URLMatcher extends TypeSafeMatcher<URL> {
	private final String urlString;
	
	public URLMatcher( String url ) throws MalformedURLException {
		urlString = new URL( url ).toString();
	}
	
	public URLMatcher( URL url ) {
		urlString = url.toString();
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText( "a URL matching '" + urlString + "'" );
	}

	@Override
	public boolean matchesSafely(URL item) {
		return item.toString().equals( urlString );
	}
	
	@Factory
	public static TypeSafeMatcher<URL> aURLMatching( String url ) throws MalformedURLException {
		return new URLMatcher( url );
	}
	
	@Factory
	public static TypeSafeMatcher<URL> aURLMatching( URL url ) {
		return new URLMatcher( url );
	}
}
