package by.epam.travel_agency.dao.paramName;

public final class UserDAOParam {

    private UserDAOParam() {
    }

    public static final String SELECT_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";
    public static final String INSERT_FULL_INFO = "INSERT INTO bustravelagency.users(id_User, Login, Password, Email, Firstname, Lastname, Phone, id_Discount, Level_access) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM bustravelagency.users WHERE Login = ?";
    public static final String SELECT_DISCOUNT_BY_ID = "SELECT Size_of_discount FROM bustravelagency.discount WHERE id_Discount = ?";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM bustravelagency.users";
    public static final String SELECT_ID_LOGIN_LA = "SELECT Id_User, Login, Level_access FROM users ORDER BY Level_access";
    public static final String SELECT_USERS_BY_ID_USER = "SELECT * FROM bustravelagency.users WHERE id_User= ?";
    public static final String COUNT_USERS_BY_LEVEL_ACCESS = "SELECT bustravelagency.users.Level_access, " +
            "COUNT(bustravelagency.users.Level_access) AS Count FROM users\n" +
            "GROUP BY Level_access ORDER BY Level_access";
    public static final String UPDATE_USER_STATUS = "UPDATE users SET Level_access=? WHERE id_User=?";
    public static final String UPDATE_USER_INFO = "UPDATE users SET Firstname=?, Lastname=?, Email=?, Phone=? WHERE Login=?";
    public static final String COUNT_TOTAL_MONEY_SPENT = "SELECT Count, Size_of_discount AS Discount FROM defrayal\n" +
            "JOIN discount ON defrayal.id_Discount = discount.id_Discount WHERE Id_User = ?";
}
