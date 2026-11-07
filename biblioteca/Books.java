public class Books {
    private String title;
    private String author;
    private int year;
    private boolean rented;

    public Books(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rented = false;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
