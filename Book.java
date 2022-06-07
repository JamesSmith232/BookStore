/**
Book is a subclass of BookStoreItem and represents a book for sale 
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/

public class Book extends BookStoreItem {
    protected int pages; 
    
    public Book(String title, String author, double price) {
        //Uses super to invoke constructors of class BookStoreItem
        super(title, author, price);  
    } //end of Book() constructor method
    
    public String toString() {
        return ("\nBook\n" + super.toString()); 
    }
}