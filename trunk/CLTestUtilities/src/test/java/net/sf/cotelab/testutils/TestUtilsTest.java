package net.sf.cotelab.testutils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.jmock.Expectations;
import org.junit.Test;

public class TestUtilsTest extends jMockTestHelper {

	@Test
	public void collectInto() {
		Collection<String> into = new HashSet<String>();
		
		TestUtils.collectInto( into, "abc", "def", "ghi" );
		
		assertEquals( 3, into.size());
		assertTrue( into.contains( "abc" ));
		assertTrue( into.contains( "def" ));
		assertTrue( into.contains( "ghi" ));
	}
	
	@Test
	public void assertCollectionsEqualSucceeds() {
		Collection<String> setOfStrings = new HashSet<>();
		Collection<String> listOfStrings = new ArrayList<>( 10 );
		
		for( int i=0; i<10; i++ ) {
			String insert = new Integer( i * i ).toString();
			setOfStrings.add( insert );
			listOfStrings.add( insert );
		}
		
		TestUtils.assertCollectionsEqual( setOfStrings, listOfStrings );
	}
	
	@Test
	public void assertCollectionsEqualSucceedsBothNull() {
		Collection<String> first = null;
		Collection<String> second = null;
		
		TestUtils.assertCollectionsEqual( first, second );
	}
	
//	@Test
//	public void assertCollectionsEqualFalseLengthsDiffer() {
//		Collection<String> setOfStrings = new HashSet<>();
//		Collection<String> listOfStrings = new ArrayList<>( 10 );
//		
//		for( int i=0; i<10; i++ ) {
//			String insert = new Integer( i * i ).toString();
//			setOfStrings.add( insert );
//			listOfStrings.add( insert );
//		}
//		setOfStrings.add( "abc" );
//		
//		TestUtils.assertCollectionsEqual( setOfStrings, listOfStrings );
//	}
	
//	@Test
//	public void assertCollectionsEqualFailsContentsDiffer() {
//		Collection<String> first = new ArrayList<>(3);
//		first.add( "a" );
//		first.add( "b" );
//		first.add( "c" );
//		Collection<String> second = new ArrayList<>(3);
//		second.add( "1" );
//		second.add( "2" );
//		second.add( "3" );
//		
//		TestUtils.assertCollectionsEqual( first, second );
//	}
	
	@Test
	public void collect() {
		String first = "first";
		String second = "second";
		String third = "third";
		
		Collection<String> listOfStrings = new ArrayList<String>(3);
		listOfStrings.add( first );
		listOfStrings.add( second );
		listOfStrings.add( third );
	
		Collection<String> collected = TestUtils.collect( first, second, third );
		
		TestUtils.assertCollectionsEqual( listOfStrings, collected );
	}
	
	@Test
	public void deleteFile() throws IOException {
		final File deleteMe = context.mock( File.class );
		
		context.checking( new Expectations() {{
			oneOf( deleteMe ).delete(); will( returnValue( true ));
		}});
		
		TestUtils.deleteFile( deleteMe );
	}
	
	@Test
	public void deleteNullFile() throws IOException {
		final File deleteMe = null;
		
		TestUtils.deleteFile( deleteMe );
	}
	
	@Test
	public void closeInputStream() throws IOException {
		final InputStream input = context.mock( InputStream.class );
		
		context.checking( new Expectations() {{
			oneOf( input ).close();
		}});
		
		TestUtils.closeStream( input );
	}
	
	@Test
	public void closeNullInputStream() {
		final InputStream input = null;
		
		TestUtils.closeStream( input );
	}
	
	@Test
	public void closeInputStreamSquashesException() throws IOException {
		final InputStream input = context.mock( InputStream.class );
		final IOException ioe = new IOException( "test" );
		
		context.checking( new Expectations() {{
			oneOf( input ).close(); will( throwException( ioe ));
		}});
		
		TestUtils.closeStream( input );
	}
	
	@Test
	public void closeOutputStream() throws IOException {
		final OutputStream output = context.mock( OutputStream.class );
		
		context.checking( new Expectations() {{
			oneOf( output ).close();
		}});
		
		TestUtils.closeStream( output );
	}
	
	@Test
	public void closeNullOutputStream() {
		final OutputStream output = null;
		
		TestUtils.closeStream( output );
	}
	
	@Test
	public void closeOutputStreamSquashesException() throws IOException {
		final OutputStream output = context.mock( OutputStream.class );
		final IOException ioe = new IOException( "test" );
		
		context.checking( new Expectations() {{
			oneOf( output ).close(); will( throwException( ioe ));
		}});
		
		TestUtils.closeStream( output );
	}

}
