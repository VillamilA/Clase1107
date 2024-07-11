import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class conexion extends JFrame {
    private JButton conectarALaBaseButton;
    private JPanel ConexionPanel;
    private JTextField eda;
    private JLabel nombreLabel;
    private JTextField nom;
    private JTextField nota1;
    private JTextField nota2;
    private JButton visualizarButton;

    public conexion() {
        super (" ");
        setTitle("Nueva conexion");
        setSize(400,500);
        setContentPane(ConexionPanel);
        conectarALaBaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    insertarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    visualizar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void conectar(){
        String url = "jdbc:mysql://localhost:3306/esfotventas";
        String user = "root";
        String password = "123456";

        try (Connection conecta = DriverManager.getConnection(url,user,password)) {
            if (conecta != null) {

                String nombre = nom.getText();
                String edad = eda.getText();
                String nota1n = nota1.getText();
                String nota2n = nota2.getText();

                String sql = "insert into estudiantes(nombre,edad,nota1,nota2) values (?,?,?,?)";
                PreparedStatement pstmt = conecta.prepareStatement(sql);
                pstmt.setString(1, nombre);
                pstmt.setInt(2, Integer.parseInt(edad));
                pstmt.setBigDecimal(3, new BigDecimal(nota1n));
                pstmt.setBigDecimal(4, new BigDecimal(nota2n));



                int rowsAffected = pstmt.executeUpdate();
                if((rowsAffected > 0)){
                    JOptionPane.showMessageDialog(null, "Insertado correctamente");
                }

                pstmt.close();
                conecta.close();
                JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
                System.out.println("CONEXION EXITOSA A LA BASE DE DATOS");
            }

            else {
                System.out.println("CONEXION RECHAZADA A LA BASE DE DATOS");

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void insertarDatos() throws SQLException {
        String nombre = nom.getText();
        String edad = eda.getText();
        String nota1n = nota1.getText();
        String nota2n = nota2.getText();
        Connection conecta = conectao();

        String sql = "insert into estudiantes(nombre,edad,nota1,nota2) values (?,?,?,?)";

        PreparedStatement pstmt = conecta.prepareStatement(sql);
        pstmt.setString(1, nombre);
        pstmt.setInt(2, Integer.parseInt(edad));
        pstmt.setBigDecimal(3, new BigDecimal(nota1n));
        pstmt.setBigDecimal(4, new BigDecimal(nota2n));



        int rowsAffected = pstmt.executeUpdate();
        if((rowsAffected > 0)){
            JOptionPane.showMessageDialog(null, "Insertado correctamente");
        }

        pstmt.close();
        conecta.close();
        JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
        System.out.println("CONEXION EXITOSA A LA BASE DE DATOS");
    }

public void visualizar() throws SQLException {
    String nombre = nom.getText();
    Connection visualconectado = conectao();
    String query = "Select * from estudiantes where nombre =?";
    PreparedStatement pstmt = visualconectado.prepareStatement(query);
    pstmt.setString(1, nombre);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        String edad = rs.getString("edad");
        JOptionPane.showMessageDialog(null, "El nombre " + nombre + "y la edad es " + edad);
    }
    rs.close();
    pstmt.close();
    visualconectado.close();
}

    public Connection conectao() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/esfotventas";
        String user = "root";
        String password = "123456";

        return DriverManager.getConnection(url,user,password);
    }
}

