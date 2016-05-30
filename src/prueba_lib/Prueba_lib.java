
package prueba_lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author julian
 */
public class Prueba_lib {

    /**
     * @param args the command line arguments
     */
    /**
     *
     * @author julian
     */
    static Connection con = null;
    static Statement s = null;
    public static String db = "base1";
    public static String url = "jdbc:mysql://localhost/" + db;
    public static String user = "root";
    public static String pass = "mi_pass";

    /**
     * Método conectar que sirve conectar con las base de datos.
     *
     * @exception ClassNotFoundException
     * @exception SQLException
     */
    public static void Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println("error al cargar el driver");
        } catch (SQLException ex) {
            System.out.println("error al conectarse a la base");
        }
    }

    /**
     * Metodo de borrado
     *
     * @exception Exception e
     */
    public static void borrar() {
        try {
            s = con.createStatement();
            String nombre = JOptionPane.showInputDialog("nombre del alumno que vas a borrar");
            s.execute("delete from Alumnos where Nombre='" + nombre + "';");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Metodo para modificar un registro de la tabla
     *
     * @exception Exception
     */
    public static void Modificar() {
        try {
            s = con.createStatement();
            String nom = JOptionPane.showInputDialog("Nombre del registro que quieres modificar");
            String ape = JOptionPane.showInputDialog("Apellido nuevo");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad nueva"));

            s.execute("update Alumnos set Edad=" + edad + ",Apellidos='" + ape + "'where Nombre='" + nom + "';");

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    /**
     * Metodo para insertar registros en la base de datos
     *
     * @param edad
     * @param nombre
     * @param apellidos
     */
    public static void insertar(int edad, String nombre, String apellidos) {
        try {

            s = con.createStatement();

            s.executeUpdate("insert into Alumnos values('" + nombre + "','" + apellidos + "'," + edad + ");");
            JOptionPane.showMessageDialog(null, "Insercion realizada");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para visualizar todos lo campos de la tabla
     *
     * @exception
     */
    public static void visualizar() {
        try {
            s = con.createStatement();
            ResultSet r = s.executeQuery("select * from Alumnos");

            while (r.next()) {
                //System.out.println(r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
                JOptionPane.showMessageDialog(null, r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
            }
            r.close();

        } catch (Exception e) {
            System.out.println("ERROR ---> " + e);
        }
    }

}
