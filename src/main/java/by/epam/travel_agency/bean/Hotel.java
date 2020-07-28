package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Hotel.
 */
public class Hotel implements Serializable {

    private static final long serialVersionUID = -7475479132330242591L;
    private int id;
    private String title;
    private String country;
    private String city;
    private byte stars;
    private int freeRooms;
    private String nutrition;
    private BigDecimal minPricePerRoom;
    private String urlWallpaper;

    /**
     * Instantiates a new Hotel.
     */
    public Hotel() {
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
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets stars.
     *
     * @return the stars
     */
    public byte getStars() {
        return stars;
    }

    /**
     * Sets stars.
     *
     * @param stars the stars
     */
    public void setStars(byte stars) {
        this.stars = stars;
    }

    /**
     * Gets free rooms.
     *
     * @return the free rooms
     */
    public int getFreeRooms() {
        return freeRooms;
    }

    /**
     * Sets free rooms.
     *
     * @param freeRooms the free rooms
     */
    public void setFreeRooms(int freeRooms) {
        this.freeRooms = freeRooms;
    }

    /**
     * Gets nutrition.
     *
     * @return the nutrition
     */
    public String getNutrition() {
        return nutrition;
    }

    /**
     * Sets nutrition.
     *
     * @param nutrition the nutrition
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * Gets min price per room.
     *
     * @return the min price per room
     */
    public BigDecimal getMinPricePerRoom() {
        return minPricePerRoom;
    }

    /**
     * Sets min price per room.
     *
     * @param minPricePerRoom the min price per room
     */
    public void setMinPricePerRoom(BigDecimal minPricePerRoom) {
        this.minPricePerRoom = minPricePerRoom;
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
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                stars == hotel.stars &&
                freeRooms == hotel.freeRooms &&
                Objects.equals(title, hotel.title) &&
                Objects.equals(country, hotel.country) &&
                Objects.equals(city, hotel.city) &&
                Objects.equals(nutrition, hotel.nutrition) &&
                Objects.equals(minPricePerRoom, hotel.minPricePerRoom) &&
                Objects.equals(urlWallpaper, hotel.urlWallpaper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, country, city, stars, freeRooms, nutrition, minPricePerRoom, urlWallpaper);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", stars=" + stars +
                ", freeRooms=" + freeRooms +
                ", nutrition='" + nutrition + '\'' +
                ", minPricePerRoom=" + minPricePerRoom +
                ", urlWallpaper='" + urlWallpaper + '\'' +
                '}';
    }
}
