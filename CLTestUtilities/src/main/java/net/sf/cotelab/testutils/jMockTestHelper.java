package net.sf.cotelab.testutils;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;

/**
 * A class that can be sub-classed to create a jMock enabled jUnit test.  Provides many convenience methods for 
 * creating a {@link Mockery}, checking assertions, a custom matchers for handling common cases.
 * @author usx13992
 *
 */
public class jMockTestHelper {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
	
	@Factory
	protected static TypeSafeMatcher<File> aFileWithThePath( String path ) {
		return FileMatcher.aFileMatchingThePath(path);
	}
	
	@Factory
	protected static TypeSafeMatcher<File> aFileMatching( File file ) {
		return FileMatcher.aFileMatching(file);
	}
	
	@Factory
	protected static TypeSafeMatcher<URL> aURLMatching( String url ) throws MalformedURLException {
		return URLMatcher.aURLMatching(url);
	}
	
	@Factory
	protected static TypeSafeMatcher<URL> aURLMatching( URL url ) {
		return URLMatcher.aURLMatching( url );
	}
}
