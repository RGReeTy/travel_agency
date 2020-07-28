package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Tour.
 */
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

    /**
     * Instantiates a new Tour.
     */
    public Tour() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets type of tour.
     *
     * @return the type of tour
     */
    public String getTypeOfTour() {
        return typeOfTour;
    }

    /**
     * Sets type of tour.
     *
     * @param typeOfTour the type of tour
     */
    public void setTypeOfTour(String typeOfTour) {
        this.typeOfTour = typeOfTour;
    }

    /**
     * Is hot tour boolean.
     *
     * @return the boolean
     */
    public boolean isHotTour() {
        return hotTour;
    }

    /**
     * Sets hot tour.
     *
     * @param hotTour the hot tour
     */
    public void setHotTour(boolean hotTour) {
        this.hotTour = hotTour;
    }

    /**
     * Gets number of places.
     *
     * @return the number of places
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Sets number of places.
     *
     * @param numberOfPlaces the number of places
     */
    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    /**
     * Gets date start.
     *
     * @return the date start
     */
    public LocalDate getDateStart() {
        return dateStart;
    }

    /**
     * Sets date start.
     *
     * @param dateStart the date start
     */
    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Gets date end.
     *
     * @return the date end
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets date end.
     *
     * @param dateEnd the date end
     */
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Gets hotel.
     *
     * @return the hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets hotel.
     *
     * @param hotel the hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets url wallpaper.
     *
     * @return the url wallpaper
     */
    public String getUrlWallpaper() {
        return urlWallpaper;
    }

    /**
     * Sets url wallpaper.
     *
     * @param urlWallpaper the url wallpaper
     */
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
