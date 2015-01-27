package net.sf.cotelab.testutils;

import java.io.File;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class FileMatcher extends TypeSafeMatcher<File> {

	private final String path;
	
	public FileMatcher( String path ) {
		this.path = new File( path ).getAbsolutePath();
		
	}
	
	public FileMatcher( File file ) {
		this.path = file.getAbsolutePath();
	}
	
	@Override
	public void describeTo(Description desc) {
		desc.appendText("a File with the path '" + path + "'" );
	}

	@Override
	protected boolean matchesSafely(File item) {
		return item.getAbsolutePath().equals( path );
	}
	
	@Factory
	public static TypeSafeMatcher<File> aFileMatchingThePath( String path ) {
		return new FileMatcher( path );
	}
	
	@Factory
	public static TypeSafeMatcher<File> aFileMatching( File file ) {
		return new FileMatcher( file );
	}
}
