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
	private RepeatTracking repeatTracking;
	ArrayList<Note> sharpAndFlatNotes;
	ArrayList<ArrayList<Measure>> repeatLoops = new ArrayList();
	Measure currentMeasure;

	public SequenceLoader(int tempo, ArrayList<String> songLines)
			throws MidiUnavailableException, InvalidMidiDataException {
		sharpAndFlatNotes = new ArrayList<>();
		currentMeasure = new Measure();
//		repeatTracking = new RepeatTracking();
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
				} else if (songLine.matches("^[vV]:.*"))
					startTick = v1RepeatPoint;
			} else if (songLine.matches("[a-gzA-G0-9,=:| \\[\\]\\/_^\\(']*")) {
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
		boolean inChord = false;
		int tupletSpec = 1;
		int tupletCount = 0;
		ArrayList<String> notes = splitLineIntoNotes(line);
		for (String noteCode : notes) {
			// if in a chord, startTick stays the same...
			if (noteCode.equals("["))
				inChord = true;
			else if (noteCode.equals("]")) {
				inChord = false;
				startTick += lastNoteDuration;
			} else if (noteCode.matches("\\(\\d")) { // handle triplets
				tupletCount = Integer.parseInt(noteCode.substring(1));
				tupletSpec = tupletCount;
			} else if (noteCode.equals("|")) {
				currentMeasure.reset();
//			} else if (noteCode.equals("|:") || noteCode.matches("\\[\\d")) {
//				repeatTracking.startRecording(startTick);
//			} else if (noteCode.equals(":|")) {
//				repeatTracking.stopRecording(startTick);
//				addNotesToSong();
			}
			// ok we have a note to process
			if (noteCode.matches(".*[A-Ga-gzZ].*")) {
				// Note note = new Note(noteCode);
				Note note = new Note(noteCode, tupletSpec, currentMeasure);
				lastNoteDuration = note.getDuration();
				if (tupletCount > 0)
					tupletCount--;
				else
					tupletSpec = 1;
				if (!inChord)
					startTick += lastNoteDuration;
				note.setCurrentPosition(startTick);
				String noteLetter = "" + note.getNoteLetter();
				if (!noteLetter.matches("[zZ]")) {
					song.addNote(note.getPitch(), startTick, lastNoteDuration + 1);
//					System.out.println("Repeat Tracking: " + repeatTracking.isRecordingOn());
//					if (repeatTracking.isRecordingOn() && startTick > repeatTracking.getStartPosition()) {
//						repeatTracking.addNote(note);
//					}
				}
				currentMeasure.addNote(note); // move to Note Class
				System.out.println(note.getNoteLetter() + " pitch: " + note.getPitch() + "\tOctave: " + note.getOctave()
						+ "\t NumTicks: " + lastNoteDuration + "\tstartTick: " + startTick);
			}
		}
	}

	private void addNotesToSong() {
		int difference = repeatTracking.getDifference() + 1;
		while (repeatTracking.hasNote()) {
			Note note = repeatTracking.popNote();
			song.addNote(note.getPitch(), note.getCurrentPosition() + difference, note.getDuration());
			lastNoteDuration = note.getDuration();
		}
		startTick = repeatTracking.getEndPosition() + difference + lastNoteDuration + 1;
		repeatTracking.reset();
	}

	private ArrayList<String> splitLineIntoNotes(String wholeLine) {
		String line = wholeLine.replaceAll(" ", "");
		ArrayList<String> notes = new ArrayList<>();
		int currentPosition = 0;
		int lineLength = line.length();
		/*
		 * I'm using the lexer for the symbols as opposed to the notes. The
		 * notes seems to have to many possibilities for a large matching loop
		 * when better methods appear to be available. I'm also not making an
		 * abstract lexer class unless I need a second lexer (a caveat in case I
		 * make one and forget to adjust this).
		 */
		System.out.println(
				"Starting CurrentPos: " + currentPosition + "\tTotalLength: " + lineLength + "\tLine: " + line);
		while (currentPosition < lineLength) {
			boolean somethingMatched = false;
			boolean isNote = true;
			String currentNote = "";
			//If the line starts with a non note symbol.
			if (line.substring(currentPosition, currentPosition + 1).matches("[|:\\[\\]]")) {
				// get 2 chars but make sure it doesn't go over the string
				// length
				int endPositon = currentPosition + 2;
				if (currentPosition == lineLength - 1) {
					endPositon--;
				}
				currentNote = MusicalSymbolParser.matchSymbol(line.substring(currentPosition, endPositon));

				if (!currentNote.equals("")) {
					currentPosition += currentNote.length();
					isNote = false;
					somethingMatched = true;
				}
			}

			// Otherwise grab the note sequence
			// get symbols before the letters
			while (isNote && line.substring(currentPosition, currentPosition + 1).matches("[_^=]")) {
				currentNote += line.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			// get the note letter
			if (isNote && currentPosition < lineLength
					&& line.substring(currentPosition, currentPosition + 1).matches("[A-Ga-gzZ]")) {
				currentNote += line.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			// get after the note symbols.
			while (isNote && currentPosition < lineLength
					&& line.substring(currentPosition, currentPosition + 1).matches("[',0-9/]")) {
				currentNote += line.substring(currentPosition, currentPosition + 1);
				currentPosition++;
				somethingMatched = true;
			}
			if (!somethingMatched) {
				System.out.println("NOT MATCHED SYMBOL " + line.substring(currentPosition, currentPosition + 1) + " at "
						+ currentPosition + " from line " + wholeLine);
				currentPosition++;
			}
			notes.add(currentNote);
		}
		return notes;
	}
}
