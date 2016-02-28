package sound;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note {
	private String note;
	private int duration;
	private HashMap<String, Integer> noteDetails;
	private static Measure currentMeasure;

	public Note(String note, int tupletRate, Measure currentMeasure) {
		this.note = note;
		this.currentMeasure = currentMeasure;
		duration = DurationCalculator.findDuration(note) / tupletRate;
		noteDetails = processPitch(note);
	}

	public int getHasAugment() {
		return noteDetails.get("hasAugment").intValue();
	}

	public int getOctave() {
		return noteDetails.get("octaveShift").intValue();
	}

	public int getPitch() {
		return noteDetails.get("pitch").intValue();
	}

	public void setPitch(Integer value) {
		noteDetails.put("pitch", value);
	}

	public char getNoteLetter() {
		return (char) noteDetails.get("noteLetter").intValue();
	}

	public int getDuration() {
		return this.duration;
	}

	private static HashMap<String, Integer> processPitch(String note) {
		System.out.println("Note passed: " + note);
		HashMap<String, Integer> noteDetails = new HashMap<>();
		int pitch = 0;
		// first let's get the note...
		String noteLetter = "";
		Pattern pattern = Pattern.compile("([A-Ga-gzZ])");
		Matcher matcher = pattern.matcher(note);
		if (matcher.find()) {
			noteLetter = matcher.group(1);
			noteDetails.put("noteLetter", (int) noteLetter.toUpperCase().charAt(0));
			if (!noteLetter.contains("Z") && !noteLetter.contains("z")) {
				// Work out octaves
				int octaveUpCount = countOccurancesOf(note, '\'');
				int octaveDownCount = countOccurancesOf(note, ',') * -1;

				// see if the letter changes the octave too...
				if (noteLetter.matches("[c-g]")) {
					octaveUpCount++;
				} else if (noteLetter.matches("[ab]")) {
					octaveUpCount += 2;
				}
				noteDetails.put("octaveShift", octaveUpCount + octaveDownCount);
				int octaveTotal = noteDetails.get("octaveShift") * Pitch.OCTAVE;

				// Do we need to transpose for flat or sharp?
				// Will need some sort of way of seeing if it is already used in
				// the
				// same measure...
				// an issue for flat, sharp, and neutral.
				int flatCount = countOccurancesOf(note, '_') * -1;
				int neutralCount = countOccurancesOf(note, '=');
				int sharpCount = countOccurancesOf(note, '^');
				noteDetails.put("isSharpOrFlat", flatCount + sharpCount);

				pitch = new Pitch((char) noteDetails.get("noteLetter").intValue())
						.transpose(noteDetails.get("isSharpOrFlat") + octaveTotal).toMidiNote();

				// reporting if there is an accidental
				if (flatCount < 0 || neutralCount > 0 || sharpCount > 0)
					noteDetails.put("hasAugment", 1);
				else { // Note is subject to previous adjustments of pitch...
					if (currentMeasure.hasSharpsOrFlats()) {
						int augmentedPitch = currentMeasure.returnPitchOfPreviousMatchingNote(noteLetter.charAt(0),
								octaveTotal);
						if (augmentedPitch > 0)
							pitch = augmentedPitch;
					}
					noteDetails.put("hasAugment", 0);
				}

				noteDetails.put("pitch", pitch);
			} else {
				//0 or -1 one values to represent lack of a value for Z;
				noteDetails.put("pitch", -1);
				noteDetails.put("isSharpOrFlat", 0);
				noteDetails.put("hasAugment", 0);
				noteDetails.put("octaveShift", 0);
			}
		}

		else
			// NEED TO DO SOMETHING ELSE FOR FALSE ARM...
			System.out.println("ERROR SHOULD FIND A LETTER IN PITCH CALCULATOR");
		return noteDetails;
	}

	private static int countOccurancesOf(String note, char symbol) {

		int stringLength = note.length();
		int occurance = 0;
		for (int i = 0; i < stringLength; i++) {
			if (note.charAt(i) == symbol)
				occurance++;
		}

		return occurance;
	}

	public boolean isSharpOrFlat() {

		boolean result = false;
		if (noteDetails.get("isSharpOrFlat").intValue() != 0)
			result = true;
		return result;
	}

	// Decided against making this a compareTo method, because I need to match a
	// few
	// elements, but not enough to sort off of through Collections.
	public int compareNameAndOctave(Note other) {

		int result = 0;
		if (getOctave() > other.getOctave())
			result = 1;
		else if (getOctave() > other.getOctave())
			result = -1;
		else {
			if (getNoteLetter() > other.getNoteLetter())
				result = 1;
			else if (getNoteLetter() < other.getNoteLetter())
				result = -1;
		}
		return result;
	}
}
