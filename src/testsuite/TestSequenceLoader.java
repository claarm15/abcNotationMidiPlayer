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
}
