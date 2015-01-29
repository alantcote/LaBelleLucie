package net.sf.cotelab.playingcards.javafx;

import static org.junit.Assert.*;

import java.util.Map;

import javafx.scene.image.Image;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class ImageFactoryTest extends jMockTestHelper {
	@Test
	public void testFlush() {
		@SuppressWarnings("unchecked")
		final Map<String, Image> mockCache =
				(Map<String, Image>) context.mock(Map.class);
		final ImageFactory mockImageFactory = context.mock(ImageFactory.class);
		
		context.checking( new Expectations() {{
			oneOf(mockImageFactory).newMap_String_Image();
			will(returnValue(mockCache));
			
			oneOf(mockCache).clear();
		}});
		
		ImageFactory fixture = new ImageFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newMap_String_Image()
			 */
			@Override
			protected Map<String, Image> newMap_String_Image() {
				return mockImageFactory.newMap_String_Image();
			}
		};
		
		fixture.flush();
	}

	@Test
	public void testGetImage() {
		final String url = "url";
		final Image mockImage = context.mock(Image.class);
		@SuppressWarnings("unchecked")
		final Map<String, Image> mockCache =
				(Map<String, Image>) context.mock(Map.class);
		final ImageFactory mockImageFactory = context.mock(ImageFactory.class);
		
		context.checking( new Expectations() {{
			oneOf(mockImageFactory).newMap_String_Image();
			will(returnValue(mockCache));
			
			oneOf(mockCache).get(with(url));
			will(returnValue((Image) null));
			
			oneOf(mockImageFactory).loadImage(with(url));
			will(returnValue(mockImage));
			
			oneOf(mockCache).put(with(url), with(mockImage));
			
			oneOf(mockCache).get(with(url));
			will(returnValue(mockImage));
		}});
		
		ImageFactory fixture = new ImageFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#loadImage(java.lang.String)
			 */
			@Override
			protected Image loadImage(String url) {
				return mockImageFactory.loadImage(url);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newMap_String_Image()
			 */
			@Override
			protected Map<String, Image> newMap_String_Image() {
				return mockImageFactory.newMap_String_Image();
			}
		};
		
		assertEquals(mockImage, fixture.getImage(url));
		assertEquals(mockImage, fixture.getImage(url));
	}

	@Test
	public void testImageFactory() {
		double expectedMaxDim = ImageFactory.DEFAULT_MAX_DIM;
		@SuppressWarnings("unchecked")
		final Map<String, Image> mockCache =
				(Map<String, Image>) context.mock(Map.class);
		final ImageFactory mockImageFactory = context.mock(ImageFactory.class);
		
		context.checking( new Expectations() {{
			oneOf(mockImageFactory).newMap_String_Image();
			will(returnValue(mockCache));
		}});
		
		ImageFactory fixture = new ImageFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newMap_String_Image()
			 */
			@Override
			protected Map<String, Image> newMap_String_Image() {
				return mockImageFactory.newMap_String_Image();
			}
		};
		
		assertEquals(mockCache, fixture.cache);
		assertEquals(expectedMaxDim, fixture.maxDim, Double.MIN_VALUE);
	}

	@Test
	public void testImageFactoryDouble() {
		double expectedMaxDim = 117.06;
		@SuppressWarnings("unchecked")
		final Map<String, Image> mockCache =
				(Map<String, Image>) context.mock(Map.class);
		final ImageFactory mockImageFactory = context.mock(ImageFactory.class);
		
		context.checking( new Expectations() {{
			oneOf(mockImageFactory).newMap_String_Image();
			will(returnValue(mockCache));
		}});
		
		ImageFactory fixture = new ImageFactory(expectedMaxDim) {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newMap_String_Image()
			 */
			@Override
			protected Map<String, Image> newMap_String_Image() {
				return mockImageFactory.newMap_String_Image();
			}
		};
		
		assertEquals(mockCache, fixture.cache);
		assertEquals(expectedMaxDim, fixture.maxDim, Double.MIN_VALUE);
	}

	@Test
	public void testLoadImage() {
		final String url = "url";
		final Image mockImage = context.mock(Image.class);
		@SuppressWarnings("unchecked")
		final Map<String, Image> mockCache =
				(Map<String, Image>) context.mock(Map.class);
		final ImageFactory mockImageFactory = context.mock(ImageFactory.class);
		
		context.checking( new Expectations() {{
			oneOf(mockImageFactory).newMap_String_Image();
			will(returnValue(mockCache));
			
			oneOf(mockImageFactory).newImage(
					with(url), with(ImageFactory.DEFAULT_MAX_DIM),
					with(ImageFactory.DEFAULT_MAX_DIM), with(true), with(true));
			will(returnValue(mockImage));
		}});
		
		ImageFactory fixture = new ImageFactory() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newImage(java.lang.String, double, double, boolean, boolean)
			 */
			@Override
			protected Image newImage(String url, double requestedWidth,
					double requestedHeight, boolean preserveRatio,
					boolean smooth) {
				return mockImageFactory.newImage(url, requestedWidth,
						requestedHeight, preserveRatio, smooth);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.playingcards.javafx.ImageFactory#newMap_String_Image()
			 */
			@Override
			protected Map<String, Image> newMap_String_Image() {
				return mockImageFactory.newMap_String_Image();
			}
		};
		
		assertEquals(mockImage, fixture.loadImage(url));
	}

	@Test
	public void testNewImage() {
		/**
		 * This method is a wrapper around a constructor, and needs no testing.
		 */
	}

	@Test
	public void testNewMap_String_Image() {
		/**
		 * This method is a wrapper around a constructor, and needs no testing.
		 */
	}
}
