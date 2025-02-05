import java.util.ArrayList;
import java.util.List;
 
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }
    
    public void addMember(Member member) {
        members.add(member);
    }
    
    public void checkoutBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);
        if (book != null && member != null && !book.isCheckedOut()) {
            book.setCheckedOut(true);
            member.borrowBook(book);
        }
    }
    
    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.isCheckedOut()) {
            book.setCheckedOut(false);
            member.returnBook(book);
        }
    }
    
    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    private Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public Library shallowCopy() {
        Library copy = new Library();
        copy.books = this.books; // Shallow copy of the list (same list instance)
        copy.members = this.members;
        return copy;
    }
    
    public Library deepCopy() {
        Library copy = new Library();
        // Deep copy of books
        for (Book book : this.books) {
            Book newBook = new Book(book.getTitle(), book.getAuthor(), book.getIsbn());
            newBook.setCheckedOut(book.isCheckedOut());
            copy.addBook(newBook);
        }
        // Deep copy of members
        for (Member member : this.members) {
            Member newMember = new Member(member.getName(), member.getId());
            // Copy borrowed books
            for (Book borrowedBook : member.getBorrowedBooks()) {
                // Need to find the corresponding book in the copied books
                Book newBorrowedBook = copy.findBookByIsbn(borrowedBook.getIsbn());
                newMember.borrowBook(newBorrowedBook);
            }
            copy.addMember(newMember);
        }
        return copy;
    }
}
