/**
DVD is a subclass of BookStoreItem and represents a DVD for sale 
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/

public class DVD extends BookStoreItem {
    protected int playingTime; 
    
    public DVD(String title, String author, double price) {
        //Uses super to invoke constructors of class BookStoreItem
        super(title, author, price);  
    } //end of DVD() constructor method
    
    public String toString() {
        return ("\nDVD\n" + super.toString()); 
    }
}