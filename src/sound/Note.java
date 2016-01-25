package sound;

public class Note {
	private final char note;
	private final int duration;
	private final int transpose;
	
	public Note(char note, int duration, int transpose) {
		this.note = note;
		this.duration = duration;
		this.transpose = transpose;
	}

	public char getNote() {
		return note;
	}

	public int getDuration() {
		return duration;
	}

	public int getTranspose() {
		return transpose;
	}
}
