package net.sf.cotelab.app.dupfilefinder.gui;

import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;

public class ResettableJProgressBar extends JProgressBar
		implements ResettableObject {
	private static final long serialVersionUID = 1L;
	
	protected boolean defaultIndeterminate = false;
	protected String defaultString = "";
	protected boolean defaultStringPainted = false;
	protected String defaultToolTipText = "";
	protected int defaultValue = 0;

	public ResettableJProgressBar() {
		super();
		
		initDefaults();
	}

	public ResettableJProgressBar(BoundedRangeModel newModel) {
		super(newModel);
		
		initDefaults();
	}

	public ResettableJProgressBar(int orient) {
		super(orient);
		
		initDefaults();
	}

	public ResettableJProgressBar(int min, int max) {
		super(min, max);
		
		initDefaults();
	}

	public ResettableJProgressBar(int orient, int min, int max) {
		super(orient, min, max);
		
		initDefaults();
	}

	/**
	 * @return the defaultString
	 */
	public String getDefaultString() {
		return defaultString;
	}

	/**
	 * @return the defaultToolTipText
	 */
	public String getDefaultToolTipText() {
		return defaultToolTipText;
	}

	/**
	 * @return the defaultValue
	 */
	public int getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @return the defaultIndeterminate
	 */
	public boolean isDefaultIndeterminate() {
		return defaultIndeterminate;
	}

	/**
	 * @return the defaultStringPainted
	 */
	public boolean isDefaultStringPainted() {
		return defaultStringPainted;
	}

	@Override
	/**
	 * Set the values to the defaults.
	 */
	public void reset() {
		setIndeterminate(defaultIndeterminate);
		setString(defaultString);
		setStringPainted(defaultStringPainted);
		setToolTipText(defaultToolTipText);
		setValue(defaultValue);
	}

	/**
	 * @param defaultIndeterminate the defaultIndeterminate to set
	 */
	public void setDefaultIndeterminate(boolean defaultIndeterminate) {
		this.defaultIndeterminate = defaultIndeterminate;
	}

	/**
	 * @param defaultString the defaultString to set
	 */
	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}

	/**
	 * @param defaultStringPainted the defaultStringPainted to set
	 */
	public void setDefaultStringPainted(boolean defaultStringPainted) {
		this.defaultStringPainted = defaultStringPainted;
	}

	/**
	 * @param defaultToolTipText the defaultToolTipText to set
	 */
	public void setDefaultToolTipText(String defaultToolTipText) {
		this.defaultToolTipText = defaultToolTipText;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	protected void initDefaults() {
		setDefaultIndeterminate(isIndeterminate());
		setDefaultString(getString());
		setDefaultStringPainted(isStringPainted());
		setDefaultToolTipText(getToolTipText());
		setDefaultValue(getValue());
	}
}
