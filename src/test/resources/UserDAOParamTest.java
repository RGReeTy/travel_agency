package resources;

public final class UserDAOParamTest {

    private UserDAOParamTest() {
    }

    public static final String SELECT_BY_LOGIN = "SELECT * FROM testdb.users WHERE Login = ?";
    public static final String INSERT_FULL_INFO = "INSERT INTO testdb.users(id_User, Login, Password, Email, Firstname, Lastname, Phone, id_Discount, Level_access) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM testdb.users WHERE Login = ?";
    public static final String SELECT_DISCOUNT_BY_ID = "SELECT Size_of_discount FROM testdb.discount WHERE id_Discount = ?";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM testdb.users";
    public static final String SELECT_ID_LOGIN_LA = "SELECT Id_User, Login, Level_access FROM users ORDER BY Level_access";
    public static final String SELECT_USERS_BY_ID_USER = "SELECT * FROM testdb.users WHERE id_User= ?";
    public static final String COUNT_USERS_BY_LEVEL_ACCESS = "SELECT testdb.users.Level_access, " +
            "COUNT(testdb.users.Level_access) AS Count FROM users\n" +
            "GROUP BY Level_access ORDER BY Level_access";
    public static final String UPDATE_USER_STATUS = "UPDATE testdb.users SET Level_access=? WHERE id_User=?";
    public static final String UPDATE_USER_INFO = "UPDATE testdb.users SET Firstname=?, Lastname=?, Email=?, Phone=? WHERE Login=?";
    public static final String COUNT_TOTAL_MONEY_SPENT = "SELECT Count, Size_of_discount AS Discount FROM testdb.defrayal\n" +
            "JOIN testdb.discount ON defrayal.id_Discount = discount.id_Discount WHERE Id_User = ?";
}
