package models;
import models.Product;

public class Dvd extends Product{
    private int size;
    private int duration;

    public Dvd() {

    }

    public Dvd(int id, String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status, int size, int duration) {
        super(id, title, author, publicYear, publisher, importPrice, exportPrice, status);
        this.size = size;
        this.duration = duration;
    }

    public Dvd(String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, boolean status, int size, int duration) {
        super(title, author, publicYear, publisher, importPrice, exportPrice, status);
        this.size = size;
        this.duration = duration;
    }

    public Dvd(String title, String author, int publicYear, String publisher, int importPrice, int exportPrice, int size, int duration) {
        super(title, author, publicYear, publisher, importPrice, exportPrice);
        this.size = size;
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString() + "|Size: " + size + "|Duration: " + duration;
    }
}
