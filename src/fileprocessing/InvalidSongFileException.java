package fileprocessing;

public class InvalidSongFileException extends Exception {
	public InvalidSongFileException() {
		super("SongFile did not load into a usable format.");
	}

	public InvalidSongFileException(String message) {
		super(message);
	}

}
