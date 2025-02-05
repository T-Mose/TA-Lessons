public class LibrarySystem {

    private Book[] catalog;
    private Book[] borrowedBooks;
    private int catalogSize;
    private int borrowedSize;
    private static final int MAX_BOOKS = 10;

    public LibrarySystem() {
        this.catalog = new Book[MAX_BOOKS];
        this.borrowedBooks = new Book[MAX_BOOKS];
        this.catalogSize = 0;
        this.borrowedSize = 0;
    }

    public boolean addBook(String title, String author) {
        if (catalogSize < MAX_BOOKS) {
            catalog[catalogSize] = new Book(title, author);
            catalogSize++;
            return true;
        }
        return false;  // Catalog is full
    }

    public boolean borrowBook(String title) {
        int bookIndex = findBookInCatalog(title);
        if (bookIndex != -1 && !isBookBorrowed(title)) {
            borrowedBooks[borrowedSize] = catalog[bookIndex];
            borrowedSize++;
            return true;
        }
        return false;
    }

    public boolean returnBook(String title) {
        int bookIndex = findBookInBorrowed(title);
        if (bookIndex != -1) {
            // Shift all books down to fill the gap in borrowedBooks array
            for (int i = bookIndex; i < borrowedSize - 1; i++) {
                borrowedBooks[i] = borrowedBooks[i + 1];
            }
            borrowedBooks[borrowedSize - 1] = null;  // Clear last slot
            borrowedSize--;
            return true;
        }
        return false;
    }

    public boolean isBookAvailable(String title) {
        int bookIndex = findBookInCatalog(title);
        return bookIndex != -1 && !isBookBorrowed(title);
    }

    private int findBookInCatalog(String title) {
        for (int i = 0; i < catalogSize; i++) {
            if (catalog[i].getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    private int findBookInBorrowed(String title) {
        for (int i = 0; i < borrowedSize; i++) {
            if (borrowedBooks[i].getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBookBorrowed(String title) {
        return findBookInBorrowed(title) != -1;
    }

    public void printCatalog() {
        System.out.println("Library Catalog:");
        for (int i = 0; i < catalogSize; i++) {
            if (!isBookBorrowed(catalog[i].getTitle())) {
                System.out.println(catalog[i]);
            }
        }
    }

    /**
     * Inner class representing a book.
     */
    class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return title + " by " + author;
        }
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        library.addBook("The Hobbit", "J.R.R. Tolkien");
        library.addBook("1984", "George Orwell");
        library.addBook("To Kill a Mockingbird", "Harper Lee");

        library.printCatalog();

        System.out.println("\nBorrowing 'The Hobbit': " + library.borrowBook("The Hobbit"));
        library.printCatalog();

        System.out.println("\nReturning 'The Hobbit': " + library.returnBook("The Hobbit"));
        library.printCatalog();

        System.out.println("\nIs '1984' available? " + library.isBookAvailable("1984"));
    }
}
