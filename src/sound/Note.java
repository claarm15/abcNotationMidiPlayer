package sound;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note {
	private String note;
	private int duration;
	private HashMap<String, Integer> noteDetails;

	public Note(String note) {
		this.note = note;
		duration = findDuration(note);
		noteDetails = processPitch(note);
	}
	
	public int getOctave() {
		return noteDetails.get("octaveShift").intValue();
	}
	
	public int getPitch() {
		return noteDetails.get("pitch").intValue();
	}

	public char getNoteLetter() {
		return (char) noteDetails.get("noteLetter").intValue();
	}

	public int getDuration() {
		return this.duration;
	}

	private static int findDuration(String note) {
		int numTicks = 12;
		boolean beatSet = false;
		Pattern pattern = Pattern.compile("(.*)(\\d+/\\d)(.*)");
		Matcher matcher = pattern.matcher(note);
		if (matcher.find()) {
			numTicks = processSlashGroup(matcher.group(2));
			beatSet = true;
		}
		pattern = Pattern.compile("(.*)(\\d)");
		matcher = pattern.matcher(note);
		if (!beatSet && matcher.find()) {
			int numberInNote = Integer.parseInt(matcher.group(2));
			numTicks = 12 * numberInNote;
			beatSet = true;
		}
		pattern = Pattern.compile("(.*)(\\d)(.*)");
		matcher = pattern.matcher(note);

		if (!beatSet && matcher.find()) {
			int numberInNote = Integer.parseInt(matcher.group(2));
			note = matcher.group(1) + matcher.group(3);
			numTicks = 12 * numberInNote;
		}
		return numTicks;
	}

	private static int processSlashGroup(String slashNumber) {
		int numTicks = 0;
		switch (slashNumber) {
		case "/2":
			numTicks = 6;
			break;
		case "3/4":
			numTicks = 9;
			break;
		case "/3":
			numTicks = 4;
			break;
		case "/4":
			numTicks = 3;
			break;
		default:
			// throw not found exception?
			break;
		}
		return numTicks;
	}

	private static HashMap<String, Integer> processPitch(String note) {
		HashMap<String, Integer> noteDetails = new HashMap<>();
		int pitch = 0;
		// first let's get the note...
		String noteLetter = "";
		Pattern pattern = Pattern.compile("([A-Ga-g])");
		Matcher matcher = pattern.matcher(note);
		if (matcher.find()) {
			noteLetter = matcher.group(1);
			noteDetails.put("noteLetter", (int) noteLetter.toUpperCase().charAt(0));

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
			// Will need some sort of way of seeing if it is already used in the
			// same measure...
			// an issue for flat, sharp, and neutral.
			int flatCount = countOccurancesOf(note, '_') * -1;
			int neutralCount = countOccurancesOf(note, '=');
			int sharpCount = countOccurancesOf(note, '^');
			// Will need reworked...
			noteDetails.put("isSharpOrFlat", flatCount + sharpCount);
			noteDetails.put("pitch", new Pitch((char) noteDetails.get("noteLetter").intValue()).transpose(noteDetails.get("isSharpOrFlat") + octaveTotal)
					.toMidiNote());
		} else
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
}
