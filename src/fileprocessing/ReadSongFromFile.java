package fileprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author Michael Claar
 * for SENG 6245 taught by Dr. Junhua Ding
 * 
 * Part of abc notation midi player.
 *
 */
public class ReadSongFromFile {
	private SongFile songFile;
	private String fileName;
	private static ReadSongFromFile readSongFromFile = null;
	/**
	 * Generates an instance of this class.  Due to private constructor, this is the only
	 * way to instantiate the class.
	 * 
	 * Using the Singleton pattern because there is never a need for multiple implementation.
	 * 
	 * @return
	 */
	public static ReadSongFromFile getInstance() {
		if (readSongFromFile == null) {
			readSongFromFile = new ReadSongFromFile();
		}
		return readSongFromFile;
	}

	/**
	 * Public interface to process a file with ABC notation and convert it to a class to be used
	 * throughout the rest of the program.  
	 * 
	 * Requires a full path name.
	 * 
	 * @param fileName   -- fileName includes the entire path.
	 * @return SongFile object.
	 * @throws IOException  -- due to file handling
	 * @throws InvalidSongFileException  -- custom exception to allow us to troubleshoot a bad file.
	 */
	public SongFile processFile(String fileName) throws IOException, InvalidSongFileException {
		SongFile.Builder.initializeValues();
		File file;
		file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			processLine(line);
		}
		fileReader.close();

		return new SongFile.Builder().build();
	}

	/**
	 * processLine sorts each line from the text file and matches it to the
	 * correct category to add to the SongFile object.
	 * 
	 * @param line
	 * @throws InvalidSongFileException
	 */
	private void processLine(String line) throws InvalidSongFileException {
		if (line.matches("^[CKLMQTXV]:.*$")) {
			AddCategoryToSongFile(line);
			AddSongLineToFile(line);
		} else if (line.matches("[a-gzA-G0-9,:| \\[\\]\\/_^]*")) {
			AddSongLineToFile(line);
		} else {
			AddNonMatchedLine(line);
		}
	}

	/**
	 * This saves unmatched line from the file to help with troubleshooting or to know 
	 * which areas of the abc notation can be further implemented.
	 * 
	 * @param line
	 */
	private void AddNonMatchedLine(String line) {
		SongFile.Builder.addUnknownLine(line);
	}

	/**
	 * Adds a line from the abc notation to the lines of playable notes.
	 * It ends up in an ArrayList<String> when done.
	 * 
	 * 
	 * @param line 
	 */
	private void AddSongLineToFile(String line) {
		SongFile.Builder.addNoteLine(line);
	}

	/**
	 * Any pattern matching a alphabetic letter then a colon gets sorted to this
	 * method.  If it is one of the main categories, it gets saved in the SongFile.
	 * 
	 * Right now things like V:1 will be ignored and not saved. If there is a reason
	 * to add it later, this would be the interface to do it through.
	 * 
	 * @param line
	 * @throws InvalidSongFileException
	 */
	private void AddCategoryToSongFile(String line)
			throws InvalidSongFileException {
		// TODO Auto-generated method stub
		String lcLine = line.toLowerCase();
		char firstLetter = lcLine.charAt(0);
		int intval;
		switch (firstLetter) {
		case 'c':
			SongFile.Builder.composer(line.substring(2).trim());
			break;
		case 'k':
			SongFile.Builder.key(line.substring(2).trim());
			break;
		case 'l':
			SongFile.Builder.length(line.substring(2).trim());
			break;
		case 'm':
			SongFile.Builder.meter(line.substring(2).trim());
			break;
		case 'q':
			intval = convertToInt(line.substring(2).trim());
			SongFile.Builder.tempo(intval);
			break;
		case 't':
			SongFile.Builder.title(line.substring(2).trim());
			break;
		case 'x':
			intval = convertToInt(line.substring(2).trim());
			SongFile.Builder.indexNumber(intval);
			break;
		default:

			break;
		}
	}

	/**
	 * Categories from the file that need to be converted to numbers (like tempo)
	 * will get sent here.  The InvalidSongFileException is thrown from this 
	 * location.  
	 * 
	 * @param substring
	 * @return
	 * @throws InvalidSongFileException
	 */
	private int convertToInt(String substring) throws InvalidSongFileException {
		int result;
		try {
			result = Integer.parseInt(substring);
		} catch (Exception e) {
			// TODO: handle exception
			throw new InvalidSongFileException(
					"Meter and tempo must be positive integer values.\n" +
			        substring + " did not convert corectly.");
		}
		return result;
	}
}
