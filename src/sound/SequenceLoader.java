package sound;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class SequenceLoader {
	private int startTick;
	private SequencePlayer song;
	private int tempo;
	private ArrayList<String> songLines;
	private int notesPerBeat;
	private int v1RepeatPoint;
	private int lastNoteDuration;
	
	public SequenceLoader(int tempo, ArrayList<String> songLines) throws MidiUnavailableException, InvalidMidiDataException {
		startTick = 1;
		notesPerBeat = 1;
		song = new SequencePlayer(tempo, 12);
		this.tempo = tempo;
		this.songLines = songLines;
		v1RepeatPoint = startTick;
		lastNoteDuration = 0;
	}

	public SequencePlayer loadSequence() {
		for (String songLine : songLines) {

			if (songLine.matches("^[CKLMQTXV]:.*$")) {
				if (songLine.matches("^[vV]: *1")) {
					v1RepeatPoint = startTick;
				}
				else if (songLine.matches("^[vV]:.*")) startTick = v1RepeatPoint;
			} else if (songLine.matches("[a-gzA-G0-9,:| \\[\\]\\/_^\\(']*")) {
				processNotesInLine(songLine);
			} else {
				/**
				 * Right now no functionality is needed for comment lines from
				 * the program. This is where it would be added.
				 */
			}
		}
		return this.song;
	}

	private void processNotesInLine(String line) {
		String[] notesSplitByBeat = line.split(" ");
		for (int i = 0; i < notesSplitByBeat.length; i++) {
			String beat = notesSplitByBeat[i];
			processNotesInBeat(beat);

		}

	}

	private void processNotesInBeat(String beat) {
		boolean in_chord = false;
		ArrayList<String> notes = splitBeatIntoNotes(beat);
		for (String noteCode : notes) {
			System.out.println("NOTE CODE: " + noteCode);
			//if in a chord, startTick stays the same...
			if (noteCode.matches("\\[")) in_chord = true;
			if (noteCode.matches("\\]")) {
				in_chord = false;
				startTick += lastNoteDuration;
			}
			
			if (noteCode.matches(".*[A-Ga-g].*")) {
				Note note = new Note(noteCode);
				lastNoteDuration = note.getDuration();
				int pitch = note.getPitch();
				if (!in_chord) startTick += lastNoteDuration;
				String noteLetter = note.getNoteLetter() + "";
				if (!noteLetter.matches("[zZ]"))song.addNote(pitch, startTick, lastNoteDuration);
				System.out.println(note.getNoteLetter() + " pitch: " + note.getPitch() + "\tOctave:  " + note.getOctave() + "\t NumTicks: "
						+ lastNoteDuration + "\tstartTick: " + startTick );

			}
		}
	}

	private ArrayList<String> splitBeatIntoNotes(String wholeBeat) {
		// so I can break into parts..
		String beat = wholeBeat;
		ArrayList<String> notes = new ArrayList<>();
		int currentPosition = 0;
		int beatLength = beat.length();

		while (currentPosition < beatLength) {
			boolean somethingMatched = false;
			boolean is_note = true;
			String currentNote = "";
			//If not a note capture symbols together, and separate from the notes...
			while (currentPosition < beatLength && beat.substring(currentPosition, currentPosition + 1).matches("[|:\\[\\]]")) {
				currentNote += beat.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				is_note = false;
				somethingMatched = true;
			}
			// Otherwise grab the note sequence
			// get symbols before the letters
			while (is_note && currentPosition < beatLength && beat.substring(currentPosition, currentPosition + 1).matches("[_^=]")) {
				currentNote += beat.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			// get the note letter
			if (is_note && currentPosition < beatLength && beat.substring(currentPosition, currentPosition + 1).matches("[A-Ga-gzZ]")) {
				currentNote += beat.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			//get after the note symbols.
			while (is_note && currentPosition < beatLength && beat.substring(currentPosition, currentPosition + 1).matches("[',0-9/]")) {
				currentNote += beat.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			if (!somethingMatched) {
				System.out.println("NOT MATCHED SYMBOL " + beat.substring(currentPosition, currentPosition + 1));
				currentPosition++;
			}
			notes.add(currentNote);
		}
		return notes;
	}

}
