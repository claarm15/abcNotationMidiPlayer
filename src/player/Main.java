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
		Path fileName = Paths.get("sample_abc/piece2.abc");
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
		
		
//		try {
//			SequencePlayer sequencePlayer = new SequencePlayer(140, 12);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 1, 12);
//			sequencePlayer.addNote(new Pitch('C').transpose(0).toMidiNote(), 13, 12);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 25, 9);
//			sequencePlayer.addNote(new Pitch('D').toMidiNote(), 34, 3);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 37, 12);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 49, 9);
//			sequencePlayer.addNote(new Pitch('D').toMidiNote(), 58, 3);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 61, 9);
//			sequencePlayer.addNote(new Pitch('F').toMidiNote(), 70, 3);
//			sequencePlayer.addNote(new Pitch('G').toMidiNote(), 73, 24);
//			sequencePlayer.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 97, 4);
//			sequencePlayer.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 101, 4);
//			sequencePlayer.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 105, 4);
//			sequencePlayer.addNote(new Pitch('G').toMidiNote(), 109, 4);
//			sequencePlayer.addNote(new Pitch('G').toMidiNote(), 113, 4);
//			sequencePlayer.addNote(new Pitch('G').toMidiNote(), 117, 4);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 121, 4);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 125, 4);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 129, 4);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 133, 4);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 137, 4);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 141, 4);
//			
//			sequencePlayer.addNote(new Pitch('G').toMidiNote(), 145, 9);
//			sequencePlayer.addNote(new Pitch('F').toMidiNote(), 154, 3);
//			sequencePlayer.addNote(new Pitch('E').toMidiNote(), 157, 9);
//			sequencePlayer.addNote(new Pitch('D').toMidiNote(), 166, 3);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 169, 12);
//
//            sequencePlayer.play();
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
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
