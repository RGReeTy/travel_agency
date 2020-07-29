package by.epam.travel_agency.dao.paramName;

public final class TourDAOParam {

    public TourDAOParam() {
    }

    public static final String SELECT_ALL_TOURS = "SELECT * FROM bustravelagency.tours";
    public static final String SELECT_ALL_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'," +
            "Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel";
    public static final String SELECT_CONCRETE_TOURS_JOIN = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            ",Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE TypeOfTour = ?";
    public static final String SELECT_TOUR_BY_ID = "SELECT id_Tour, tours.Title, TypeOfTour, Price, " +
            "Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'" +
            ",Description, tours.Url_wallpaper" +
            "        FROM bustravelagency.tours" +
            "        JOIN typeoftour ON Type=id_TypeOfTour" +
            "        JOIN discount ON tours.id_Discount = discount.id_Discount" +
            "        JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE id_Tour = ?";
    public static final String SELECT_ALL_HOTELS = "SELECT id_Hotel, Title, country, City, Stars, Free_rooms," +
            "Type, Min_price_per_room, Url_wallpaper FROM hotel JOIN nutrition ON Nutrition=id_Nutrition";
    public static final String SELECT_ALL_TOURS_BY_USER_ID = "SELECT tours.id_Tour, tours.Title, TypeOfTour, " +
            "Price, Size_of_discount, Hot_tour, Number_of_places, Date_start, Date_end, hotel.Title AS 'Hotel'," +
            " defrayal.Date_of_payment\n" +
            "FROM bustravelagency.tours JOIN typeoftour ON Type=id_TypeOfTour\n" +
            "JOIN discount ON tours.id_Discount = discount.id_Discount\n" +
            "JOIN defrayal ON defrayal.Id_Tour = tours.id_Tour\n" +
            "JOIN hotel ON tours.id_Hotel = hotel.id_Hotel WHERE Id_User = ?";
    public static final String SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_ID = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User,\n" +
            "      Login, Size_of_discount, Annotation FROM bustravelagency.defrayal JOIN tours ON  defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON defrayal.id_Discount=discount.id_Discount " +
            "JOIN users ON defrayal.Id_User=users.id_User WHERE defrayal.Id_User =  ?";
    public static final String SELECT_DEFRAYAL_BY_ID = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User,\n" +
            "      Login, Size_of_discount, Annotation FROM bustravelagency.defrayal JOIN tours ON  defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON defrayal.id_Discount=discount.id_Discount " +
            "JOIN users ON defrayal.Id_User=users.id_User WHERE defrayal.id_Defrayal =  ?";
    public static final String UPDATE_DEFRAYAL = "UPDATE defrayal SET Payment_percentage=?, Annotation=? WHERE id_Defrayal=?";
    public static final String SELECT_ALL_DEFRAYAL = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User, Login,\n" +
            "Size_of_discount, Annotation FROM bustravelagency.defrayal JOIN tours ON defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN users ON defrayal.Id_User=users.id_User\n" +
            "JOIN discount ON defrayal.id_Discount=discount.id_Discount ORDER BY Date_of_payment";
    public static final String SELECT_ALL_DEFRAYAL_WHERE_IS_DEBT = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, defrayal.Id_User, Login,\n" +
            "Size_of_discount, Annotation FROM bustravelagency.defrayal JOIN tours ON defrayal.Id_Tour=tours.id_Tour\n" +
            "JOIN users ON defrayal.Id_User=users.id_User JOIN discount ON defrayal.id_Discount=discount.id_Discount\n" +
            "    WHERE Payment_percentage!=100";
    public static final String SELECT_ALL_DEFRAYAL_FOR_USER_BY_USER_LOGIN = "SELECT id_Defrayal, Date_of_payment, Title, Count, Payment_percentage, Login,\n" +
            "       Size_of_discount, Annotation, users.id_User FROM bustravelagency.defrayal JOIN tours ON  defrayal.Id_Tour=tours.id_Tour\n" +
            "    JOIN discount ON defrayal.id_Discount=discount.id_Discount\n" +
            "    JOIN users ON defrayal.Id_User=users.id_User WHERE Login = ?";
    public final static String INSERT_NEW_TOUR = "INSERT INTO bustravelagency.tours(id_Tour, Title, Price, Type," +
            "Hot_tour, Number_of_places, Date_start, Date_end, id_Discount, id_Hotel, Description, Url_wallpaper) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    public final static String INSERT_NEW_DEFRAYAL = "INSERT INTO  defrayal(id_Defrayal, Date_of_payment, Id_Tour, " +
            "Count, Payment_percentage, Id_User, id_Discount, Annotation) VALUES (?,?,?,?,?,?,?,?)";
    public final static String FIND_MAX_VALUE_TOUR_ID = "SELECT MAX(id_Tour) FROM tours";
    public final static String FIND_MAX_VALUE_DEFRAYAL_ID = "SELECT MAX(id_Defrayal) FROM defrayal";
    public final static String GET_ALL_TYPES_OF_TOURS = "SELECT * FROM typeoftour";
    public final static String SELECT_ALL_DISCOUNTS = "SELECT * FROM discount";
}
