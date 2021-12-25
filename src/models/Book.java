package models;
import models.Product;

public class Book extends Product {
    private int numberOfPage;
    private int length;
    private int width;

    public Book() {

    }

    public Book(int id, String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status, int numberOfPage, int length, int width) {
        super(id, title, author, publicYear, publisher, importPrice, exportPrice, status);
        this.numberOfPage = numberOfPage;
        this.length = length;
        this.width = width;
    }

    public Book(String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status, int numberOfPage, int length, int width) {
        super(title, author, publicYear, publisher, importPrice, exportPrice, status);
        this.numberOfPage = numberOfPage;
        this.length = length;
        this.width = width;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return super.toString() + "|Number of page: " + numberOfPage + "|Length: " + length + "|Width: " + width ;
    }
}
