package clases;

import java.sql.*;

public class DBConexion {

    public static Connection conexion() {
        try {

            String dataBase = "chat";

            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/" + dataBase, "root", "");
            return cn;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
