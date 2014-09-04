package net.sf.cotelab.nio.file.monitor;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileSystemsMonitor extends Thread {
	protected FileSystemsMonitorPublisher publisher =
			new FileSystemsMonitorPublisher();
	protected WatchService watchService;
	
	public FileSystemsMonitor() throws IOException {
		super("FileSystemsMonitor");
		
		watchService = FileSystems.getDefault().newWatchService();
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				WatchKey watchKey = watchService.poll(60, TimeUnit.SECONDS);
				
				if (watchKey.isValid()) {
					processWatchKey(watchKey);
				}
				
				watchKey.reset();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * @param watchable
	 * @param observer
	 * @throws IOException 
	 * @see net.sf.cotelab.nio.file.monitor.FileSystemsMonitorPublisher#addObserver(java.nio.file.Watchable, net.sf.cotelab.nio.file.monitor.FileSystemsMonitorObserver)
	 */
	public void addObserver(Watchable watchable,
			FileSystemsMonitorObserver observer) throws IOException {
		watchable.register(watchService,
				ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY, OVERFLOW);
		
		publisher.addObserver(watchable, observer);
	}

	/**
	 * @param watchKey
	 * @param event
	 * @see net.sf.cotelab.nio.file.monitor.FileSystemsMonitorPublisher#fireEvent(java.nio.file.WatchKey, java.nio.file.WatchEvent)
	 */
	public void fireEvent(WatchKey watchKey, WatchEvent<?> event) {
		publisher.fireEvent(watchKey, event);
	}

	/**
	 * @param watchable
	 * @param observer
	 * @see net.sf.cotelab.nio.file.monitor.FileSystemsMonitorPublisher#removeObserver(java.nio.file.Watchable, net.sf.cotelab.nio.file.monitor.FileSystemsMonitorObserver)
	 */
	public void removeObserver(Watchable watchable,
			FileSystemsMonitorObserver observer) {
		publisher.removeObserver(watchable, observer);
	}

	protected void processWatchKey(WatchKey watchKey) {
		List<WatchEvent<?>> events = watchKey.pollEvents();
		
		if (events != null) {
			for (WatchEvent<?> event : events) {
				fireEvent(watchKey, event);
			}
		}
	}
}
