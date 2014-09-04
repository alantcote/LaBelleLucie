package net.sf.cotelab.nio.file.monitor;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.Watchable;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchServiceExperiment implements FileSystemsMonitorObserver {
	public class FileCollector extends SimpleFileVisitor<Path> {
		private Logger log =
				Logger.getLogger(FileCollector.class.getName());
		
    	protected FileSystemsMonitorObserver fsmo;
    	
    	public FileCollector(FileSystemsMonitorObserver fsmo) {
    		super();
    		
    		this.fsmo = fsmo;
    	}
    	
    	@Override
    	public FileVisitResult preVisitDirectory(
    			Path dir, BasicFileAttributes attrs) throws IOException {
    		log.info("registering " + dir);
    		
    		fsm.addObserver(dir, fsmo);
    		
    		return FileVisitResult.CONTINUE;
    	}

    	/* (non-Javadoc)
		 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
		 */
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			if (attrs.isDirectory()) {
				log.log(Level.SEVERE, "file is directory: " + file);
				
				System.exit(1);
			}
			return super.visitFile(file, attrs);
		}

		@Override
    	public FileVisitResult visitFileFailed(Path file, IOException exc)
    			throws IOException {
    		return FileVisitResult.CONTINUE;
    	}
    }
	
	private static final Logger log =
			Logger.getLogger(WatchServiceExperiment.class.getName());

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)
			throws IOException, InterruptedException {
		new WatchServiceExperiment();
//		Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
//		long meg = 1024 * 1024;
//		
//		for (FileStore store : stores) {
//			long total, unallocated, avail;
//			String name, type;
//			
//			name = store.toString();
//			type = store.type();
//			total = store.getTotalSpace() / meg;
//			unallocated = store.getUnallocatedSpace() / meg;
//			avail = store.getUsableSpace() / meg;
//			
//			System.out.format(
//					"%,12dMB %,12dMB %,12dMB [%20s] [%20s]%n",
//					total, unallocated, avail, name, type);
//			
//		}
//		List<FileSystemProvider> fspList =
//				FileSystemProvider.installedProviders();
//		for (FileSystemProvider fsp : fspList) {
//			System.out.println(fsp.toString());
//		}
	}

	protected FileSystemsMonitor fsm;

	public WatchServiceExperiment() throws IOException {
		super();

		log.info("Creating monitor . . .");
		fsm = new FileSystemsMonitor();
		
		log.info("Registering directories with monitor . . .");
		registerDirs();

		log.info("Awaiting events . . .");
//		fsm.start();
	}

	@Override
	public void eventFired(Watchable watchable, WatchEvent<?> event) {
		log.info("*****");
		log.info("watchable = " + watchable);
		log.info("event.context = " + event.context());
		log.info("event.count = " + event.count());
		log.info("event.kind = " + event.kind());
	}
	
	protected Path[] listFileSystemRootDirs() {
    	FileSystem defaultFileSystem = FileSystems.getDefault();
    	Iterable<Path> paths = defaultFileSystem.getRootDirectories();
    	LinkedList<Path> pathList = new LinkedList<Path>();
    	Path[] rootDirs;
    	
    	for (Path p : paths) {
    		pathList.add(p);
    	}
    	
    	rootDirs = pathList.toArray(new Path[pathList.size()]);
    	
    	return rootDirs;
    }
	
    protected void registerDirs() {
    	Path[] rootDirs = listFileSystemRootDirs();

		try {
			Files.walkFileTree(rootDirs[0], new FileCollector(this));
		} catch (IOException e) {
			log.log(Level.WARNING, "Examining " + rootDirs[0], e);
		}
	}
}
