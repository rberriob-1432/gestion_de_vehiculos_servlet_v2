package gestion_vehiculo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String HOST =
            System.getenv("DB_HOST");

    private static final String PORT =
            System.getenv("DB_PORT");

    private static final String DATABASE =
            System.getenv("DB_NAME");

    private static final String USER =
            System.getenv("DB_USER");

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    private static final String URL =
            "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encontró el driver de PostgreSQL",
                    e
            );
        }

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
