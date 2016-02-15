package sound;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note  {
	private String note;
	private int duration;
	private HashMap<String, Integer> noteDetails;

	public Note(String note) {
		this.note = note;
		duration = findDuration(note);
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

	private static int findDuration(String note) {
		
		int numTicks = 12;
		Pattern pattern = Pattern.compile("([0-9]*\\/[0-9])");
		Matcher matcher = pattern.matcher(note);
		Pattern pattern2 = Pattern.compile("(.*)(\\d)(.*)");
		Matcher matcher2 = pattern2.matcher(note);
		if (matcher.find()) {
			numTicks = processSlashGroup(matcher.group(1));
		} else if (matcher2.find()) {
			int numberInNote = Integer.parseInt(matcher2.group(2));
			note = matcher2.group(1) + matcher2.group(3);
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
		case "3/2":
			numTicks = 16;
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
		Pattern pattern = Pattern.compile("([A-Ga-gzZ])");
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
			if (flatCount < 0 || neutralCount > 0 || sharpCount > 0)
				noteDetails.put("hasAugment", 1);
			else 
				noteDetails.put("hasAugment", 0);
			noteDetails.put("isSharpOrFlat", flatCount + sharpCount);
			if (noteLetter.contains("z") ||  noteLetter.contains("z")) noteDetails.put("pitch", -1);
			else noteDetails.put("pitch", new Pitch((char) noteDetails.get("noteLetter").intValue()).transpose(noteDetails.get("isSharpOrFlat") + octaveTotal)
					.toMidiNote());
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
		if (noteDetails.get("isSharpOrFlat").intValue() != 0) result = true;
		return result;
	}
	
	//Decided against making this a compareTo method, because I need to match a few
	//elements, but not enough to sort off of through Collections.
	public int compareNameAndOctave(Note other) {
		
		int result = 0;
		if (getOctave() > other.getOctave()) 
			result = 1;
		else if (getOctave() > other.getOctave()) 
			result = -1;
		else {
			if (getNoteLetter() > other.getNoteLetter()) result = 1;
			else if (getNoteLetter() < other.getNoteLetter()) result = -1;
		}
		return result;
	}
}
