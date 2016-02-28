package sound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DurationCalculator {
public static int findDuration(String note) {
		
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
}
