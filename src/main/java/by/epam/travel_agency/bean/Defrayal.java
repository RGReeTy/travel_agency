package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Defrayal.
 */
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
     * Gets date of payment.
     *
     * @return the date of payment
     */
    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    /**
     * Sets date of payment.
     *
     * @param dateOfPayment the date of payment
     */
    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    /**
     * Gets tour.
     *
     * @return the tour
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Sets tour.
     *
     * @param tour the tour
     */
    public void setTour(Tour tour) {
        this.tour = tour;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public BigDecimal getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(BigDecimal count) {
        this.count = count;
    }

    /**
     * Gets payment percentage.
     *
     * @return the payment percentage
     */
    public int getPaymentPercentage() {
        return paymentPercentage;
    }

    /**
     * Sets payment percentage.
     *
     * @param paymentPercentage the payment percentage
     */
    public void setPaymentPercentage(int paymentPercentage) {
        this.paymentPercentage = paymentPercentage;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
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
     * Gets final count.
     *
     * @return the final count
     */
    public BigDecimal getFinalCount() {
        return finalCount;
    }

    /**
     * Sets final count.
     *
     * @param finalCount the final count
     */
    public void setFinalCount(BigDecimal finalCount) {
        this.finalCount = finalCount;
    }

    /**
     * Gets annotation.
     *
     * @return the annotation
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * Sets annotation.
     *
     * @param annotation the annotation
     */
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
