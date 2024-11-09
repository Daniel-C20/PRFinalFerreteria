package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarProducto {

    public ActualizarProducto(JFrame frame) {
        // Configurar la ventana para actualizar productos
        frame.getContentPane().removeAll();
        frame.setTitle("Actualizar Producto");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Actualizar Producto");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 30);
        frame.add(titleLabel);

        JLabel codigoLabel = new JLabel("Código del Producto:");
        codigoLabel.setForeground(Color.WHITE);
        codigoLabel.setBounds(150, 80, 150, 25);
        frame.add(codigoLabel);

        JTextField codigoField = new JTextField();
        codigoField.setBounds(300, 80, 200, 30);
        frame.add(codigoField);

        JLabel nombreLabel = new JLabel("Nuevo Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setBounds(150, 130, 150, 25);
        frame.add(nombreLabel);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(300, 130, 200, 30);
        frame.add(nombreField);

        JLabel precioLabel = new JLabel("Nuevo Precio:");
        precioLabel.setForeground(Color.WHITE);
        precioLabel.setBounds(150, 180, 150, 25);
        frame.add(precioLabel);

        JTextField precioField = new JTextField();
        precioField.setBounds(300, 180, 200, 30);
        frame.add(precioField);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(350, 230, 100, 40);
        actualizarButton.setBackground(new Color(70, 130, 180));
        actualizarButton.setForeground(Color.WHITE);
        frame.add(actualizarButton);

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();
                String nuevoNombre = nombreField.getText();
                double nuevoPrecio;

                try {
                    nuevoPrecio = Double.parseDouble(precioField.getText());

                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                         PreparedStatement pst = con.prepareStatement("UPDATE producto SET nombreProducto = ?, precioUnitario = ? WHERE codigoProducto = ?")) {

                        pst.setString(1, nuevoNombre);
                        pst.setDouble(2, nuevoPrecio);
                        pst.setInt(3, Integer.parseInt(codigo));
                        int rowsAffected = pst.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Producto actualizado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "No se encontró un producto con el código proporcionado.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores válidos para el precio y el código.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
