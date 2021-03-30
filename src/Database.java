class Database
{

    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password

    private Connection dbConnect;
    private ResultSet results;

    public Database(String DBURL, String USERNAME, String PASSWORD)
    {
        /**
         * @param DBURL,USERNAME,PASSWORD.
         *             Requires:
         *             DBURL - stores the database url information
         *             USERNAME - stores the user's account username
         *             PASSWORD - stores the user's account password.
         *             Does not use database but assists in connecting to SQL database
         *
         * @return returns nothing, since 3 arg constructor
         */

        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }
    public String getDburl()
    {
        /**
         * Does not use database
         * @return no params, returns String representation stored Data Base URL
         */

        return this.DBURL;
    }
    public String getUserName()
    {
        /**
         * Does not use database
         * @return no params, returns String representation of stored User Name
         */

        return this.USERNAME;
    }
    public String getPassword()
    {
        /**
         * Does not use database
         * @return no params, returns String representation of stored Password
         */

        return this.PASSWORD;
    }
    public void initializeConnection()
    {
        /**
         *  method tries to make a connection with the database URL and if the connection is not made,
         *  then the SQL exception is caught.
         */

        try
        {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "chad", );
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}

