package sound;

import java.util.ArrayList;
import java.util.Iterator;

class MusicalSymbolLexer {
	private ArrayList<String> symbols;
	Iterator i;
	public MusicalSymbolLexer() {
		/*
		 * NOTE: SINGLE SYMBOLS MUST START AFTER THE DOUBLE OF THE SAME TYPE TO 
		 *       RUN CORRECTLY e.g. || must come before |
		 *       
		 *  I considered using a Hashmap and giving the values integers to represent
		 *  the various states, but it was another grammar that wasn't necessary and
		 *  only added to mental debt.  I doubt it would be any more efficient.  This
		 *  also should improve the readability for anyone who understand the musical
		 *  notation symbols.
		 */
		symbols = new ArrayList<String>();
		 symbols.add("||");
		 symbols.add("[|");
		 symbols.add("|]");
		 symbols.add(":|");
		 symbols.add("|:");
		 symbols.add("|");
		 symbols.add("(2");
		 symbols.add("(3");
		 symbols.add("(4");
		 symbols.add("[1");
		 symbols.add("[2");
		 symbols.add("[3");
		 symbols.add("[4");
		 symbols.add("[5");
		 symbols.add("[6");
		 symbols.add("[7");
		 symbols.add("[8");
		 symbols.add("[");
		 symbols.add("]");
		 i = symbols.iterator();
	}
	/**
	 * hasNext() is a boolean check if there is a next element in the array
	 * 
	 * @return true if there is another element to the array
	 */
	public boolean hasNext() {
		return i.hasNext();
	}
	
	
	/**
	 * Next loop through the available symbols.  It will return a blank
	 * string when out of elements or you can use the hasNext() method
	 * to check.
	 *   
	 * @return String of the possible musical symbols.
	 */
	public String next() {
		String result = "";
		if (i.hasNext()) {
			result =  i.next().toString();
		}
		return result;
	}
}
