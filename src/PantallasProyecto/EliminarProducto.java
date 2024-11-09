package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarProducto {

    public EliminarProducto(JFrame frame) {
        // Configurar la ventana para eliminar productos
        frame.getContentPane().removeAll();
        frame.setTitle("Eliminar Producto");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Eliminar Producto");
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

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(350, 130, 100, 40);
        eliminarButton.setBackground(new Color(70, 130, 180));
        eliminarButton.setForeground(Color.WHITE);
        frame.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                     PreparedStatement pst = con.prepareStatement("DELETE FROM producto WHERE codigoProducto = ?")) {

                    pst.setInt(1, Integer.parseInt(codigo));
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Producto eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "No se encontró un producto con el código proporcionado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
