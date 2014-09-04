/**
 * 
 */
package net.sf.cotelab.nio.file.monitor;

import java.nio.file.WatchEvent;
import java.nio.file.Watchable;

/**
 * An observer of a <tt>FileSystemsMonitor</tt>.
 * @author acote
 */
public interface FileSystemsMonitorObserver {
	/**
	 * Called to notify this object that a <tt>FileSystemsMonitor</tt> has
	 * recognized an event.
	 * @param watchable the item being watched.
	 * @param event the event.
	 */
	public void eventFired(Watchable watchable, WatchEvent<?> event);
}
