/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author US80653H
 */
public class HeapMonitorPanel extends JPanel {
	/**
	 * Periodically update the memory monitor.
	 * @author US80653H
	 */
	protected class UpdateThread extends Thread {
		protected static final long DELAY_MILLIS = 500;

		/**
		 * 
		 */
		public UpdateThread() {
			super();
			
			this.setDaemon(true);
		}

		/**
		 * @param target
		 */
		public UpdateThread(Runnable target) {
			super(target);
			
			this.setDaemon(true);
		}

		/**
		 * @param target
		 * @param name
		 */
		public UpdateThread(Runnable target, String name) {
			super(target, name);
			
			this.setDaemon(true);
		}

		/**
		 * @param name
		 */
		public UpdateThread(String name) {
			super(name);
			
			this.setDaemon(true);
		}

		/**
		 * @param group
		 * @param target
		 */
		public UpdateThread(ThreadGroup group, Runnable target) {
			super(group, target);
			
			this.setDaemon(true);
		}

		/**
		 * @param group
		 * @param target
		 * @param name
		 */
		public UpdateThread(ThreadGroup group, Runnable target, String name) {
			super(group, target, name);
			
			this.setDaemon(true);
		}

		/**
		 * @param group
		 * @param target
		 * @param name
		 * @param stackSize
		 */
		public UpdateThread(ThreadGroup group, Runnable target, String name,
				long stackSize) {
			super(group, target, name, stackSize);
			
			this.setDaemon(true);
		}

		/**
		 * @param group
		 * @param name
		 */
		public UpdateThread(ThreadGroup group, String name) {
			super(group, name);
			
			this.setDaemon(true);
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				while (true) {
					sleep(DELAY_MILLIS);

					updateHeapMonitor();
				}
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}
		}
	}

	public static long INTERVAL_MILLIS = 5000;
	public static int KILO = 1024;
	public static int MEGA = KILO * KILO;
	
	private static final long serialVersionUID = 1L;

	protected long heapMaxBytes = 0;
	protected long heapMaxMB = 0;
	protected String heapMaxMBString = "";
	protected long highWaterMark = 0;
	protected JLabel label = new JLabel("Memory (MB):");
	protected JProgressBar memProgressBar = new JProgressBar();
	protected NumberFormat nbrFmt = NumberFormat.getNumberInstance();
	protected Runtime runtime = Runtime.getRuntime();
	protected long stamp = 0;
	protected UpdateThread updateThread = new UpdateThread();

	/**
	 * 
	 */
	public HeapMonitorPanel() {
		super();
        
        heapMaxBytes = runtime.maxMemory();
        heapMaxMB = bytes2Megabytes(heapMaxBytes);
        
        nbrFmt.setGroupingUsed(true);
        heapMaxMBString = nbrFmt.format(heapMaxMB);
        
        stamp = getNowMillis() + INTERVAL_MILLIS;

		initComponents();
		
		updateThread.start();
	}
	
	public void updateHeapMonitor() {
		long nowMillis = getNowMillis();
		
		if (nowMillis > stamp) {
			long totalHeap = runtime.totalMemory();
			long freeHeap = runtime.freeMemory();
			long usedHeapBytes = totalHeap - freeHeap;
			long usedHeapMB = bytes2Megabytes(usedHeapBytes);
			String usedHeapMBString = nbrFmt.format(usedHeapMB);
			int pct = calcPct(usedHeapBytes, heapMaxBytes);
			String pctString = Integer.toString(pct) + "%";
			String displayString = usedHeapMBString + "/" + heapMaxMBString +
					" (" + pctString + ")";
			
			memProgressBar.setValue(pct);
			memProgressBar.setString(displayString);
			
			if (usedHeapMB > highWaterMark) {
				highWaterMark = usedHeapMB;
				
//				System.out.println("New high-water mark: " + usedHeapMBString +
//						"MB in use");
			}
			
			stamp = nowMillis + INTERVAL_MILLIS;
		}
	}
	
	protected long bytes2Megabytes(long bytes) {
		double bytesDouble = bytes;
		double mbDouble = bytesDouble / MEGA;
		
		return Math.round(mbDouble);
	}

    protected int calcPct(long num, long denom) {
        double numDbl = num;
        double fraction = numDbl / denom;
        double percentCompleted = fraction * 100;
        int pct = (int) Math.round(percentCompleted);

        return pct;
    }

	protected long getNowMillis() {
		Date now = new Date();
		long nowMillis = now.getTime();
		
		return nowMillis;
	}
	
	protected void initComponents() {
		setLayout(new BorderLayout());
		
        add(label, BorderLayout.WEST);
        
        memProgressBar.setForeground(Color.RED);
		memProgressBar.setIndeterminate(false);
		memProgressBar.setString("");
		memProgressBar.setStringPainted(true);
        add(memProgressBar, BorderLayout.CENTER);
	}
}
