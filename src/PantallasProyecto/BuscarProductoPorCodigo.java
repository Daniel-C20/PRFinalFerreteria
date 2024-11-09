package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarProductoPorCodigo {

    public BuscarProductoPorCodigo(JFrame frame) {
        // Configurar la ventana para buscar productos por código
        frame.getContentPane().removeAll();
        frame.setTitle("Buscar Producto por Código");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Buscar Producto por Código");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 20, 400, 30);
        frame.add(titleLabel);

        JLabel codigoLabel = new JLabel("Código del Producto:");
        codigoLabel.setForeground(Color.WHITE);
        codigoLabel.setBounds(150, 80, 150, 25);
        frame.add(codigoLabel);

        JTextField codigoField = new JTextField();
        codigoField.setBounds(300, 80, 200, 30);
        frame.add(codigoField);

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setFont(new Font("Open Sans", Font.PLAIN, 14));
        resultadoArea.setForeground(Color.WHITE);
        resultadoArea.setBackground(new Color(30, 30, 30));
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setBounds(150, 130, 400, 200);
        frame.add(scrollPane);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(350, 350, 100, 40);
        buscarButton.setBackground(new Color(70, 130, 180));
        buscarButton.setForeground(Color.WHITE);
        frame.add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                     PreparedStatement pst = con.prepareStatement("SELECT * FROM producto WHERE codigoProducto = ?")) {

                    pst.setInt(1, Integer.parseInt(codigo));
                    ResultSet rs = pst.executeQuery();

                    StringBuilder result = new StringBuilder();
                    if (rs.next()) {
                        result.append("Código: ").append(rs.getInt("codigoProducto")).append("\n");
                        result.append("Nombre: ").append(rs.getString("nombreProducto")).append("\n");
                        result.append("Precio: ").append(rs.getDouble("precioUnitario")).append("\n");
                        result.append("Cantidad: ").append(rs.getInt("cantidadProducto")).append("\n");
                        result.append("Fecha de Vencimiento: ").append(rs.getDate("fechaVencimiento")).append("\n");
                    } else {
                        result.append("No se encontró un producto con el código proporcionado.");
                    }
                    resultadoArea.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al buscar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
