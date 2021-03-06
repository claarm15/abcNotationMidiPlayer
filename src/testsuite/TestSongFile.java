package testsuite;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import fileprocessing.InvalidSongFileException;
import fileprocessing.SongFile;

/*
 * Most SongFile Tests are in the TestReadSongFile testing class 
 * because it affected both classes.
 */

public class TestSongFile {
	private SongFile songFile;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test(expected=InvalidSongFileException.class)
	public void testSongFileNoInputsThrowsError() throws InvalidSongFileException {
		SongFile.Builder.initializeValues();
		songFile = new SongFile.Builder().build();
		
	}
	
	@Test(expected=InvalidSongFileException.class)
	public void testSongFileNoInputsInvalidTempo() throws InvalidSongFileException {
		SongFile.Builder.initializeValues();
		SongFile.Builder.tempo(0);
		songFile = new SongFile.Builder().build();
	}
	
	@Test(expected=InvalidSongFileException.class)
	public void testSongFileNoInputsWithValidTempoButNoLines() throws InvalidSongFileException {
		SongFile.Builder.initializeValues();
		SongFile.Builder.tempo(1);
		songFile = new SongFile.Builder().build();
	}
	
	@Test
	public void testSongFileReturnStringOfNotesThroughGetNotesAsString() throws InvalidSongFileException {
		SongFile.Builder.initializeValues();
		SongFile.Builder.tempo(1);
		SongFile.Builder.addNoteLine("String one");
		SongFile.Builder.addNoteLine("String two");
		songFile = new SongFile.Builder().build();
		assertTrue("String one String two".equals(songFile.getNotesAsString()));
	}
	
}
