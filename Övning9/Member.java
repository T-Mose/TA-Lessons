import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String id;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public String getId() {
        return id;
    }
    
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }
    
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
    
    @Override
    public String toString() {
        return String.format("Member{Name: %s, ID: %s, Borrowed Books: %s}", 
                name, id, borrowedBooks);
    }

    public String getName() {
        return name;
    }
}