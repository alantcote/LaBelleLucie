/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author US80653H
 */
public class CheckBoxAndButtonPanel extends JPanel
		implements ResettableObject {
	public static final boolean DEFAULT_CHECKBOX_SELECTED = false;
	public static final String DEFAULT_CHECKBOX_TEXT =
			"Minimize concurrent file handles";
	public static final String DEFAULT_CHECKBOX_TOOLTIP =
			"Reduce the number of files open simultaneously by checksumming" +
				" files to be compared (reduces performance dramatically, but" +
				" improves probability of success).";
	
	private static final long serialVersionUID = 1L;

	protected StartButtonPanel button = new StartButtonPanel();
	protected ResettableJCheckBox checkBox = new ResettableJCheckBox();

	/**
	 * 
	 */
	public CheckBoxAndButtonPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public CheckBoxAndButtonPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public CheckBoxAndButtonPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}
	
	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public CheckBoxAndButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}
	
	public void addStartButtonActionListener(ActionListener listener) {
		button.addStartButtonActionListener(listener);
	}

	/**
	 * @return
	 * @see javax.swing.AbstractButton#isSelected()
	 */
	public boolean isSpecialHandling() {
		return checkBox.isSelected();
	}
	
	public void reset() {
		button.reset();
		checkBox.reset();
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StartButtonPanel#setButtonEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		button.setButtonEnabled(b);
	}

	protected void initComponents() {
		setLayout(new BorderLayout());
		
        add(button, BorderLayout.EAST);
        
        checkBox.setDefaultSelected(DEFAULT_CHECKBOX_SELECTED);
        checkBox.setDefaultText(DEFAULT_CHECKBOX_TEXT);
        checkBox.setDefaultToolTipText(DEFAULT_CHECKBOX_TOOLTIP);
        add(checkBox, BorderLayout.WEST);
	}
}
