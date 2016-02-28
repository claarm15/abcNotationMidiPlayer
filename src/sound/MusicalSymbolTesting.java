package sound;

import static org.junit.Assert.*;

import org.junit.Test;

public class MusicalSymbolTesting {

	@Test
	public void testReturnsEmptyStringWhenNotMatched() {
		String result = MusicalSymbolParser.matchSymbol("blahblahblah");
		assertTrue(result.equals(""));
	}
	
	@Test
	public void testReturnsTheSymbolWhenStringStartsWithMatch() {
		String result = MusicalSymbolParser.matchSymbol("|:sdfghjk");
		assertTrue(result.equals("|:"));
	}
	@Test
	public void testReturnsTheSymbolWhenStringStartsWithMatchWith2Chars() {
		String result = MusicalSymbolParser.matchSymbol(":|");
		assertTrue(result.equals(":|"));
	}
	@Test
	public void testReturnsTheSymbolWhenStringStartsWithMatchWith1Char() {
		String result = MusicalSymbolParser.matchSymbol("[");
		assertTrue(result.equals("["));
	}
	@Test
	public void testReturnsTheSymbolWhenStringStartsWithMatchWithCharThenLetter() {
		String result = MusicalSymbolParser.matchSymbol("|e");
		assertTrue(result.equals("|"));
	}
	
	@Test
	public void testReturnsTheSymbolWhenStringStartsColon() {
		String result = MusicalSymbolParser.matchSymbol(":|e");
		assertTrue(result.equals(":|"));
	}
	@Test
	public void testReturnsTheSymbolWithTupletTest() {
		String result = MusicalSymbolParser.matchSymbol("(3");
		assertTrue(result.equals("(3"));
	}
}
