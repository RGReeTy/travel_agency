package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Defrayal implements Serializable {

    private static final long serialVersionUID = 3998642067837688230L;

    private int id;
    private LocalDate dateOfPayment;
    private Tour tour;
    private BigDecimal count;
    private int paymentPercentage;
    private User user;
    private int discount;
    private BigDecimal finalCount;
    private String annotation;

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

    public BigDecimal getFinalCount() {
        return finalCount;
    }

    public void setFinalCount(BigDecimal finalCount) {
        this.finalCount = finalCount;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Defrayal defrayal = (Defrayal) o;
        return id == defrayal.id &&
                paymentPercentage == defrayal.paymentPercentage &&
                discount == defrayal.discount &&
                Objects.equals(dateOfPayment, defrayal.dateOfPayment) &&
                Objects.equals(tour, defrayal.tour) &&
                Objects.equals(count, defrayal.count) &&
                Objects.equals(user, defrayal.user) &&
                Objects.equals(finalCount, defrayal.finalCount) &&
                Objects.equals(annotation, defrayal.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfPayment, tour, count, paymentPercentage, user, discount, finalCount, annotation);
    }

    @Override
    public String toString() {
        return "Defrayal{" +
                "id=" + id +
                ", dateOfPayment=" + dateOfPayment +
                ", tour=" + tour +
                ", count=" + count +
                ", paymentPercentage=" + paymentPercentage +
                ", user=" + user +
                ", discount=" + discount +
                ", finalCount=" + finalCount +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
