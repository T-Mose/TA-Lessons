public 
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean checkedOut = false;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    public boolean isCheckedOut() {
        return checkedOut;
    }
    
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    @Override
    public String toString() {
        return String.format("Book{Title: %s, Author: %s, ISBN: %s, Checked Out: %s}", 
                title, author, isbn, checkedOut);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}