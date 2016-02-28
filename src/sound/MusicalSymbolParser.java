package sound;

class MusicalSymbolParser {

	/**
	 * Using this as a static class because I need to regenerate the lexer
	 * postion each time, so there is no perceived gain on the re-instatiating
	 * the class.
	 */
	private MusicalSymbolParser() {

	}

	/**
	 * This will match up to 2 characters from the MusicalSymbolLexer file.
	 * 
	 * Result will return the matching code or an empty string if none is found.
	 * 
	 * 
	 * @param matchSymbol
	 *            passes in a symbol to match.
	 * @return "" or matching symbol.
	 */
	public static String matchSymbol(String matchSymbol) {
		String result = "";
		String testSymbol = "";
		MusicalSymbolLexer symbols = new MusicalSymbolLexer();
		while (symbols.hasNext()) {
			testSymbol = symbols.next();
			int numberOfCharacters = (matchSymbol.length() > testSymbol.length())?testSymbol.length():matchSymbol.length() ;
			if (testSymbol.equals(matchSymbol.substring(0, numberOfCharacters))) {
				result = testSymbol;
				break;
			}
		}
		return result;
	}

}
