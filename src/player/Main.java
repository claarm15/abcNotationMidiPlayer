package player;

import java.io.IOException;
import java.util.ArrayList;

import sound.*;
import fileprocessing.*;

/**
 * Main entry point of your application.
 */
public class Main {
	public static void main(String[] args) {
		String fileName = "/home/michael/workspace/MidiTester/sample_abc/scale.abc";
		ReadSongFromFile readSong = ReadSongFromFile.getInstance();
		try {
			SongFile songFile = readSong.processFile(fileName);
			System.out.println(songFile.getComposer());
			System.out.println(songFile.getKey());
			ArrayList<String> notes = songFile.getNoteLines();
			for (String noteLine : notes) {
				System.out.println("NoteLine: " + noteLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSongFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			SequencePlayer sequencePlayer = new SequencePlayer(140, 12);
//			sequencePlayer.addNote(new Pitch('C').toMidiNote(), 1, 12);
//			sequencePlayer.addNote(new Pitch('D').transpose(-1).toMidiNote(), 13, 12);
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
