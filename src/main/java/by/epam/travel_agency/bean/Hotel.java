package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Hotel() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte getStars() {
        return stars;
    }

    public void setStars(byte stars) {
        this.stars = stars;
    }

    public int getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(int freeRooms) {
        this.freeRooms = freeRooms;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public BigDecimal getMinPricePerRoom() {
        return minPricePerRoom;
    }

    public void setMinPricePerRoom(BigDecimal minPricePerRoom) {
        this.minPricePerRoom = minPricePerRoom;
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
