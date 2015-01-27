package net.sf.cotelab.testutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class TestUtils {
	
	@SafeVarargs
	public static <T extends Object> Collection<T> collect( T... things ) {
		Collection<T> collectionOfThings = new ArrayList<T>( things.length );
		for( T thing : things ) {
			collectionOfThings.add( thing );
		}
		return collectionOfThings;
	}

	public static <T extends Object> void assertCollectionsEqual( Collection<T> expected, Collection<T> actual) {
		if( expected != null ) {
			assertNotNull( actual );
			assertEquals( expected.size(), actual.size());
			assertTrue( expected.containsAll( actual ));
		}
		else {
			assertNull( actual );
		}
	}

	public static <K extends Object, V extends Object> void assertCollectionsEqual(Map<K, V> expected, Map<K, V> actual) {
		if( expected != null ) {
			assertNotNull( actual );
			assertEquals( expected.size(), actual.size());
			for( K key : expected.keySet()) {
				V expectedValue = expected.get( key );
				V actualValue = actual.get( key );
				assertEquals( expectedValue, actualValue );
			}
		}
		else {
			assertNull( actual );
		}
	}
	
	@SafeVarargs
	public static <T extends Object> void collectInto(Collection<T> into, T... things ) {
		for( T thing : things ) {
			into.add( thing );
		}
	}
	
	public static void deleteFile(File deleteMe) {
		if( deleteMe != null ) {
			deleteMe.delete();
		}
	}

	public static void closeStream(InputStream input) {
		if( input != null ) {
			try {
				input.close();
			} 
			catch (IOException e) {}
		}
	}

	public static void closeStream(OutputStream output) {
		if( output != null ) {
			try {
				output.close();
			}
			catch( IOException ioe ) {}
		}
	}
}
