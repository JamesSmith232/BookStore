/**
Catalog represents the inventory for the book store 
 
 --James Hunter Smith
 --Feb 7, 2019
 --james.smith048@topper.wku.edu
**/

public class Catalog {
    private BookStoreItem[] inventory; 
    private final int MAX = 100; 
    private int count;
    
    public Catalog() {
        inventory = new BookStoreItem[MAX];
        count = 0; 
    } //end of Catlalog() method
    
    //adds items in the inventory array
    public void add(BookStoreItem newItem) {
        inventory[count] = newItem; 
        count++; 
    } //end of add() method
    
    //checks if an item is avaliable in the inventory array
    public boolean isAvailable(String title) {
        boolean found = false; 
        
        for (int i = 0; i < count && !found; i++) {
            if (title.equals(inventory[i].getTitle())) found = true;
        }
        
        return found; 
    } //end of isAvailable() method
    
    //searches and gets item from inventory array 
    public BookStoreItem getItem(String title) {
        BookStoreItem desiredItem = null; 
        boolean found = false; 
        
        for (int i = 0; i < count && !found; i++) {
            if (title.equals(inventory[i].getTitle())) {
                desiredItem = inventory[i]; 
                found = true; 
            }
        }
        
        return desiredItem; 
    } //end of getItem() method
    
    //returns entire inventory array 
    public BookStoreItem[] getList() {
        return inventory; 
    } //end of getList() method 
    
}
