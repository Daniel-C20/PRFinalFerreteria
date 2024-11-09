package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TransaccionVenta {

    public TransaccionVenta(JFrame frame) {
        // Configurar la ventana para transacciones de venta
        frame.getContentPane().removeAll();
        frame.setTitle("Transacción de Venta");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Transacción de Venta");
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

        JLabel cantidadLabel = new JLabel("Cantidad a Vender:");
        cantidadLabel.setForeground(Color.WHITE);
        cantidadLabel.setBounds(150, 130, 150, 25);
        frame.add(cantidadLabel);

        JTextField cantidadField = new JTextField();
        cantidadField.setBounds(300, 130, 200, 30);
        frame.add(cantidadField);

        JButton venderButton = new JButton("Vender");
        venderButton.setBounds(350, 200, 100, 40);
        venderButton.setBackground(new Color(70, 130, 180));
        venderButton.setForeground(Color.WHITE);
        frame.add(venderButton);

        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();
                int cantidadVender;

                try {
                    cantidadVender = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678")) {
                    con.setAutoCommit(false);

                    // Verificar la existencia del producto
                    String selectQuery = "SELECT cantidadProducto, precioUnitario FROM producto WHERE codigoProducto = ?";
                    try (PreparedStatement selectPst = con.prepareStatement(selectQuery)) {
                        selectPst.setString(1, codigo);
                        ResultSet rs = selectPst.executeQuery();

                        if (rs.next()) {
                            int cantidadExistente = rs.getInt("cantidadProducto");
                            double precioUnitario = rs.getDouble("precioUnitario");

                            if (cantidadExistente >= cantidadVender) {
                                // Calcular el total de la venta
                                double totalVenta = cantidadVender * precioUnitario;

                                // Actualizar el inventario
                                String updateQuery = "UPDATE producto SET cantidadProducto = cantidadProducto - ? WHERE codigoProducto = ?";
                                try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                                    updatePst.setInt(1, cantidadVender);
                                    updatePst.setString(2, codigo);
                                    updatePst.executeUpdate();
                                }

                                con.commit();
                                JOptionPane.showMessageDialog(frame, "Venta realizada exitosamente. Total: Q" + totalVenta);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Cantidad insuficiente en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No se encontró un producto con el código proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error durante la transacción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
