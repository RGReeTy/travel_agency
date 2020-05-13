package by.epam.travel_agency.bean;

public enum Nutrition {

    NONE("none"),
    BREAKFAST("breakfast"),
    DINNER("dinner"),
    EVENING_MEAL("evening meal"),
    BREAKFAST_AND_DINNER("breakfast and dinner"),
    BREAKFAST_AND_EVENING_MEAL("breakfast and evening dinner"),
    FULL("full");

    String nutrition;

    Nutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    Nutrition() {
    }


}
