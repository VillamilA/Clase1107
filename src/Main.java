import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /*String url = "jdbc:mysql://localhost:3306/esfotventas";
       /String user = "root";
        String password = "123456";

        try (Connection conecta = DriverManager.getConnection(url,user,password)) {
            if (conecta != null) {
                System.out.println("CONEXION EXITOSA A LA BASE DE DATOS");

            } else {
                System.out.println("CONEXION RECHAZADA A LA BASE DE DATOS");

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }*/
    conexion conectado = new conexion();
    conectado.setVisible(true);


    }
}