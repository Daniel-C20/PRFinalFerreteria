package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngresoProducto {

    public IngresoProducto(JFrame frame) {
        // Configurar ventana para ingreso de productos
        frame.getContentPane().removeAll();
        frame.setTitle("Ingresar Producto");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Ingresar Nuevo Producto");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 30);
        frame.add(titleLabel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setBounds(200, 80, 100, 25);
        frame.add(nombreLabel);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(300, 80, 200, 30);
        frame.add(nombreField);

        JLabel precioLabel = new JLabel("Precio Unitario:");
        precioLabel.setForeground(Color.WHITE);
        precioLabel.setBounds(200, 130, 100, 25);
        frame.add(precioLabel);

        JTextField precioField = new JTextField();
        precioField.setBounds(300, 130, 200, 30);
        frame.add(precioField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setForeground(Color.WHITE);
        cantidadLabel.setBounds(200, 180, 100, 25);
        frame.add(cantidadLabel);

        JTextField cantidadField = new JTextField();
        cantidadField.setBounds(300, 180, 200, 30);
        frame.add(cantidadField);

        JLabel fechaLabel = new JLabel("Fecha de Vencimiento (yyyy-mm-dd):");
        fechaLabel.setForeground(Color.WHITE);
        fechaLabel.setBounds(50, 230, 250, 25);
        frame.add(fechaLabel);

        JTextField fechaField = new JTextField();
        fechaField.setBounds(300, 230, 200, 30);
        frame.add(fechaField);

        JButton ingresarButton = new JButton("Ingresar");
        ingresarButton.setBounds(350, 280, 100, 40);
        ingresarButton.setBackground(new Color(70, 130, 180));
        ingresarButton.setForeground(Color.WHITE);
        frame.add(ingresarButton);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                double precio;
                int cantidad;
                String fecha = fechaField.getText();

                try {
                    precio = Double.parseDouble(precioField.getText());
                    cantidad = Integer.parseInt(cantidadField.getText());

                    // Conexión a la base de datos
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                    String query = "INSERT INTO producto (nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, nombre);
                    pst.setDouble(2, precio);
                    pst.setInt(3, cantidad);
                    pst.setString(4, fecha.isEmpty() ? null : fecha);
                    pst.executeUpdate();
                    pst.close();
                    con.close();

                    JOptionPane.showMessageDialog(frame, "Producto ingresado correctamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores válidos para precio y cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al ingresar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
