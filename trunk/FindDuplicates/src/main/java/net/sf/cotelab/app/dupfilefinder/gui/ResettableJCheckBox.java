/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 * @author US80653H
 */
public class ResettableJCheckBox extends JCheckBox implements ResettableObject {
	private static final long serialVersionUID = 1L;
	
	protected boolean defaultSelected = false;
	protected String defaultText = "";
	protected String defaultToolTipText = "";

	/**
	 * 
	 */
	public ResettableJCheckBox() {
		super();
		
		reset();
	}

	public ResettableJCheckBox(Action a) {
		super(a);
		
		reset();
	}

	public ResettableJCheckBox(Icon icon) {
		super(icon);
		
		reset();
	}

	public ResettableJCheckBox(Icon icon, boolean selected) {
		super(icon, selected);
		
		reset();
	}

	public ResettableJCheckBox(String text) {
		super(text);
		
		reset();
	}

	public ResettableJCheckBox(String text, boolean selected) {
		super(text, selected);
		
		reset();
	}

	public ResettableJCheckBox(String text, Icon icon) {
		super(text, icon);
		
		reset();
	}

	public ResettableJCheckBox(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		
		reset();
	}

	/**
	 * @return the defaultText
	 */
	public String getDefaultText() {
		return defaultText;
	}

	/**
	 * @return the defaultToolTipText
	 */
	public String getDefaultToolTipText() {
		return defaultToolTipText;
	}

	/**
	 * @return the defaultSelected
	 */
	public boolean isDefaultSelected() {
		return defaultSelected;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableJComponent#reset()
	 */
	@Override
	public void reset() {
		setText(defaultText);
	}

	/**
	 * @param defaultSelected the defaultSelected to set
	 */
	public void setDefaultSelected(boolean defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	/**
	 * @param defaultText the defaultText to set
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	/**
	 * @param defaultToolTipText the defaultToolTipText to set
	 */
	public void setDefaultToolTipText(String defaultToolTipText) {
		this.defaultToolTipText = defaultToolTipText;
	}
}
