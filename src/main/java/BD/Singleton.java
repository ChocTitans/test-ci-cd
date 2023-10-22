package BD;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {

    private static Singleton INSTANCE = null;
    private static Connection session = null;


    private Singleton()
    {
        try {

          /*  Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("le driver de mysql a ete charge avec succes");
            DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7609632","sql7609632","j2AtLu7Uyl");
            System.out.println("cnx etablie");*/

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("application.properties");

            if(config == null) throw new IOException("Fichier properties introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);

            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");

            session = DriverManager.getConnection(url,user,pass);

            System.out.println("cnx établie avec succès");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Singleton getINSTANCE()
    {
        if(INSTANCE == null) INSTANCE = new Singleton();
        return INSTANCE;
    }

    public static Connection getSession()
    {
        if(session == null) INSTANCE = new Singleton();
        return session;
    }

    public static void closeSession()
    {
        if(session != null)
        {
            try {
                session.close();
                System.out.println("Fermeture de session avec succès");
            }
            catch (SQLException e)
            {
                System.err.println("Fermeture de session échoué");
            }
        }
    }
}
