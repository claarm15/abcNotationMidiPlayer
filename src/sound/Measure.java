package sound;

import java.util.ArrayList;
import java.util.Iterator;

public class Measure extends NoteSequence {
	

	public Measure() {
		super();
	}

	public int returnPitchOfPreviousMatchingNote(char noteLetter, int octave) {
		int result = 0;
		int findPosition = -1;
		for (int i = measure.size() - 1; i >= 0; i--) {
			if (measure.get(i).getNoteLetter() == noteLetter &&
					measure.get(i).getOctave() == octave) {
				result = measure.get(i).getPitch();
				break;
			}
		}
		return result;
	}
}
