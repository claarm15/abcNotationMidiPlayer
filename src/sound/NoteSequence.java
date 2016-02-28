package sound;

import java.util.ArrayList;
import java.util.Iterator;

abstract class NoteSequence {
	ArrayList<Note> measure;
	boolean hasSharpsOrFlats;
	int foundPosition;

	public NoteSequence() {
		measure = new ArrayList<Note>();
		hasSharpsOrFlats = false;
		foundPosition = -1;
	}

	public void addNote(Note note) {
		measure.add(note);
		if (note.isSharpOrFlat()) {
			hasSharpsOrFlats = true;
		}
	}

	public void reset() {
		measure = new ArrayList<Note>();
		hasSharpsOrFlats = false;
		foundPosition = -1;
	}

	public boolean hasSharpsOrFlats() {
		return hasSharpsOrFlats;
	}


}
