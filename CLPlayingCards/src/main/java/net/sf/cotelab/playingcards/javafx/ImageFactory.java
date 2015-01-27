package net.sf.cotelab.playingcards.javafx;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageFactory {
	public static final double DEFAULT_MAX_DIM = 100;
	
	protected Map<String, Image> cache;
	protected double maxDim;

	/**
	 * Construct a new object with default settings.
	 */
	public ImageFactory() {
		this(DEFAULT_MAX_DIM);
	}

	/**
	 * Construct a new object with a specified maximum dimension.
	 * While the new object will preserve aspect ratio, the images produced will
	 * be limited in height and width to the specified maximum dimension.
	 * @param maxDim the maximum dimension.
	 */
	public ImageFactory(double maxDim) {
		super();
		
		this.maxDim = maxDim;
		
		cache = newCache();
	}
	
	/**
	 * Get an image from the given URL.
	 * @param url the image source.
	 * @return the image (<tt>null</tt> if the image cannot be retrieved).
	 */
	public Image getImage(String url) {
		Image image = cache.get(url);
		
		if (image == null) {
			image = loadImage(url);
			
			if (image != null) {
				cache.put(url, image);
			}
		}
		
		return image;
	}
	
	/**
	 * Flush the cache.
	 */
	public void flush() {
		cache.clear();
	}

	protected Image loadImage(String url) {
		Image image = new Image(url, maxDim, maxDim, true, true);
		
		return image;
	}
	
	protected Map<String, Image> newCache() {
		return new HashMap<>();
	}
}
