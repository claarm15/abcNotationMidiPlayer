package testsuite;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import fileprocessing.*; 

public class TestReadSongFromFile {
	private static ReadSongFromFile readSongFromFile;
	
	@BeforeClass
	public static void beforeClass() {
		readSongFromFile = ReadSongFromFile.getInstance();
	}
	
	@Test(expected=java.nio.file.NoSuchFileException.class)
	public void testBadFileName() throws IOException, InvalidSongFileException {
		readSongFromFile.processFile(Paths.get("somefilethatdoesntexistatallzzzzz.txt"));
		fail("Didn't throw the exception yet...");
	}
	
	@Test
	public void testReadSongFromFileReturnsSongFile() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertTrue(songFile instanceof SongFile);
	}
	
	@Test
	public void testReadSongFromFileReturnsComposer() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getComposer(), "Unknown");
	}
	
	@Test
	public void testReadSongFromFileReturnsKey() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getKey(), "C");
	}
	
	@Test
	public void testReadSongFromFileReturnsTitle() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getTitle(), "Simple scale");
	}
	
	@Test
	public void testReadSongFromFileReturnsTempo() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getTempo(), 120);
	}
	
	@Test
	public void testReadSongFromFileReturnsMeter() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getMeter(), "4/4");
	}
	
	@Test
	public void testReadSongFromFileReturnsLength() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getLength(), "1/4");
	}
	
	@Test
	public void testReadSongFromFileReturnsIndexNumber() throws IOException, InvalidSongFileException {
		SongFile songFile = readSongFromFile.processFile(Paths.get("sample_abc/scale.abc"));
		assertEquals(songFile.getIndexNumber(), 1);
	}
}
