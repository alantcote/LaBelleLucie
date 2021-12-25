package net.sf.cotelab.lbl;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 * Persistent application preferences
 * 
 * @author TEST
 */
public class AppPrefs {
	public static final String KEY_WINDOW_HEIGHT = "WINDOW_HEIGHT";
	public static final String KEY_WINDOW_WIDTH = "WINDOW_WIDTH";
	public static final String KEY_WINDOW_X = "WINDOW_X";
	public static final String KEY_WINDOW_Y = "WINDOW_Y";

	protected Preferences prefs = null;
	protected Stage stage = null;

	/**
	 * Constructor.
	 * 
	 * @param aStage The app's @link(Stage).
	 */
	public AppPrefs(Stage aStage) {
		stage = aStage;

		prefs = Preferences.userNodeForPackage(getClass());

		try {
			prefs.sync();
		} catch (BackingStoreException e) {
			System.err.println("AppPrefs.AppPrefs(): caught . . .");
			e.printStackTrace();
			System.err.println("AppPrefs.AppPrefs(): proceeding.");
		}

		inizGeometryPrefs();
	}

	/**
	 * Initialize the window geometry preferences.
	 * 
	 * Apply the height, width, x and y preferences to the window. If the
	 * preferences don't already exist in the persistent storage, the ones from the
	 * window are stored there.
	 * 
	 * Listeners are established to sense changes to the window geometry and reflect
	 * those changes into the persistent storage.
	 */
	protected void inizGeometryPrefs() {
		if (prefs.getDouble(KEY_WINDOW_HEIGHT, 0) == 0) {
			prefs.putDouble(KEY_WINDOW_HEIGHT, stage.getHeight());
		} else {
			stage.setHeight(prefs.getDouble(KEY_WINDOW_HEIGHT, 0));
		}

		stage.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				prefs.putDouble(KEY_WINDOW_HEIGHT, stage.getHeight());
				try {
					prefs.sync();
				} catch (BackingStoreException e) {
					System.err.println("AppPrefs.inizGeometryPrefs() height observer: caught . . .");
					e.printStackTrace();
					System.err.println("AppPrefs.inizGeometryPrefs() height observer: proceeding.");
				}
			}

		});

		if (prefs.getDouble(KEY_WINDOW_WIDTH, 0) == 0) {
			prefs.putDouble(KEY_WINDOW_WIDTH, stage.getWidth());
		} else {
			stage.setWidth(prefs.getDouble(KEY_WINDOW_WIDTH, 0));
		}

		stage.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				prefs.putDouble(KEY_WINDOW_WIDTH, stage.getWidth());
				try {
					prefs.sync();
				} catch (BackingStoreException e) {
					System.err.println("AppPrefs.inizGeometryPrefs() width observer: caught . . .");
					e.printStackTrace();
					System.err.println("AppPrefs.inizGeometryPrefs() width observer: proceeding.");
				}
			}

		});

		if (prefs.getDouble(KEY_WINDOW_X, 0) == 0) {
			prefs.putDouble(KEY_WINDOW_X, stage.getX());
		} else {
			stage.setX(prefs.getDouble(KEY_WINDOW_X, 0));
		}

		stage.xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				prefs.putDouble(KEY_WINDOW_X, stage.getX());
				try {
					prefs.sync();
				} catch (BackingStoreException e) {
					System.err.println("AppPrefs.inizGeometryPrefs() x observer: caught . . .");
					e.printStackTrace();
					System.err.println("AppPrefs.inizGeometryPrefs() x observer: proceeding.");
				}
			}

		});

		if (prefs.getDouble(KEY_WINDOW_Y, 0) == 0) {
			prefs.putDouble(KEY_WINDOW_Y, stage.getY());
		} else {
			stage.setY(prefs.getDouble(KEY_WINDOW_Y, 0));
		}

		stage.yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				prefs.putDouble(KEY_WINDOW_Y, stage.getY());
				try {
					prefs.sync();
				} catch (BackingStoreException e) {
					System.err.println("AppPrefs.inizGeometryPrefs() y observer: caught . . .");
					e.printStackTrace();
					System.err.println("AppPrefs.inizGeometryPrefs() y observer: proceeding.");
				}
			}

		});

		try {
			prefs.sync();
		} catch (BackingStoreException e) {
			System.err.println("AppPrefs.inizGeometryPrefs(): caught . . .");
			e.printStackTrace();
			System.err.println("AppPrefs.inizGeometryPrefs(): proceeding.");
		}
	}
}
