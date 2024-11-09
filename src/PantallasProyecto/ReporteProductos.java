package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ReporteProductos {

    public ReporteProductos(JFrame frame) {
        // Configurar la ventana para mostrar el reporte de productos
        frame.getContentPane().removeAll();
        frame.setTitle("Reporte de Productos");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Reporte de Productos");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 30);
        frame.add(titleLabel);

        JTextArea reporteArea = new JTextArea();
        reporteArea.setFont(new Font("Open Sans", Font.PLAIN, 14));
        reporteArea.setForeground(Color.WHITE);
        reporteArea.setBackground(new Color(30, 30, 30));
        reporteArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reporteArea);
        scrollPane.setBounds(100, 80, 500, 300);
        frame.add(scrollPane);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT codigoProducto, nombreProducto, precioUnitario, cantidadProducto FROM producto")) {

            StringBuilder reporte = new StringBuilder();
            while (rs.next()) {
                reporte.append("Código: ").append(rs.getInt("codigoProducto")).append("\n");
                reporte.append("Nombre: ").append(rs.getString("nombreProducto")).append("\n");
                reporte.append("Precio Unitario: Q").append(rs.getDouble("precioUnitario")).append("\n");
                reporte.append("Existencia: ").append(rs.getInt("cantidadProducto")).append("\n\n");
            }

            reporteArea.setText(reporte.toString().isEmpty() ? "No hay productos registrados." : reporte.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.revalidate();
        frame.repaint();
    }
}
