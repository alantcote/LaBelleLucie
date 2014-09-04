/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;

/**
 * @author US80653H
 */
public class ResettableJTextField extends JTextField
		implements ResettableObject {
	private static final long serialVersionUID = 1L;

	protected Border defaultBorder;
	protected int defaultColumns;
	protected boolean defaultEditable;
	protected int defaultHorizontalAlignment;
	protected String defaultText;
	
	/**
	 * 
	 */
	public ResettableJTextField() {
		super();
		
		initDefaults();
	}

	/**
	 * @param doc
	 * @param text
	 * @param columns
	 */
	public ResettableJTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		
		initDefaults();
	}

	/**
	 * @param columns
	 */
	public ResettableJTextField(int columns) {
		super(columns);
		
		initDefaults();
	}

	/**
	 * @param text
	 */
	public ResettableJTextField(String text) {
		super(text);
		
		initDefaults();
	}

	/**
	 * @param text
	 * @param columns
	 */
	public ResettableJTextField(String text, int columns) {
		super(text, columns);
		
		initDefaults();
	}

	/**
	 * @return the defaultBorder
	 */
	public Border getDefaultBorder() {
		return defaultBorder;
	}

	/**
	 * @return the defaultColumns
	 */
	public int getDefaultColumns() {
		return defaultColumns;
	}

	/**
	 * @return the defaultHorizontalAlignment
	 */
	public int getDefaultHorizontalAlignment() {
		return defaultHorizontalAlignment;
	}

	/**
	 * @return the defaultText
	 */
	public String getDefaultText() {
		return defaultText;
	}

	/**
	 * @return the defaultEditable
	 */
	public boolean isDefaultEditable() {
		return defaultEditable;
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
		setBorder(getDefaultBorder());
		setColumns(getDefaultColumns());
		setEditable(isDefaultEditable());
		setHorizontalAlignment(getDefaultHorizontalAlignment());
		setText(getDefaultText());
	}

	/**
	 * @param defaultBorder the defaultBorder to set
	 */
	public void setDefaultBorder(Border defaultBorder) {
		this.defaultBorder = defaultBorder;
	}

	/**
	 * @param defaultColumns the defaultColumns to set
	 */
	public void setDefaultColumns(int defaultColumns) {
		this.defaultColumns = defaultColumns;
	}

	/**
	 * @param defaultEditable the defaultEditable to set
	 */
	public void setDefaultEditable(boolean defaultEditable) {
		this.defaultEditable = defaultEditable;
	}

	/**
	 * @param defaultHorizontalAlignment the defaultHorizontalAlignment to set
	 */
	public void setDefaultHorizontalAlignment(int defaultHorizontalAlignment) {
		this.defaultHorizontalAlignment = defaultHorizontalAlignment;
	}

	/**
	 * @param defaultText the defaultText to set
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	protected void initDefaults() {
		setDefaultBorder(getBorder());
		setDefaultColumns(getColumns());
		setDefaultEditable(isEditable());
		setDefaultHorizontalAlignment(getHorizontalAlignment());
		setDefaultText(getText());
	}
}
