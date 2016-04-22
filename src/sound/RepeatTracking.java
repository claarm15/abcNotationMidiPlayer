package sound;

class RepeatTracking extends NoteSequence {
	//inherited methods
	/*
	 * public void addNote(Note note) 
	 * public void reset()
	 * public boolean hasSharpsOrFlats() 
	 */
	private boolean recordingOn;
	private int startPosition;
	private int endPosition;
	
	public RepeatTracking() {
		super();
		startPosition = -1;
		endPosition = -1;
		recordingOn = false;
	}

	public boolean isRecordingOn() {
		return recordingOn;
	}

	public void startRecording(int startPosition) {
		this.startPosition = startPosition;
		this.recordingOn = true;
	}
	
	public void stopRecording(int endPosition) {
		this.endPosition = endPosition;
		this.recordingOn = true;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getDifference() {
		return endPosition - startPosition;
	}
	
	public boolean hasNote() {
		boolean result = false;
		if (notes.size() > 0) {
			result = true;
		}
		return result;
	}
	
	public Note popNote() {
		if (notes.size() > 0) {
			return notes.remove(0);
		}
		return null;
	}

	public int getEndPosition() {
		return endPosition;
	}
}
