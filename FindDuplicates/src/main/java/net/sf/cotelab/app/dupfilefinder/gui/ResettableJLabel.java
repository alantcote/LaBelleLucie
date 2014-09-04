/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author US80653H
 */
public class ResettableJLabel extends JLabel implements ResettableObject {
	private static final long serialVersionUID = 1L;
	
	protected String defaultText = "";

	/**
	 * 
	 */
	public ResettableJLabel() {
		super();
		
		initDefaults();
	}

	/**
	 * @param image
	 */
	public ResettableJLabel(Icon image) {
		super(image);
		
		initDefaults();
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public ResettableJLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		
		initDefaults();
	}

	/**
	 * @param text
	 */
	public ResettableJLabel(String text) {
		super(text);
		
		initDefaults();
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public ResettableJLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		
		initDefaults();
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public ResettableJLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		
		initDefaults();
	}

	/**
	 * @return the defaultText
	 */
	public String getDefaultText() {
		return defaultText;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableJComponent#reset()
	 */
	@Override
	public void reset() {
		setText(defaultText);
	}

	/**
	 * @param defaultText the defaultText to set
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}
	
	protected void initDefaults() {
		setDefaultText(getText());
	}
}
