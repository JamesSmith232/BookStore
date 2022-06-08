/**
BookStoreGUI creates a GUI for the BookStore  
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/

import java.util.Scanner;

import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.SplitPane;
import javafx.scene.control.Label; 
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup; 

import javafx.event.EventHandler; 
import javafx.event.ActionEvent;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCombination;

import javafx.geometry.Pos;
import javafx.geometry.Orientation;

import java.io.PrintWriter; 
import java.io.File;
import java.io.FileNotFoundException;

public class BookStoreGUI extends Application {
		
    private Label titleLabel; 
    private Label authorLabel;
    private Label priceLabel;
    private TextField titleTF;
    private TextField authorTF;
    private TextField priceTF;
    
    private RadioButton bookRB;
    private RadioButton cdRB;
    private RadioButton dvdRB;
    
    private ToggleGroup itemTypeGroup;
    private Button clearButton;
    private Button exitButton;
    
    private GridPane inputGP; 
    private VBox sizeVB;
    private VBox buttonVB; 
    private HBox inputHB;
    
    private BorderPane mainBP;
    private BorderPane leftBP;
    private BorderPane rightBP;
    private SplitPane inputSP;
    private TextArea outputTA;
    
    private MenuBar leftMenuBar;
    private MenuBar rightMenuBar;
    private Menu catalogMenu;
    private Menu inventoryMenu;
    private MenuItem loadMenuItem;
    private MenuItem saveMenuItem;
    private MenuItem addMenuItem;
    private MenuItem displayMenuItem;
    private MenuItem searchMenuItem;
	
    private Scene scene; 
    private BookStoreListener listener;
    
    private String title;
    private String author;
    private double price;
    private BookStoreItem book; 
    private BookStoreItem cd; 
    private BookStoreItem dvd;
    
    static Catalog storeCatalog = new Catalog();
    static int count = 0;
    
    public void start(Stage primaryStage) {
	setUpInputControls();
	setUpRB(); 
	setUpButtons();
	
	outputTA = new TextArea(); 
	outputTA.appendText("Welcome to the Book Store!\n");
	outputTA.appendText("If you want to search, enter the title and press Search!");
	inputHB = new HBox(10); 
	inputHB.getChildren().add(inputGP);
	inputHB.getChildren().add(sizeVB);
	
	 //Setting up a menu:
 	leftMenuBar = new MenuBar();
 	rightMenuBar = new MenuBar();
 
	catalogMenu = new Menu("_Catalog");
	inventoryMenu = new Menu("_Inventory");
	
	//initialize menu items
	loadMenuItem = new MenuItem("_Load");
	saveMenuItem = new MenuItem("_Save");
	addMenuItem = new MenuItem("_Add");
	displayMenuItem = new MenuItem("_Display");
	searchMenuItem = new MenuItem("_Search");

	//add menu items to menu	 
	catalogMenu.getItems().add(loadMenuItem);
	catalogMenu.getItems().add(saveMenuItem);
	catalogMenu.getItems().add(addMenuItem);
	inventoryMenu.getItems().add(displayMenuItem);
	inventoryMenu.getItems().add(searchMenuItem);
	 
 	leftMenuBar.getMenus().add(catalogMenu);
 	rightMenuBar.getMenus().add(inventoryMenu);
	
 	leftBP = new BorderPane();
 	leftBP.setTop(inputHB);
 	leftBP.setCenter(outputTA);
 
 	mainBP = new BorderPane();
 	mainBP.setTop(leftMenuBar);
 	mainBP.setCenter(leftBP); 
	 	
     	rightBP = new BorderPane();
     	rightBP.setTop(rightMenuBar);
     	rightBP.setCenter(buttonVB);
    	
    	inputSP = new SplitPane(); 
    	inputSP.setDividerPosition(0, 0.75);
    	inputSP.setOrientation(Orientation.HORIZONTAL);
    	inputSP.getItems().add(mainBP);
    	inputSP.getItems().add(rightBP);
		
	//setOnAction() is used to make a button do something when clicked 
     	listener = new BookStoreListener();
     	loadMenuItem.setOnAction(listener);	
	saveMenuItem.setOnAction(listener);
     	addMenuItem.setOnAction(listener);
     	displayMenuItem.setOnAction(listener);
     	searchMenuItem.setOnAction(listener);
	
	//sets the scene and creates the size of the window 
	scene = new Scene(inputSP, 500, 500);
	
	primaryStage.setTitle("Book Store");
	primaryStage.setScene(scene);
	primaryStage.show();  
    } //end of start() method
	
	
    private void setUpInputControls() {
	//used to describe the textfield 
    	titleLabel = new Label("Title");
    	authorLabel = new Label("Author");
    	priceLabel = new Label("Price");
    	
	//place to add the entries
    	titleTF = new TextField();
    	authorTF = new TextField();
    	priceTF = new TextField();
    	
	//a GridPane lays out items within a flexible grid of rows and columns
    	inputGP = new GridPane();
    	inputGP.setHgap(10); 
    	inputGP.setVgap(10); 
    	inputGP.add(titleLabel, 0, 0);
    	inputGP.add(titleTF, 1, 0);
    	
    	inputGP.add(authorLabel, 0, 1);
    	inputGP.add(authorTF, 1, 1); 
    	
    	inputGP.add(priceLabel, 0, 2);
    	inputGP.add(priceTF, 1, 2); 		
    } //end of setupInputControls() method
	
    private void setUpRB() {
	//creates the radioButtons that will be used to distinguish between items 
    	bookRB = new RadioButton("Book");
    	cdRB = new RadioButton("  CD");
    	dvdRB = new RadioButton("DVD");
    	
    	itemTypeGroup = new ToggleGroup();
    	bookRB.setToggleGroup(itemTypeGroup);
    	cdRB.setToggleGroup(itemTypeGroup);
    	dvdRB.setToggleGroup(itemTypeGroup);
    	
	//creates a vertical column for the radio buttons
    	sizeVB = new VBox(10);
    	sizeVB.setAlignment(Pos.CENTER);
    	sizeVB.setSpacing(20);
    	sizeVB.getChildren().add(bookRB);
    	sizeVB.getChildren().add(cdRB);
    	sizeVB.getChildren().add(dvdRB);
    }
    
    private void setUpButtons() {
    	clearButton = new Button("Clear");
    	exitButton = new Button("Exit");
    	
	//creates a vertical column for the buttons
    	buttonVB = new VBox(10);
    	buttonVB.setAlignment(Pos.CENTER);
    	buttonVB.getChildren().add(clearButton);
    	buttonVB.getChildren().add(exitButton);	
    	
	//gives an action for the clear button, which will set each textfield and textarea to ""
    	clearButton.setOnAction(ae -> {
            titleTF.setText("");
            authorTF.setText("");
            priceTF.setText("");
            outputTA.setText("");
    	});
	//gives the exit button the action of closing out of the GUI
    	exitButton.setOnAction(ae -> { System.exit(0); });
    }
	
    private class BookStoreListener implements EventHandler<ActionEvent> {
	
	//Event Handling controls the event and decides what happen with each item 
	public void handle(ActionEvent e) {
            if (e.getSource() == loadMenuItem) loadFile();
            else if (e.getSource() == saveMenuItem) saveFile();
            else if (e.getSource() == addMenuItem) {
	       	outputTA.setText("");
		title = titleTF.getText();
		author = authorTF.getText();
		price = Double.parseDouble(priceTF.getText());
		if (bookRB.isSelected()) {
			//if book radiobutton is selected, it will create a book object and add it to the 
			//store catalog (array). The same goes for the other two radiobuttons
			book = new Book(title, author, price); 
			storeCatalog.add(book);
			count++;
			outputTA.appendText("\nThe book " + title + " has been added!");
		}
		else if (cdRB.isSelected()) {
			cd = new CD(title, author, price); 
			storeCatalog.add(cd); 
			count++;
			outputTA.appendText("\nThe cd " + title + " has been added!");
		}
		else if (dvdRB.isSelected()) {
			dvd = new DVD(title, author, price); 
			storeCatalog.add(dvd);
			count++;
			outputTA.appendText("\nThe dvd " + title + " has been added!");
		}
				
        	titleTF.setText("");
        	authorTF.setText("");
        	priceTF.setText("");				
            }
            else if (e.getSource() == displayMenuItem) {
		//clears the main textarea and prints out all that is in the catalog array
                outputTA.setText("");
                for (int i = 0; i < count; i++) {
                    outputTA.appendText("\n" + storeCatalog.getList()[i].toString());
                }
            }
            else if (e.getSource() == searchMenuItem) searchItem();
        }
    } //end of class BookStoreListener
	
    private void saveFile() {
	File outputFile = null;
	PrintWriter outputWriter = null; 
		
	try { 
            //creates a new text file in the same folder as the program
            outputFile = new File("E:/Java/Bookstore/inventory.txt");
            outputWriter = new PrintWriter(outputFile);
            outputTA.setText("");
	    //goes through catalog array and prints out every item in the new text file 
            for (int i = 0; i < count; i++) {
        	outputWriter.println("\n" + storeCatalog.getList()[i].toString());
            } 
            outputTA.appendText("\nItem Saved!");
        }
	//attemps to catch this error to prevent the program from stopping 
        catch (FileNotFoundException fnfe) {
            outputTA.setText("");	
            outputTA.setText("\nCould not open the file for writing.");	
        }		
        finally { if (outputWriter != null) outputWriter.close(); }
    } //end of saveFile() method
	
    private void loadFile() {
        File inputFile = null;
        Scanner inputReader = null; 
		
        try {	
	    //takes the inventory.txt file and prints it out in the main text area
            inputFile = new File("E:/Java/Bookstore/inventory.txt");
            outputTA.setText("");	
            outputTA.appendText("\nAttempting to open up the library...");
            inputReader = new Scanner(inputFile);
            while (inputReader.hasNextLine()) {
            	outputTA.appendText("\n" + inputReader.nextLine());
            }
        }
	////attemps to catch this error to prevent the program from stopping 
        catch (FileNotFoundException fnfe) {
            outputTA.setText("");	
            outputTA.appendText("\nNo items currently in the library. Add some if you like!");
        }		
        finally { if (inputReader != null) inputReader.close(); }		
    } //end of loadFile() method
	
    private void searchItem() {
    	String title = titleTF.getText();
    	boolean found = false;
        
	//searches for the title name within the catalog array and prints out the results inside 
	//the main text area 
    	if (storeCatalog.isAvailable(title) == true) {
            outputTA.setText("");
            outputTA.appendText("\nTitle found: " + title);
            outputTA.appendText("\n" + storeCatalog.getItem(title));
    	}
    	else {
            outputTA.setText("");			
            outputTA.appendText("\nTitle not found: " + title);
    	}					
    } //end of searchItem() method
} //end of BookStoreGUI class























