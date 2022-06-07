/**
BookStoreItem represents all items for sale at a bookstore 
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/
public abstract class BookStoreItem {
    
    //declare instance variables which each object created will have its 
    //own copy of these varaibles. 
    protected String title; 
    protected String author; 
    protected double price; 
    
    //constructor method, which intially sets up each BookStoreItem object created
    public BookStoreItem(String title, String author, double price) {
        setTitle(title); 
        setAuthor(author); 
        setPrice(price); 
    } //end of BookStoreItem() constructor method
    
    public BookStoreItem() {
        //using "this" reference to call the other constructor which we pass
        //a title, author, and price
        this("title", "author", 0.0); 
    } //end of BookStoreItem()
    
    //accessor methods which gets the values of the current objects instance variables 
    public String getTitle() {return this.title;}
    public String getAuthor() {return this.author;}
    public double getPrice() {return this.price;}
    //end of accessor methods
    
    //mutator methods which allow current objects instance variables to be assigned
    //new values
    public void setTitle(String title) {
        //trims the title parameter to remove any white space at the end
        //of the string, Then test if string has at least one character
        if ((title.trim()).length() > 0) this.title = title; 
    } //end of setTitle() method
    
    public void setAuthor(String author) {
        //same process as setTitle()
        if ((author.trim()).length() > 0) this.author = author;
    } //end of setAuthor() method
    
    public void setPrice(double price) {
        //checks if price parameter is greater than 0
        if (price >= 0) this.price = price;
    } //end of setPrice() method
    
    public String toString() {
        return "\tTitle: " + this.title + "\n\tAuthor: " + this.author + "\n\tPrice: " + this.price; 
    } //end of toString() method
}
