package by.epam.travel_agency.bean;

/**
 * The enum Nutrition.
 */
public enum Nutrition {

    /**
     * Full nutrition.
     */
    FULL("full"),
    /**
     * Breakfast nutrition.
     */
    BREAKFAST("breakfast"),
    /**
     * Dinner nutrition.
     */
    DINNER("dinner"),
    /**
     * The Evening meal.
     */
    EVENING_MEAL("evening meal"),
    /**
     * The Breakfast and dinner.
     */
    BREAKFAST_AND_DINNER("breakfast and dinner"),
    /**
     * The Breakfast and evening dinner.
     */
    BREAKFAST_AND_EVENING_DINNER("breakfast and evening dinner"),
    /**
     * None nutrition.
     */
    NONE("none");

    private String nutrition;

    Nutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    Nutrition() {
    }

    /**
     * Nutrition string.
     *
     * @return the string
     */
    public String nutrition(){
        return nutrition;
    }

}
