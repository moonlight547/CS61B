public class Book {
}
class Book {
    public String title;
    public Library library;
    public static Book last = null;

    public Book(String name) {
        title = name;
        last = this;
        library = null;
    }

    public static String lastBookTitle() {
        return last.title;
    }
    public String getTitle() {
        return title;
    }
}
class Library {
    public Book[] books;
    public int index;
    public  int totalBooks = 0;

    public static Library(int size) {
        books = new Book[size];
        index = 0;
    }

    public void  addBook(Book book) {
        books[index] = book;
        index++;
        totalBooks++;
        book.library = this;
    }

    public void lastBookTitle(Book book) {
        books[index] = book;
        index++;
        totalBooks++;
        book.library = this;
    }
}

System.out.println(Library.totalBooks);
