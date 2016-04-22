package player;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import sound.*;
import fileprocessing.*;

/**
 * Main entry point of your application.
 */
public class Main {
	public static void main(String[] args) {
//		Path fileName = Paths.get("sample_abc/scale.abc");
//		Path fileName = Paths.get("sample_abc/sample1.abc");
//		Path fileName = Paths.get("sample_abc/sample2.abc");
//		Path fileName = Paths.get("sample_abc/sample3.abc");
//		Path fileName = Paths.get("sample_abc/piece1.abc");
//		Path fileName = Paths.get("sample_abc/piece2.abc");
//		Path fileName = Paths.get("sample_abc/paddy.abc");
//		Path fileName = Paths.get("sample_abc/fur_elise.abc");
//		Path fileName = Paths.get("sample_abc/little_night_music.abc");
		Path fileName = Paths.get("sample_abc/invention.abc");
//		Path fileName = Paths.get("sample_abc/debussy.abc");
//		Path fileName = Paths.get("sample_abc/testing.abc");
		ReadSongFromFile readSong = ReadSongFromFile.getInstance();
		SequenceLoader sl; 
		try {
			SongFile songFile = readSong.processFile(fileName);
			sl = new SequenceLoader(songFile.getTempo(),  songFile.getNoteLines());
			SequencePlayer sp = sl.loadSequence();
			sp.play();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSongFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Plays the input file using Java MIDI API and displays header information
	 * to the standard output stream.
	 * 
	 * <p>
	 * Your code <b>should not</b> exit the application abnormally using
	 * System.exit()
	 * </p>
	 * 
	 * @param file
	 *            the name of input abc file
	 */
	public static void play(String file) {
		// YOUR CODE HERE

	}
}
