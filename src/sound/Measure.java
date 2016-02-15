package sound;

import java.util.ArrayList;
import java.util.Iterator;

public class Measure {
	ArrayList<Note> measure;
	boolean hasSharpsOrFlats;
	int foundPosition;

	public Measure() {
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

	public boolean matchesNote(Note note) {
		boolean result = false;
		//if note has already augmented, we don't want to match.
		if (note.getHasAugment() != 1) {
			for (Note listNote : measure) {
				if (listNote.compareNameAndOctave(note) == 0) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public void augmentNote(Note note) {
		// need to find the most recent match (in case the first has been
		// overridden)
		int findPosition = -1;
		for (int i = measure.size() - 1; i >= 0; i--) {
			if (measure.get(i).compareNameAndOctave(note) == 0) {
				findPosition = i;
				break;
			}
		}
		if (findPosition > -1) {
			note.setPitch(measure.get(findPosition).getPitch());
		}
	}

}
