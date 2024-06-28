package application;

//import java.io.PrintStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import java.util.Random;



public class Main extends Application {
	
	private String KEY;
	private String MODE;
	private boolean keyAndModeSet = false;
	private Modes instance;
	//private boolean isFlat = false;
	//private boolean isSharp = false;
	//private boolean isSus2 = false;
	//private boolean isSus4 = false;
	
	private boolean MODS[] = {false , false,   false,  false,  false};
	      //boolean[] MODS = {isFlat, isSharp, isSus2, isSus4, isSeventh};
	
	
	
    @Override
    public void start(Stage primaryStage) {
    	
    	ArrayList<Chord> chordsAdded = new ArrayList<>();
    	
    	// Create a VBox to hold the radio buttons and output TextArea
    	VBox root = new VBox();
    	
    	// Set vertical spacing between child nodes (in pixels)
        root.setSpacing(7); // Adjust the value to set your desired spacing

    	// Create a Label for the radio button section title
    	Label radioLabel = new Label("Select a root/key:");

    	// Create a ToggleGroup to ensure only one radio button can be selected at a time asd
    	ToggleGroup toggleGroup = new ToggleGroup();

    	// Create an array of radio button names
    	String[] radioButtonNames = {
    	    "C",
    	    "C#/Db",
    	    "D",
    	    "D#/Eb",
    	    "E",
    	    "F",
    	    "F#/Gb",
    	    "G",
    	    "G#/Ab",
    	    "A",
    	    "A#/Bb",
    	    "B"
    	};

    	root.getChildren().add(radioLabel);
    	// Create and add 12 radio buttons to the VBox with individual names
    	for (String name : radioButtonNames) {
    	    RadioButton radioButton = new RadioButton(name);
    	    radioButton.setToggleGroup(toggleGroup);
    	    root.getChildren().add(radioButton);
    	}

    	// Create a ComboBox for selecting one of seven options
    	ComboBox<String> comboBox = new ComboBox<>();
    	comboBox.getItems().addAll(
    	    "Ionian",
    	    "Dorian",
    	    "Phrygian",
    	    "Lydian",
    	    "Mixolydian",
    	    "Aeolian",
    	    "Locrian"
    	);
    	comboBox.setPromptText("Modes");
    	
    	root.getChildren().add(comboBox);
    	
    	TextArea outputTextArea = new TextArea();
    	outputTextArea.setEditable(false); // Make it read-only
    	outputTextArea.setWrapText(true); // Wrap text within the TextArea

    	// Create a button for submitting the input
    	Button submitButton = new Button("Submit");
    	
    	Button setKeyAndMode = new Button("Select Key and Mode");
    	root.getChildren().add(setKeyAndMode);
    	
    	Button randomKeyAndMode = new Button("Select Random Key and Mode");
    	root.getChildren().add(randomKeyAndMode);
    	randomKeyAndMode.setAlignment(Pos.TOP_LEFT);
    	
    	Label radioLabel2 = new Label("Add a chord: ");
        ToggleGroup toggleGroup2 = new ToggleGroup(); // Create a ToggleGroup
        
     // Create an array of radio button names
    	String[] radioButtonChords = {
    	    "I",
    	    "II",
    	    "III",
    	    "IV",
    	    "V",
    	    "VI",
    	    "VII"
    	};
    	
    	Button addChordButton = new Button("Add Chord");
    	
    	setKeyAndMode.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    	    public void handle(ActionEvent event) {
    	        
    			KEY = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
    	        MODE = comboBox.getValue();
    	        instance = new Modes(KEY);
    	        instance.applyMode(MODE);
    	        keyAndModeSet = true;
    	        
    	        outputTextArea.appendText("Selected Key: " + KEY + "      Selected Mode: " + MODE + "\n"); 
    	    }
    		
    	});
    	
    	randomKeyAndMode.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			
    			int totalRadioButtons = toggleGroup.getToggles().size();
    			if (totalRadioButtons > 0) {
    		        int randomIndex = new Random().nextInt(totalRadioButtons);
    		        toggleGroup.getToggles().get(randomIndex).setSelected(true);
    		    }
    			int totalComboBoxes = comboBox.getItems().size();
    			if (totalComboBoxes > 0) {
    				int randomIndex = new Random().nextInt(totalComboBoxes);
    		        comboBox.getSelectionModel().select(randomIndex);
    			}
    			
    			KEY = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
    	        MODE = comboBox.getValue();
    	        instance = new Modes(KEY);
    	        instance.applyMode(MODE);
    	        keyAndModeSet = true;
    	        
    	        outputTextArea.appendText("Selected Key: " + KEY + "      Selected Mode: " + MODE + "\n"); 
    		}
    	});
    	
    	
    	root.getChildren().add(radioLabel2);
        	
        // Create and add 7 radio buttons to the VBox with chord Roman numerals
        for (String name : radioButtonChords) {
            RadioButton chordNumeral = new RadioButton(name);
            chordNumeral.setToggleGroup(toggleGroup2);
        	root.getChildren().add(chordNumeral);
        }
    	
        Label mods = new Label("Modulations: "); 
        root.getChildren().add(mods);
    	
    	CheckBox checkFlat = new CheckBox("b (Flat)");
    	root.getChildren().add(checkFlat);
    	
    	CheckBox checkSharp = new CheckBox("# (Sharp)");
     	root.getChildren().add(checkSharp);
    	
    	// Define an event handler for the CheckBox
        checkFlat.setOnAction(event -> {
            if (checkFlat.isSelected()) {
                MODS[0] = true;
                checkSharp.setSelected(false);
                MODS[1] = false;
            } else {
                MODS[0] = false;
            }
        });
        
    	// Define an event handler for the CheckBox
        checkSharp.setOnAction(event -> {
            if (checkSharp.isSelected()) {
                MODS[1] = true;
                checkFlat.setSelected(false);
                MODS[0] = false;
            } else {
                MODS[1] = false;
            }
        });
        
        CheckBox checkSus4 = new CheckBox("Suspended 4th");
        root.getChildren().add(checkSus4);
        
        CheckBox checkSus2 = new CheckBox("Suspended 2nd");
    	root.getChildren().add(checkSus2);
    	
    	// Define an event handler for the CheckBox
        checkSus2.setOnAction(event -> {
            if (checkSus2.isSelected()) {
                MODS[2] = true;
                checkSus4.setSelected(false);
                MODS[3] = false;
            } else {
            	MODS[2] = false;
            }
        });
        
        checkSus4.setOnAction(event -> {
            if (checkSus4.isSelected()) {
            	MODS[3] = true;
                checkSus2.setSelected(false);
                MODS[2] = false;
            } else {
            	MODS[3] = false;
            }
        });
        
        CheckBox checkSeventh = new CheckBox("Seventh Chord");
        root.getChildren().add(checkSeventh);
        
        checkSeventh.setOnAction(event -> {
        	if (checkSeventh.isSelected()) {
        		MODS[4] = true;
        	}
        	else {
        		MODS[4] = false;
        	}
        });
    	
    	// Event handler for the submit button click
    	submitButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	      
    	        //outputTextArea.clear();	        
    	        String output = "";   	        
    	        String temp = "";
    	        outputTextArea.appendText("\n");
    	        
    	        for (int i = 0; i < chordsAdded.size(); i++) {
    	            temp = chordsAdded.get(i).printNotes() + " " + chordsAdded.get(i).getChordName();
    	            output = output + temp + "\n";
    	        }
    	        outputTextArea.appendText(output + "\n");
    	    }
    	});
  
    	
    	// Event handler for the button click
    	addChordButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	    	
    	    	String selectedNumeral = ((RadioButton) toggleGroup2.getSelectedToggle()).getText();
    	    	int chordNum;
    	    	
    	    	if (keyAndModeSet == true) {
    	    	
    	    		chordNum = switch (selectedNumeral) {
    	    				case "I" -> 1;
    	    				case "II" -> 2;
    	    				case "III" -> 3;
    	    				case "IV" -> 4;
    	    				case "V" -> 5;
    	    				case "VI" -> 6;
    	    				case "VII" -> 7;
    	    				default -> 0;
    	    			};
    	    			
    	    			if (MODS[0] == true) {
    	    				selectedNumeral = selectedNumeral + "b";
    	    			}
    	    			if (MODS[1] == true) {
    	    				selectedNumeral = selectedNumeral + "#";
    	    			}
    	    			if (MODS[4] == true) {
    	    				selectedNumeral = selectedNumeral + "^7";
    	    			}
    	    			if (MODS[2] == true) {
    	    				selectedNumeral = selectedNumeral + "sus2";
    	    			}
    	    			if (MODS[3] == true) {
    	    				selectedNumeral = selectedNumeral + "sus4";
    	    			}
    	    			
    	    			outputTextArea.appendText(selectedNumeral + " ");
    	    			Chord tempChord = new Chord(chordNum,instance.getScale(), instance.getChromatic(), MODS);
    	    			tempChord.buildChord();
    	    			chordsAdded.add(tempChord);
    	    			
    	    			//clear check boxes
    	    			checkFlat.setSelected(false);
    	    			MODS[0] = false;
    	    			checkSharp.setSelected(false);
    	    			MODS[1] = false;
    	    			checkSus2.setSelected(false);
    	    			MODS[2] = false;
    	                checkSus4.setSelected(false);
    	                MODS[3] = false;
    	                checkSeventh.setSelected(false);
    	                MODS[4] = false;
    	    		}
    	    	}
    	    }
    	);	
    	
    	Button addRandomChord = new Button("Add a Random Chord");
    	
    	addRandomChord.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	    	
    	    	int randomChord = new Random().nextInt(7);
     		    toggleGroup2.getToggles().get(randomChord).setSelected(true);
    	    	String selectedNumeral = ((RadioButton) toggleGroup2.getSelectedToggle()).getText();
    	    	int chordNum; 	
    	    	
    	    	if (keyAndModeSet == true) {
    	    	
    	    		chordNum = switch (selectedNumeral) {
    	    				case "I" -> 1;
    	    				case "II" -> 2;
    	    				case "III" -> 3;
    	    				case "IV" -> 4;
    	    				case "V" -> 5;
    	    				case "VI" -> 6;
    	    				case "VII" -> 7;
    	    				default -> 0;
    	    			};
    	    			
    	    			Random random0and1 = new Random();
    	    			
    	    			for(int i = 0; i < MODS.length; i++) {
    	    				int randomBool = random0and1.nextInt(10);
    	    				if (randomBool == 1) {
    	    					MODS[i] = true;
    	    				}
    	    			}
    	    			
    	    			if (MODS[0] == true) {
	    					MODS[1] = false;
	    				}
	    				if (MODS[2] == true) {
	    					MODS[3] = false;
	    				}
    	    			
    	    			if (MODS[0] == true) {
    	    				selectedNumeral = selectedNumeral + "b";
    	    			}
    	    			if (MODS[1] == true) {
    	    				selectedNumeral = selectedNumeral + "#";
    	    			}
    	    			if (MODS[4] == true) {
    	    				selectedNumeral = selectedNumeral + "^7";
    	    			}
    	    			if (MODS[2] == true) {
    	    				selectedNumeral = selectedNumeral + "sus2";
    	    			}
    	    			if (MODS[3] == true) {
    	    				selectedNumeral = selectedNumeral + "sus4";
    	    			}
    	    			
    	    			outputTextArea.appendText(selectedNumeral + " ");
    	    			Chord tempChord = new Chord(chordNum,instance.getScale(), instance.getChromatic(), MODS);
    	    			tempChord.buildChord();
    	    			chordsAdded.add(tempChord);
    	    			
    	    			//clear check boxes
    	    			checkFlat.setSelected(false);
    	    			MODS[0] = false;
    	    			checkSharp.setSelected(false);
    	    			MODS[1] = false;
    	    			checkSus2.setSelected(false);
    	    			MODS[2] = false;
    	                checkSus4.setSelected(false);
    	                MODS[3] = false;
    	                checkSeventh.setSelected(false);
    	                MODS[4] = false;
    	    		}
    	    	}
    	    }
    	);
    	
    	// Add all components to the VBox
    	root.getChildren().addAll(
    		addChordButton,
    		addRandomChord,
    	    submitButton,
    	    outputTextArea
    	);
    	
    	Button resetButton = new Button("Reset");
    	root.getChildren().add(resetButton);
    	
    	resetButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        outputTextArea.clear();
    	        toggleGroup.selectToggle(null); // De-select radio buttons
    	        comboBox.getSelectionModel().clearSelection(); // Clear combo box selection
    	        chordsAdded.clear();
    	        instance = null;
    	        keyAndModeSet = false;
    	        
    	        //clear check boxes
    	        checkFlat.setSelected(false);
    	        MODS[0] = false;
    			checkSharp.setSelected(false);
    			MODS[1] = false;
    			checkSus2.setSelected(false);
    			MODS[2] = false;
                checkSus4.setSelected(false);
                MODS[3] = false;
                checkSeventh.setSelected(false);
                MODS[4] = false;
    	    }
    	});
        
        // Create a Scene and set it on the primaryStage
        Scene scene = new Scene(root, 800, 1000);
        primaryStage.setTitle("Chord Progressions");
        primaryStage.setScene(scene);
        
        // Show the primaryStage
        primaryStage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void print(Node nodeToPrint) {
	    Printer printer = Printer.getDefaultPrinter();
	    PrinterJob job = PrinterJob.createPrinterJob(printer);
	    
	    if (job != null) {
	        boolean success = job.printPage(nodeToPrint);
	        
	        if (success) {
	            job.endJob();
	        }
	    }
	}
}
