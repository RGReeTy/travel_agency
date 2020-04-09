package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = -7483740798840490264L;
    private int id;
    private String username;
    private String createdAt;
    private int count;

    public Order() {
    }

    public Order(int id, String createdAt, int count) {
        this.id = id;
        this.createdAt = createdAt;
        this.count = count;
    }

    public Order(int id, String username, String createdAt, int count) {
        super();
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.count, count) == 0 &&
                Objects.equals(username, order.username) &&
                Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, createdAt, count);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", count=" + count +
                '}';
    }
}
