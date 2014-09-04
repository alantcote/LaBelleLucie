/**
 * $Id: PhaseStats.java,v 1.1 2008/04/16 13:06:48 acote Exp $
 * $Log: PhaseStats.java,v $
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.4  2008/04/15 17:12:12  acote
 * Cleaned up some javadoc comments.
 *
 * Revision 1.3  2008/04/14 12:02:22  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.4  2008/04/11 20:01:11  acote
 * Checkpoint.
 *
 * Revision 1.3  2008/04/11 18:10:13  acote
 * Checkpoint.
 *
 * Revision 1.2  2008/04/10 19:50:32  acote
 * Still evolving.
 *
 * Revision 1.1  2008/04/10 15:28:34  acote
 * Creating a place for advanced development.
 *
 * Revision 1.1  2008/04/10 14:21:50  acote
 * Continuing evolution of a better-structured program.
 *
 */
package net.sf.cotelab.app.dupfilefinder.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Statistics for a duplicate file finder phase.
 * 
 * @author cote
 */
public class PhaseStats {

    public static final String PN_CANCELLED = "cancelled";
    public static final String PN_DIRECTORIES = "directories";
    public static final String PN_GROUPS = "groups";
    public static final String PN_MEANINGFUL_PROGRESS = "meaningfulProgress";
    public static final String PN_NBR_BYTES_CONSIDERED = "nbrBytesConsidered";
    public static final String PN_NBR_FILE_GROUPS_CONSIDERED =
            "nbrFileGroupsConsidered";
    public static final String PN_PERCENT_COMPLETE = "percentComplete";
    public static final String PN_PHASE_ENTRY_STAMP = "phaseEntryStamp";
    public static final String PN_PHASE_EXIT_STAMP = "phaseExitStamp";
    public static final String PN_PHASE_PROGRESS_MESSAGE = "progressMessage";
    public static final String PN_PRESENTATION_NAME = "presentationName";
    public static final String PN_REGULAR_FILES = "regularFiles";
    public static final String PN_UNIQUE_FILES = "uniqueFiles";
    public static final String PN_UNREADABLE_FILES = "unreadableFiles";
    /**
     * Support for property change listeners.
     */
    protected PropertyChangeSupport propertyChangeSupport =
            new PropertyChangeSupport(this);
    /**
     * Directories.
     * <p>
     * This is a bound property.
     */
    private List<File> directoriesIdentified = new LinkedList<File>();
    /**
     * The file groups identified in this phase.
     * <p>
     * This is a bound property.
     */
    private List<Collection<File>> groups =
            new LinkedList<Collection<File>>();
    /**
     * The truth-value of the assertion, "The value of <tt>percentComplete</tt>
     * should be considered meaningful".
     * <p>
     * This could be useful in calculating a parameter to be passed to
     * <tt>JProgressBar.isIndeterminate()</tt>.
     * <p>
     * This is a bound property.
     */
    private boolean meaningfulProgress = false;
    /**
     * The number of input bytes accounted for in this phase.
     * <p>
     * This is a bound property.
     */
    private long nbrBytesConsidered = 0;
    /**
     * The number of file groups accounted for in this phase.
     * <p>
     * This is a bound property.
     */
    private long nbrFileGroupsConsidered = 0;
    /**
     * An indication of the degree to which the phase has completed its work.
     * <p>
     * This is a bound property.
     */
    private int percentComplete = 0;
    /**
     * Timestamp on entry to <tt>doInBackground()</tt>.
     * <p>
     * This is a bound property.
     */
    private Date phaseEntryStamp = new Date();
    /**
     * Timestamp on exit from <tt>doInBackground()</tt>.
     * <p>
     * This is a bound property.
     */
    private Date phaseExitStamp = new Date();
    /**
     * The presentation name of this phase.
     * <p>
     * This is a bound property.
     */
    private String presentationName = "New Phase";
    /**
     * A progress message.
     * <p>
     * This is a bound property.
     */
    private String progressMessage = "Phase constructed.";
    /**
     * Regular files.
     */
    private Collection<File> regularFiles = new LinkedList<File>();
    /**
     * Unique files.
     * <p>
     * This is a bound property.
     */
    private List<File> uniqueFilesIdentified = new LinkedList<File>();
    /**
     * Unreadable files.
     * <p>
     * This is a bound property.
     */
    private List<File> unreadableFilesIdentified = new LinkedList<File>();

    /**
     * Construct a new object.
     */
    public PhaseStats(String presentationName) {
        super();

        setPresentationName(presentationName);
    }

    /**
     * Add a group to <tt>groups</tt>.
     * <p>
     * Calls <tt>reportProgress()</tt> to alert interested parties.
     * 
     * @param group
     *            the group to add.
     */
    public synchronized void addGroup(Collection<File> group) {
        int oldSize = groups.size();

        groups.add(group);
        fireIndexedPropertyChange(PN_GROUPS, oldSize, null, group);
    }

    /**
     * @param listener
     * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * @param propertyName
     * @param listener
     * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Add a regular file.
     * 
     * @param regFile
     *            the group to add.
     */
    public synchronized void addRegularFile(File regFile) {
        int oldSize = regularFiles.size();

        regularFiles.add(regFile);
        fireIndexedPropertyChange(PN_REGULAR_FILES, oldSize, null, regFile);
    }

    /**
     * @param propertyName
     * @param index
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, boolean, boolean)
     */
    public void fireIndexedPropertyChange(String propertyName, int index,
            boolean oldValue, boolean newValue) {
        propertyChangeSupport.fireIndexedPropertyChange(propertyName, index,
                oldValue, newValue);
    }

    /**
     * @param propertyName
     * @param index
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, int, int)
     */
    public void fireIndexedPropertyChange(String propertyName, int index,
            int oldValue, int newValue) {
        propertyChangeSupport.fireIndexedPropertyChange(propertyName, index,
                oldValue, newValue);
    }

    /**
     * @param propertyName
     * @param index
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, java.lang.Object, java.lang.Object)
     */
    public void fireIndexedPropertyChange(String propertyName, int index,
            Object oldValue, Object newValue) {
        propertyChangeSupport.fireIndexedPropertyChange(propertyName, index,
                oldValue, newValue);
    }

    /**
     * @param evt
     * @see java.beans.PropertyChangeSupport#firePropertyChange(java.beans.PropertyChangeEvent)
     */
    public void firePropertyChange(PropertyChangeEvent evt) {
        propertyChangeSupport.firePropertyChange(evt);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, boolean, boolean)
     */
    public void firePropertyChange(String propertyName, boolean oldValue,
            boolean newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue,
                newValue);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, int, int)
     */
    public void firePropertyChange(String propertyName, int oldValue,
            int newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue,
                newValue);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
     */
    public void firePropertyChange(String propertyName, Object oldValue,
            Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue,
                newValue);
    }

    /**
     * @return the directoriesIdentified
     */
    public synchronized List<File> getDirectoriesIdentified() {
        return directoriesIdentified;
    }

    /**
     * @return the groups
     */
    public synchronized List<Collection<File>> getGroups() {
        return groups;
    }

    /**
     * @return the nbrBytesConsidered
     */
    public synchronized long getNbrBytesConsidered() {
        return nbrBytesConsidered;
    }

    /**
     * @return the nbrFileGroupsConsidered
     */
    public synchronized long getNbrFileGroupsConsidered() {
        return nbrFileGroupsConsidered;
    }

    /**
     * @return the percentComplete
     */
    public synchronized int getPercentComplete() {
        return percentComplete;
    }

    /**
     * @return the phaseEntryStamp
     */
    public synchronized Date getPhaseEntryStamp() {
        return phaseEntryStamp;
    }

    /**
     * @return the phaseExitStamp
     */
    public synchronized Date getPhaseExitStamp() {
        return phaseExitStamp;
    }

    /**
     * @return the presentationName
     */
    public String getPresentationName() {
        return presentationName;
    }

    /**
     * @return the progressMessage
     */
    public synchronized String getProgressMessage() {
        return progressMessage;
    }

    /**
     * @return the property change listeners.
     * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    /**
     * @param propertyName
     * @return the property change listeners listening for the named property.
     * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(java.lang.String)
     */
    public PropertyChangeListener[] getPropertyChangeListeners(
            String propertyName) {
        return propertyChangeSupport.getPropertyChangeListeners(propertyName);
    }

//    /**
//     * @return the propertyChangeSupport
//     */
//    public PropertyChangeSupport getPropertyChangeSupport() {
//        return propertyChangeSupport;
//    }

    /**
     * @return the regularFiles
     */
    public synchronized Collection<File> getRegularFiles() {
        return regularFiles;
    }

    /**
     * @return the uniqueFilesIdentified
     */
    public synchronized List<File> getUniqueFilesIdentified() {
        return uniqueFilesIdentified;
    }

    /**
     * @return the unreadableFilesIdentified
     */
    public synchronized List<File> getUnreadableFilesIdentified() {
        return unreadableFilesIdentified;
    }

    /**
     * @param propertyName
     * @return what <tt>propertyChangeSupport.hasListeners()</tt> does.
     * @see java.beans.PropertyChangeSupport#hasListeners(java.lang.String)
     */
    public boolean hasListeners(String propertyName) {
        return propertyChangeSupport.hasListeners(propertyName);
    }

    /**
     * Identify the parameter as a directory.
     * 
     * @param file
     *            the directory.
     */
    public synchronized void identifyDirectory(File file) {
        int oldSize = directoriesIdentified.size();

        directoriesIdentified.add(file);
        fireIndexedPropertyChange(
                PN_DIRECTORIES, oldSize, null, file);
    }

    /**
     * Identify the parameter as a unique file.
     * 
     * @param file
     *            the unique file.
     */
    public synchronized void identifyUniqueFile(File file) {
        int oldSize = uniqueFilesIdentified.size();

        uniqueFilesIdentified.add(file);
        fireIndexedPropertyChange(
                PN_UNIQUE_FILES, oldSize, null, file);
    }

    /**
     * Identify the parameter as an unreadable file.
     * 
     * @param file
     *            the unreadable file.
     */
    public synchronized void identifyUnreadableFile(File file) {
        int oldSize = unreadableFilesIdentified.size();

        unreadableFilesIdentified.add(file);
        fireIndexedPropertyChange(
                PN_UNREADABLE_FILES, oldSize, null, file);
    }

    /**
     * @return the meaningfulProgress
     */
    public synchronized boolean isMeaningfulProgress() {
        return meaningfulProgress;
    }

    /**
     * Propagate "global" fields from another object.
     * <p>
     * The "global" fields propagated are <tt>directoriesIdentified</tt>,
     * <tt>regularFiles</tt>, <tt>uniqueFilesIdentified</tt>, and
     * <tt>unreadableFilesIdentified</tt>.
     */
    public synchronized void propagateGlobalsFrom(PhaseStats other) {
        directoriesIdentified.addAll(other.directoriesIdentified);
        regularFiles.addAll(other.regularFiles);
        uniqueFilesIdentified.addAll(other.uniqueFilesIdentified);
        unreadableFilesIdentified.addAll(other.unreadableFilesIdentified);
    }

    /**
     * @param listener
     * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * @param propertyName
     * @param listener
     * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName,
                listener);
    }

    /**
     * @param directoriesIdentified
     *            the directoriesIdentified to set
     */
    public synchronized void setDirectoriesIdentified(List<File> directoriesIdentified) {
        firePropertyChange(PN_DIRECTORIES,
                this.directoriesIdentified, directoriesIdentified);
        this.directoriesIdentified = directoriesIdentified;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public synchronized void setGroups(List<Collection<File>> groups) {
        firePropertyChange(PN_GROUPS, this.groups, groups);
        this.groups = groups;
    }

    /**
     * @param meaningfulProgress
     *            the meaningfulProgress to set
     */
    public synchronized void setMeaningfulProgress(boolean meaningfulProgress) {
        firePropertyChange(PN_MEANINGFUL_PROGRESS, this.meaningfulProgress,
                meaningfulProgress);
        this.meaningfulProgress = meaningfulProgress;
    }

    /**
     * @param nbrBytesConsidered
     *            the nbrBytesConsidered to set
     */
    public synchronized void setNbrBytesConsidered(long nbrBytesConsidered) {
        firePropertyChange(PN_NBR_BYTES_CONSIDERED, this.nbrBytesConsidered,
                nbrBytesConsidered);
        this.nbrBytesConsidered = nbrBytesConsidered;
    }

    /**
     * @param nbrFileGroupsConsidered
     *            the nbrFileGroupsConsidered to set
     */
    public synchronized void setNbrFileGroupsConsidered(long nbrFileGroupsConsidered) {
        firePropertyChange(PN_NBR_FILE_GROUPS_CONSIDERED,
                this.nbrFileGroupsConsidered, nbrFileGroupsConsidered);
        this.nbrFileGroupsConsidered = nbrFileGroupsConsidered;
    }

    /**
     * @param percentComplete
     *            the percentComplete to set
     */
    public synchronized void setPercentComplete(int percentComplete) {
        firePropertyChange(PN_PERCENT_COMPLETE, this.percentComplete,
                percentComplete);
        this.percentComplete = percentComplete;
    }

    /**
     * @param phaseEntryStamp
     *            the entryStamp to set
     */
    public synchronized void setPhaseEntryStamp(Date phaseEntryStamp) {
        firePropertyChange(
                PN_PHASE_ENTRY_STAMP, this.phaseEntryStamp, phaseEntryStamp);
        this.phaseEntryStamp = phaseEntryStamp;
    }

    /**
     * @param phaseExitStamp
     *            the exitStamp to set
     */
    public synchronized void setPhaseExitStamp(Date phaseExitStamp) {
        firePropertyChange(
                PN_PHASE_EXIT_STAMP, this.phaseExitStamp, phaseExitStamp);
        this.phaseExitStamp = phaseExitStamp;
    }

    /**
     * @param presentationName
     *            the presentationName to set
     */
    public void setPresentationName(String presentationName) {
        firePropertyChange(PN_PRESENTATION_NAME, this.presentationName,
                presentationName);
        this.presentationName = presentationName;
    }

    /**
     * @param progressMessage
     *            the progressMessage to set
     */
    public synchronized void setProgressMessage(String progressMessage) {
        firePropertyChange(PN_PHASE_PROGRESS_MESSAGE, this.progressMessage,
                progressMessage);
        this.progressMessage = progressMessage;
    }

//    /**
//     * @param propertyChangeSupport the propertyChangeSupport to set
//     */
//    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
//        this.propertyChangeSupport = propertyChangeSupport;
//    }

    /**
     * @param regularFiles the regularFiles to set
     */
    public synchronized void setRegularFiles(Collection<File> regularFiles) {
        firePropertyChange(PN_REGULAR_FILES, this.regularFiles, regularFiles);
        this.regularFiles = regularFiles;
    }

    /**
     * @param uniqueFilesIdentified
     *            the uniqueFilesIdentified to set
     */
    public synchronized void setUniqueFilesIdentified(List<File> uniqueFilesIdentified) {
        firePropertyChange(PN_UNIQUE_FILES,
                this.uniqueFilesIdentified, uniqueFilesIdentified);
        this.uniqueFilesIdentified = uniqueFilesIdentified;
    }

    /**
     * @param unreadableFilesIdentified
     *            the unreadableFilesIdentified to set
     */
    public synchronized void setUnreadableFilesIdentified(
            List<File> unreadableFilesIdentified) {
        firePropertyChange(PN_UNREADABLE_FILES,
                this.unreadableFilesIdentified, unreadableFilesIdentified);
        this.unreadableFilesIdentified = unreadableFilesIdentified;
    }
}
