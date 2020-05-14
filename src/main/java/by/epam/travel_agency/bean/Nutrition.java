package by.epam.travel_agency.bean;

public enum Nutrition {

    FULL("full"),
    BREAKFAST("breakfast"),
    DINNER("dinner"),
    EVENING_MEAL("evening meal"),
    BREAKFAST_AND_DINNER("breakfast and dinner"),
    BREAKFAST_AND_EVENING_DINNER("breakfast and evening dinner"),
    NONE("none");

    private String nutrition;

    Nutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    Nutrition() {
    }

    public String nutrition(){
        return nutrition;
    }

}
