

// VU DUC DAI
package models;

public class Product {
    private int id;
    private String title;
    private String author;
    private int publicYear;
    private String publisher;
    private int importPrice;
    private int exportPrice;
    private boolean status;

    public Product() {

    }

    public Product(int id, String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.publisher = publisher;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.status = status;
    }

    public Product(String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.publisher = publisher;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.status = status;
    }

    public Product(String title, String author, int publicYear, String publisher, int importPrice, int exportPrice) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.publisher = publisher;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPublicYear() {
        return publicYear;
    }

    public void setPublicYear(int publicYear) {
        this.publicYear = publicYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(int exportPrice) {
        this.exportPrice = exportPrice;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return "|ID: " + id + "|Title: " + title + "|Author: " + author + "|Public year: " + publicYear + "|Publisher: " + publisher + "|Import price: " + importPrice + "|Export price: " + exportPrice;
    }
}