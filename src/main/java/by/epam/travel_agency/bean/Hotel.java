package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.util.Objects;

public class Hotel implements Serializable {

    private static final long serialVersionUID = 147695978580801694L;
    private int id;
    private String title;
    private byte stars;
    private int freeRooms;
    private Nutrition nutrition;

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

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
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
                nutrition == hotel.nutrition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, stars, freeRooms, nutrition);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", stars=" + stars +
                ", freeRooms=" + freeRooms +
                ", nutrition=" + nutrition +
                '}';
    }
}
