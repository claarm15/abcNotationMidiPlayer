package sound;

import java.util.ArrayList;
import java.util.Iterator;

class Measure extends NoteSequence {
	

	public Measure() {
		super();
	}

	public int returnPitchOfPreviousMatchingNote(char noteLetter, int octave) {
		int result = 0;
		int findPosition = -1;
		for (int i = notes.size() - 1; i >= 0; i--) {
			if (notes.get(i).getNoteLetter() == noteLetter &&
					notes.get(i).getOctave() == octave) {
				result = notes.get(i).getPitch();
				break;
			}
		}
		return result;
	}
}
