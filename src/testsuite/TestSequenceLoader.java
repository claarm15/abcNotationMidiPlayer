package testsuite;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.BeforeClass;
import org.junit.Test;

import sound.*;

public class TestSequenceLoader {
	SequencePlayer player;
	ArrayList<String> noteLines;
	
		@Test
	public void testSequenceLoaderPlaysASingleNote() throws MidiUnavailableException, InvalidMidiDataException {
		noteLines = new ArrayList<>();
		noteLines.add("G");
		SequenceLoader loader = new SequenceLoader(0, noteLines);
		player = loader.loadSequence();
		System.out.println(player);
		assertTrue(player.toString().contains("Event: NOTE_ON  Pitch: 67"));
	}

	@Test
	public void testSequenceLoaderPlaysASeriesOfNotes() throws MidiUnavailableException, InvalidMidiDataException {
		noteLines = new ArrayList<>();
		noteLines.add("C E G");
		SequenceLoader loader = new SequenceLoader(0, noteLines);
		player = loader.loadSequence();
		System.out.println(player);
		assertTrue(player.toString().contains("Event: NOTE_ON  Pitch: 67") && 
				player.toString().contains("Event: NOTE_ON  Pitch: 60") && 
				player.toString().contains("Event: NOTE_ON  Pitch: 64"));
	}
	
	@Test
	public void testSequenceLoaderPlaysSongWithTripplets() throws MidiUnavailableException, InvalidMidiDataException {
		noteLines = new ArrayList<>();
		noteLines.add("C C C3/4 D/4 E | E3/4 D/4 E3/4 F/4 G2 | C/3 C/3 C/3 G/3 G/3 G/3 E/3 E/3 E/3 C/3 C/3 C/3 | G3/4 F/4 E3/4 D C2 |");
		SequenceLoader loader = new SequenceLoader(0, noteLines);
		player = loader.loadSequence();
		player.play();
		System.out.println(player);
		assertTrue(player.toString().contains("Event: NOTE_ON  Pitch: 67") && 
				player.toString().contains("Event: NOTE_ON  Pitch: 60") && 
				player.toString().contains("Event: NOTE_ON  Pitch: 64"));
	}
}
