package fileprocessing;
import java.util.ArrayList;

public class SongFile {
	private String composer; //C:
	private String key;      //K:
	private int indexNumber; //X: (Like track in a recording)
	private String title; 		//T:
	private int tempo;			//Q;
	private String meter; 		//M:
	private String length;		//L:
	private ArrayList<String> noteLines;
	private ArrayList<String> unknownLines;
	
	public static class Builder {
		private static String composer;
		private static String key;
		private static int indexNuber;
		private static String title;
		private static int tempo;
		private static String meter;
		private static String length;
		private static ArrayList<String> noteLines;
		private static ArrayList<String> unknownLines;
		
		/**
		 * initializeValues allows you to set or reset all of the Builder fields
		 * for the initialization process.  It should be run at the beginning of 
		 * all builder files.
		 */
		public static void initializeValues() {
			noteLines = new ArrayList<String>();
			unknownLines = new ArrayList<String>();
			composer = "";
			key = "";
			indexNuber = 0;
			title = "";
			tempo = 100;
			meter= "";
			length= "";
		}
		/**
		 * sets the Composer (C: in the abc file)
		 * @param val
		 */
		public static void composer(String val) {
			composer = val; 
		}
		
		/**
		 * sets the Key value (K: in the abc file)
		 * @param val
		 */
		public static void key(String val) {
			key = val; 
		}
		
		/**
		 * Sets the index number (K: from the abc file)
		 * @param val
		 */
		public static void indexNumber(int val) {
			indexNuber = val; 
		}
		
		/**
		 * Sets the title (T: from the abc file)
		 * @param val
		 */
		public static void title(String val) {
			title = val; 
		}
		
		/**
		 * Sets the index number (Q: from the abc file)
		 * @param val
		 */
		public static void tempo(int val) {
			tempo = val; 
		}
		
		/**
		 * Sets the meter (M: from the abc file)
		 * @param val
		 */
		public static void meter(String val) {
			meter = val; 
		}
		
		/**
		 * Sets the length (L: from the abc file)
		 * @param val
		 */
		public static void length(String val) {
			length = val; 
		}
		
		/**
		 * Sets the index number (K: from the abc file)
		 * @param val
		 */
		public static void addNoteLine(String val) {
			Builder.noteLines.add(val); 
		}
		/**
		 * Adds unknown lines from the file to an object
		 * to allow a location for future processing.
		 * 
		 * @param val
		 */
		public static void addUnknownLine(String val) {
			Builder.unknownLines.add(val); 
		}
		
		/**
		 * Calls the private constructor for SongFile
		 * It will set an empty invalid file if the 
		 * Builder methods are not used.  A file must
		 * have notes, a tempo, and a meter to be valid.
		 * 
		 * @return -- a SongFile object
		 * @throws InvalidSongFileException 
		 */
		public SongFile build() throws InvalidSongFileException {
			if (tempo <= 0 || noteLines.size() == 0)
				throw new InvalidSongFileException("SongFiles must have a positive tempo and notes to play.");
			return new SongFile(this);
		}
	}
	
	/**
	 * Constructor for a SongFile.  There are default values
	 * offered for tempo, but it is not valid without a 
	 * tempo and a meter.  Passes a builder object from the 
	 * Builder subclass.
	 * 
	 * @param builder
	 */
	private SongFile(Builder builder) {
		composer = builder.composer;
		key = builder.key;
		indexNumber = builder.indexNuber;
		title = builder.title;
		tempo = builder.tempo;
		meter = builder.meter;
		length = builder.length;
		noteLines = builder.noteLines;
		unknownLines = builder.unknownLines;
	}
	/**
	 * Returns a composer string from the SongFile Object.
	 * 
	 * @return -- String
	 */
	public String getComposer() {
		return composer;
	}
	/**
	 * Returns a key string from the SongFile Object.
	 * 
	 * @return -- String
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Returns an indexNumber from the SongFile Object.
	 * 
	 * @return -- int
	 */
	public int getIndexNumber() {
		return indexNumber;
	}

	/**
	 * Returns a title string from the SongFile Object.
	 * 
	 * @return -- String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the tempo from the SongFile Object.
	 * 
	 * @return -- int
	 */
	public int getTempo() {
		return tempo;
	}

	/**
	 * Returns the meter string from the SongFile Object.
	 * 
	 * @return -- String
	 */
	public String getMeter() {
		return meter;
	}

	/**
	 * Returns a length string from the SongFile Object.
	 * 
	 * @return -- String
	 */
	public String getLength() {
		return length;
	}
	/**
	 * Returns an ArrayList of note lines from the SongFile Object.
	 * 
	 * @return -- ArrayList<String>
	 */
	public ArrayList<String> getNoteLines() {
		return noteLines;
	}
	
	/**
	 * Returns one String of the note lines from the SongFile Object.
	 * 
	 * @return -- ArrayList<String>
	 */
	public String getNotesAsString() {
	    StringBuilder sb = new StringBuilder();
		for (String line : noteLines) {
			sb.append(line.trim()+ " ");
		}
		String notes = sb.toString();
		return notes.trim();
	}
}
