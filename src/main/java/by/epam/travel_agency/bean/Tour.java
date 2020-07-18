package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Tour implements Serializable {

    private static final long serialVersionUID = -7400151352943332396L;
    private int id;
    private String title;
    private BigDecimal price;
    private String typeOfTour;
    private boolean hotTour;
    private int numberOfPlaces;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int discount;
    private Hotel hotel;
    private String description;
    private String urlWallpaper;

    public Tour() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTypeOfTour() {
        return typeOfTour;
    }

    public void setTypeOfTour(String typeOfTour) {
        this.typeOfTour = typeOfTour;
    }

    public boolean isHotTour() {
        return hotTour;
    }

    public void setHotTour(boolean hotTour) {
        this.hotTour = hotTour;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlWallpaper() {
        return urlWallpaper;
    }

    public void setUrlWallpaper(String urlWallpaper) {
        this.urlWallpaper = urlWallpaper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id &&
                hotTour == tour.hotTour &&
                numberOfPlaces == tour.numberOfPlaces &&
                discount == tour.discount &&
                Objects.equals(title, tour.title) &&
                Objects.equals(price, tour.price) &&
                Objects.equals(typeOfTour, tour.typeOfTour) &&
                Objects.equals(dateStart, tour.dateStart) &&
                Objects.equals(dateEnd, tour.dateEnd) &&
                Objects.equals(hotel, tour.hotel) &&
                Objects.equals(description, tour.description) &&
                Objects.equals(urlWallpaper, tour.urlWallpaper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, typeOfTour, hotTour, numberOfPlaces, dateStart, dateEnd, discount, hotel, description, urlWallpaper);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", typeOfTour='" + typeOfTour + '\'' +
                ", hotTour=" + hotTour +
                ", numberOfPlaces=" + numberOfPlaces +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", discount=" + discount +
                ", hotel=" + hotel +
                ", description='" + description + '\'' +
                ", url_wallpaper='" + urlWallpaper + '\'' +
                '}';
    }
}
