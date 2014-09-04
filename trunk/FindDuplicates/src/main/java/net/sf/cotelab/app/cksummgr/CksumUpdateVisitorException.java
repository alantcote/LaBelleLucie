/**
 * 
 */
package net.sf.cotelab.app.cksummgr;

import java.io.IOException;

/**
 * An <tt>IOException</tt> thrown by <tt>CksumUpdateVisitor</tt>.
 * @author acote
 */
public class CksumUpdateVisitorException extends IOException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CksumUpdateVisitorException() {
		super();
	}

	/**
	 * @param message
	 */
	public CksumUpdateVisitorException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CksumUpdateVisitorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public CksumUpdateVisitorException(Throwable cause) {
		super(cause);
	}
}
