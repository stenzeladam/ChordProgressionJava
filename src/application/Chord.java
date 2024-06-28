package application;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chord {
    
    private final String[] scale;
    private final String[] chromatic;
    private final int chordNum;
    private ArrayList<String> notes;
    private int rootIndex;
    private boolean isFlattened;
    private boolean isSharpened;
    private boolean suspended2;
    private boolean suspended4;
    private boolean seventh;
    
    public Chord(int chordNumber, String[] localScale, String[] localChrom, boolean[] MODS){
        scale = localScale;
        chromatic = localChrom;
        chordNum = chordNumber - 1; //because java starts counting at 0, but chord progressions start counting at 1
        isFlattened = MODS[0];
        isSharpened = MODS[1];
        suspended2 = MODS[2];
        suspended4 = MODS[3];
        seventh = MODS[4];
        
        /*
        for (boolean value : MODS) {
            System.out.println(value);
        }
        */
        for (int i = 0; i < 12; i++) {				//Set the starting index of the chromatic scale to the root of the chord 
        	if (scale[chordNum] == chromatic[i]) { 
        		rootIndex = i;
        		if (isFlattened) {
        			rootIndex = (rootIndex - 1) % chromatic.length;
        			if (rootIndex == -1) {
        				rootIndex = 11;
        			}
        		}
        		else if (isSharpened) {
        			rootIndex = (rootIndex + 1) % chromatic.length;
        		}
        		break;
        	}
        }
        
        notes = new ArrayList<>(); //can dynamically add notes to the chord, because a chord could be as few as two notes, three notes, four notes, or technically as many notes as you want to add
        notes.add(chromatic[rootIndex]); //add the root note of the chord
        
    }
    
    private void getMinorSecond() {
    	int minorSecond;
        minorSecond = (rootIndex + 1) % chromatic.length;
        notes.add(chromatic[minorSecond]);
    }
    
    private void getMajorSecond() {
    	int majorSecond;
        majorSecond = (rootIndex + 2) % chromatic.length;
        notes.add(chromatic[majorSecond]);
    }
    
    private void getMinorThird() {
    	int minorThird;
        minorThird = (rootIndex + 3) % chromatic.length;
        notes.add(chromatic[minorThird]);
    }
    
    private void getMajorThird() {
    	int majorThird;
    	majorThird = (rootIndex + 4) % chromatic.length;
        notes.add(chromatic[majorThird]);
    }
    
    private void getPerfectFourth() {
    	int perfectFourth;
    	perfectFourth = (rootIndex + 5) % chromatic.length;
        notes.add(chromatic[perfectFourth]);
    }
    
    private void getFlatFifth() {
    	int flatFifth;
    	flatFifth = (rootIndex + 6) % chromatic.length;
        notes.add(chromatic[flatFifth]);
    }
    
    private void getPerfectFifth() {
    	int perfectFifth;
    	perfectFifth = (rootIndex + 7) % chromatic.length;
        notes.add(chromatic[perfectFifth]);
    }
    
    private void getMinorSixth( ) {
    	int minorSixth;
    	minorSixth = (rootIndex + 8) % chromatic.length;
    	notes.add(chromatic[minorSixth]);
    }
    
    private void getMajorSixth( ) {
    	int majorSixth;
    	majorSixth = (rootIndex + 9) % chromatic.length;
    	notes.add(chromatic[majorSixth]);
    }
    
    private void getMinorSeventh( ) {
    	int minorSeventh;
    	minorSeventh = (rootIndex + 10) % chromatic.length;
    	notes.add(chromatic[minorSeventh]);
    }
    
    private void getMajorSeventh( ) {
    	int majorSeventh;
    	majorSeventh = (rootIndex + 11) % chromatic.length;
    	notes.add(chromatic[majorSeventh]);
    }
    
    private void getOctave( ) {
    	int octave;
    	octave = (rootIndex + 12) % chromatic.length;
    	notes.add(chromatic[octave]);
    }
    
    private void getMinorNinth( ) {
    	int minorNinth;
    	minorNinth = (rootIndex + 13) % chromatic.length;
    	notes.add(chromatic[minorNinth]);
    }
    
    private void getMajorNinth( ) {
    	int majorNinth;
    	majorNinth = (rootIndex + 14) % chromatic.length;
    	notes.add(chromatic[majorNinth]);
    }
    
    private void getEleventh( ) {
    	int eleventh;
    	eleventh = (rootIndex + 17) % chromatic.length;
    	notes.add(chromatic[eleventh]);
    }
    
    private void getMinorThirteenth( ) {
    	int minorThirteenth;
    	minorThirteenth = (rootIndex + 20) % chromatic.length;
    	notes.add(chromatic[minorThirteenth]);
    }
    
    private void getMajorThirteenth( ) {
    	int majorThirteenth;
    	majorThirteenth = (rootIndex + 21) % chromatic.length;
    	notes.add(chromatic[majorThirteenth]);
    }
    
    private void getThird(){
        int third;
        third = (chordNum + 2) % scale.length;
        notes.add(scale[third]);
    }
   
    private void getFifth(){
        int fifth;
        fifth = (chordNum + 4) % scale.length;
        notes.add(scale[fifth]);
    }
   
    public void buildChord() {
        
    	if (suspended2 == false && suspended4 == false) {
    		this.getThird();
    	}
    	else if (suspended2 == true) {
    		this.getMajorSecond();
    	}
    	else if (suspended4 == true) {
    		this.getPerfectFourth();
    	}
    	this.getFifth();
    	if (seventh == true) {
    		int sev;
            sev = (chordNum + 6) % scale.length;
            notes.add(scale[sev]);
    	}
    }

    public ArrayList<String> getNotes(){
        return notes;
    }
    
    public String printNotes() {
        String str = "[ "; 
        for (int i = 0; i < notes.size(); i++) {
            if (i < notes.size() - 1) {
                str = str + notes.get(i) + ", ";
            }
            else {
                str = str + notes.get(i) + " ]";
            }
        }
        return str;
    }
    
    public String getChordName() {
    	
    	String[] notesArray = notes.toArray(new String[notes.size()]);
    	
    	
    	// Sort the notes in ascending order
        //Arrays.sort(notesArray);

        // Create a map of common chord patterns
        Map<String, String> chordPatterns = new HashMap<>();
        chordPatterns.put(notesArray[0] + "", "0-4-7-");
        chordPatterns.put(notesArray[0] + "m", "0-3-7-");
        chordPatterns.put(notesArray[0] + "°", "0-3-6-");
        chordPatterns.put(notesArray[0] + "aug", "0-4-8-");
        chordPatterns.put(notesArray[0] + "sus2", "0-2-7-");
        chordPatterns.put(notesArray[0] + "sus4", "0-5-7-");
        chordPatterns.put(notesArray[0] + "7", "0-4-7-10-");
        chordPatterns.put(notesArray[0] + "m7", "0-3-7-10-");
        chordPatterns.put(notesArray[0] + "7Maj7", "0-4-7-11-");
        chordPatterns.put(notesArray[2] + "/" + notesArray[1], "0-3-8-");
        chordPatterns.put(notesArray[1] + "m/" + notesArray[0], "0-5-8-");

        // Convert the input notes to intervals
        StringBuilder intervals = new StringBuilder();
        int rootNote = noteToValue(notesArray[0]);
        for (String note : notesArray) {
            int noteValue = noteToValue(note);
            int interval = (noteValue - rootNote + 12) % 12;
            intervals.append(interval).append("-");
        }

        // Check if the intervals match a common chord pattern
        for (Map.Entry<String, String> entry : chordPatterns.entrySet()) {
        	//System.out.println(intervals.toString());
            if (intervals.toString().equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        // If no match is found, return a custom format
        return "Custom Chord";
    }

    // Helper function to convert a note to a numeric value
    private static int noteToValue(String note) {
        String[] noteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        //System.out.println(Arrays.asList(noteNames).indexOf(note));
        return Arrays.asList(noteNames).indexOf(note);
    }
}