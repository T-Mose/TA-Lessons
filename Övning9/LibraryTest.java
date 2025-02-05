import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Test class for Library functionalities.
 */
public class LibraryTest {

    /**
     * Tests the addition of a new book to the library.
     */
    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book = new Book("Java Programming", "Author Name", "ISBN123");
        library.addBook(book);
        assertTrue("Book should be added to library", library.getBooks().contains(book));
    }

    /**
     * Tests checking out a book from the library.
     */
    @Test
    public void testCheckoutBook() {
        Library library = new Library();
        Book book = new Book("Java Programming", "Author Name", "ISBN123");
        Member member = new Member("Alice", "ID001");

        library.addBook(book);
        library.addMember(member);

        library.checkoutBook("ISBN123", "ID001");
        assertTrue("Book should be marked as checked out", book.isCheckedOut());
        assertTrue("Book should be in member's borrowed list", member.getBorrowedBooks().contains(book));
    }

    /**
     * Tests returning a book to the library.
     */
    @Test
    public void testReturnBook() {
        Library library = new Library();
        Book book = new Book("Java Programming", "Author Name", "ISBN123");
        Member member = new Member("Alice", "ID001");

        library.addBook(book);
        library.addMember(member);
        library.checkoutBook("ISBN123", "ID001");
        library.returnBook("ISBN123", "ID001");

        assertFalse("Book should not be checked out", book.isCheckedOut());
        assertFalse("Book should not be in member's borrowed list", member.getBorrowedBooks().contains(book));
    }

    /**
     * Tests shallow copying of the library.
     */
    @Test
    public void testShallowCopy() {
        Library library = new Library();
        Book book = new Book("Effective Java", "Joshua Bloch", "ISBN0001");
        library.addBook(book);

        Library shallowCopy = library.shallowCopy();
        shallowCopy.getBooks().get(0).setCheckedOut(true);

        assertTrue("Original should reflect changes in shallow copy", library.getBooks().get(0).isCheckedOut());
    }

    /**
     * Tests deep copying of the library.
     */
    @Test
    public void testDeepCopy() {
        Library library = new Library();
        Book book = new Book("Effective Java", "Joshua Bloch", "ISBN0001");
        library.addBook(book);

        Library deepCopy = library.deepCopy();
        deepCopy.getBooks().get(0).setCheckedOut(true);

        assertFalse("Original should not reflect changes in deep copy", library.getBooks().get(0).isCheckedOut());
    }

    /**
     * Tests the toString method of the Book class.
     */
    @Test
    public void testBookToString() {
        Book book = new Book("Effective Java", "Joshua Bloch", "ISBN0001");
        String expected = "Book{Title: Effective Java, Author: Joshua Bloch, ISBN: ISBN0001, Checked Out: false}";
        assertEquals("toString should return a formatted book description", expected, book.toString());
    }

    /**
     * Tests the toString method of the Member class.
     */
    @Test
    public void testMemberToString() {
        Member member = new Member("Alice", "ID001");
        String expected = "Member{Name: Alice, ID: ID001, Borrowed Books: []}";
        assertEquals("toString should return a formatted member description", expected, member.toString());
    }
}
