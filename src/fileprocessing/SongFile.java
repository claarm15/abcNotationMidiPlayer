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
		public static void composer(String val) {
			composer = val; 
		}
		
		public static void key(String val) {
			key = val; 
		}
		
		public static void indexNumber(int val) {
			indexNuber = val; 
		}
		
		public static void title(String val) {
			title = val; 
		}
		
		public static void tempo(int val) {
			tempo = val; 
		}
		
		public static void meter(String val) {
			meter = val; 
		}
		
		public static void length(String val) {
			length = val; 
		}
		
		public static void addNoteLine(String val) {
			Builder.noteLines.add(val); 
		}
		
		public static void addUnknownLine(String val) {
			Builder.unknownLines.add(val); 
		}
		
		public SongFile build() {
			return new SongFile(this);
		}
	}
	
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

	public String getComposer() {
		return composer;
	}

	public String getKey() {
		return key;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public String getTitle() {
		return title;
	}

	public int getTempo() {
		return tempo;
	}

	public String getMeter() {
		return meter;
	}

	public String getLength() {
		return length;
	}

	public ArrayList<String> getNoteLines() {
		return noteLines;
	}
}
