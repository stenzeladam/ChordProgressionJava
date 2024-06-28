package application;
import java.lang.String;

public class Modes {
	
	private String root;
    private String[] chromatic;
    private String[] scale;
    
    public Modes(String r) {
       
        root = r;
        chromatic = switch (root) {
            case "C" -> new String[] {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
            case "C#/Db" -> new String[] {"C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};
            case "D" -> new String[] {"D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#"};
            case "D#/Eb" -> new String[] {"D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D"};
            case "E" -> new String[] {"E", "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#"};
            case "F" -> new String[] {"F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E"};
            case "F#/Gb" -> new String[] {"F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F"};
            case "G" -> new String[] {"G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#"};
            case "G#/Ab" -> new String[] {"G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G"};
            case "A" -> new String[] {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
            case "A#/Bb" -> new String[] {"A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A"};
            case "B" -> new String[] {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#"};
            default -> new String[] {"ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR", "ERR"};
        };
    }
    
    public void applyMode(String mode) {
        switch(mode) {
            case "Ionian" -> ionian();
            case "Dorian" -> dorian();
            case "Phrygian" -> phrygian();
            case "Lydian" -> lydian();
            case "Mixolydian" -> mixolydian();
            case "Aeolian" -> aeolian();
            case "Locrian" -> locrian();
            default -> {
            }
        }
    }

    private void ionian(){
        
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 2) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void dorian() {
        
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 1 && i != 5) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void phrygian() {
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 0 && i != 4) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void lydian() {
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 3) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void mixolydian() {
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 2 && i != 5) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void aeolian() {
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 1 && i != 4) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    private void locrian() {
        this.scale = new String[7];
        
        int j = 0;
        for (int i = 0; i < this.scale.length; i++) {
            
            if (i != 0 && i != 3) {               // whole step
                this.scale[i] = chromatic[j];
                j++;
                j++;
            }
            else {                      // half step
                this.scale[i] = chromatic[j];
                j++;
            }
        }
    }
    
    public String[] getScale(){
        return this.scale;
    }
    public String[] getChromatic(){
        return this.chromatic;
    }
    public String getRoot(){
        return this.root;
    }

}
