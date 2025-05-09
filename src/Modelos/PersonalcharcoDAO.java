package Modelos;

import Conexion.conexionBD;
import Entidad.Personalcharco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que maneja las operaciones de base de datos relacionadas con los empleados.
 * Permite agregar, eliminar, actualizar empleados y obtener todos los empleados.
 */
public class PersonalcharcoDAO {

    // Instanciamos un objeto de la clase conexion para poder conectarnos a la base de datos
    private Conexion.conexionBD conexionBD = new conexionBD();

    /**
     * Agrega un nuevo empleado a la base de datos.
     *
     * @param personalcharco El empleado a agregar.
     */
    public void agregar(Personalcharco personalcharco) {
        Connection con = conexionBD.getConnection();
        String query = "INSERT INTO empleados (nombre, cargo, salario) VALUES (?, ?, ?)";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, personalcharco.getNombre());
            pst.setString(2, personalcharco.getCargo());
            pst.setInt(3, personalcharco.getSalario());

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar empleado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param id El ID del empleado a eliminar.
     */
    public void eliminar(int id) {
        Connection con = conexionBD.getConnection();
        String query = "DELETE FROM empleados WHERE id_empleado = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Personal eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar personal.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza la información de un empleado en la base de datos.
     *
     * @param personalcharco El empleado con los nuevos datos.
     */
    public void actualizar(Personalcharco personalcharco) {
        Connection con = conexionBD.getConnection();
        String query = "UPDATE empleados SET nombre = ?, cargo = ?, salario = ? WHERE id_empleado = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, personalcharco.getNombre());
            pst.setString(2, personalcharco.getCargo());
            pst.setDouble(3, personalcharco.getSalario());
            pst.setInt(4, personalcharco.getId());

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Personal actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar personal.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Obtiene todos los empleados registrados en la base de datos.
     *
     * @return Lista de empleados.
     */
    public ArrayList<Personalcharco> obtenerTodos() {
        ArrayList<Personalcharco> lista = new ArrayList<>();
        Connection con = conexionBD.getConnection();
        String query = "SELECT id_empleado, nombre FROM empleados";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Personalcharco empleado = new Personalcharco();
                empleado.setId(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                lista.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
}
