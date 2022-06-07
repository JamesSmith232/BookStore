/**
CD is a subclass of BookStoreItem and represents a CD for sale 
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/

public class CD extends BookStoreItem {
    protected int playingTime; 
    
    public CD (String title, String author, double price) {
        //Uses super to invoke constructors of class BookStoreItem
        super(title, author, price);  
    } //end of CD() constructor method
    
    public String toString() {
        return ("\nCD\n" + super.toString()); 
    }
}