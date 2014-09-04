/**
 * 
 */
package net.sf.cotelab.nio.file.monitor;

import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.Watchable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A publisher of <tt>FileSystemsMonitor</tt>-sourced events.
 * @author acote
 */
public class FileSystemsMonitorPublisher {
	protected HashMap<Watchable, LinkedList<FileSystemsMonitorObserver>> map =
			new HashMap<Watchable, LinkedList<FileSystemsMonitorObserver>>();
	
	/**
	 * Construct a new object.
	 */
	public FileSystemsMonitorPublisher() {
		super();
	}
	
	public synchronized void addObserver(
			Watchable watchable, FileSystemsMonitorObserver observer) {
		LinkedList<FileSystemsMonitorObserver> observers = map.get(watchable);
		
		if (observers == null) {
			observers = new LinkedList<FileSystemsMonitorObserver>();
			
			map.put(watchable, observers);
		}
		
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	public synchronized void fireEvent(
			WatchKey watchKey, WatchEvent<?> event) {
		Watchable watchable = watchKey.watchable();
		LinkedList<FileSystemsMonitorObserver> observers = map.get(watchable);
		
		if (observers != null) {
			for (FileSystemsMonitorObserver observer : observers) {
				observer.eventFired(watchable, event);
			}
		}
	}
	
	public synchronized void removeObserver(
			Watchable watchable, FileSystemsMonitorObserver observer) {
		LinkedList<FileSystemsMonitorObserver> observers = map.get(watchable);
		
		if (observers != null) {
			while (observers.contains(observer)) {
				observers.remove(observer);
			}
			
			if (observers.isEmpty()) {
				map.remove(watchable);
			}
		}
	}
}
