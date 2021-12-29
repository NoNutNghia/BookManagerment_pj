package controller.request.book;

public class SearchBookRequest {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public SearchBookRequest(String title, String author, Integer publicYear) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
    }

    public Integer getPublicYear() {
        return publicYear;
    }

    public void setPublicYear(int publicYear) {
        this.publicYear = publicYear;
    }

    private String title;
    private String author;
    private Integer publicYear;
}
