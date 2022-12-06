package cu.edu.cujae.pweb.dto;



public class BookAuthorDto {
    private BookDto book;
    private AuthorDto author;


    public BookAuthorDto(BookDto book, AuthorDto author) {
        this.book = book;
        this.author = author;

    }

    public BookAuthorDto() {
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }



}
