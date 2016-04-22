package sound;

import java.util.ArrayList;
import java.util.Iterator;

abstract class NoteSequence {
	protected ArrayList<Note> notes;
	boolean hasSharpsOrFlats;
	int foundPosition;

	public NoteSequence() {
		notes = new ArrayList<Note>();
		hasSharpsOrFlats = false;
		foundPosition = -1;
	}

	public void addNote(Note note) {
		notes.add(note);
		if (note.isSharpOrFlat()) {
			hasSharpsOrFlats = true;
		}
	}

	public void reset() {
		notes = new ArrayList<Note>();
		hasSharpsOrFlats = false;
		foundPosition = -1;
	}

	public boolean hasSharpsOrFlats() {
		return hasSharpsOrFlats;
	}


}
