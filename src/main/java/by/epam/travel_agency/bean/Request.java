package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Request implements Serializable {

    private static final long serialVersionUID = 9018034227623488171L;

    private int id;
    private LocalDate dateOfPayment;
    private Tour tour;
    private BigDecimal count;
    private int paymentPercentage;
    private User user;
    private int discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public int getPaymentPercentage() {
        return paymentPercentage;
    }

    public void setPaymentPercentage(int paymentPercentage) {
        this.paymentPercentage = paymentPercentage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                paymentPercentage == request.paymentPercentage &&
                discount == request.discount &&
                Objects.equals(dateOfPayment, request.dateOfPayment) &&
                Objects.equals(tour, request.tour) &&
                Objects.equals(count, request.count) &&
                Objects.equals(user, request.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfPayment, tour, count, paymentPercentage, user, discount);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", dateOfPayment=" + dateOfPayment +
                ", tour=" + tour +
                ", count=" + count +
                ", paymentPercentage=" + paymentPercentage +
                ", user=" + user +
                ", discount=" + discount +
                '}';
    }
}
