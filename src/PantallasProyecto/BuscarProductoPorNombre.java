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

public class BuscarProductoPorNombre {

    public BuscarProductoPorNombre(JFrame frame) {
        // Configurar la ventana para buscar productos por nombre
        frame.getContentPane().removeAll();
        frame.setTitle("Buscar Producto por Nombre");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Buscar Producto por Nombre");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 20, 400, 30);
        frame.add(titleLabel);

        JLabel nombreLabel = new JLabel("Nombre del Producto:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setBounds(150, 80, 150, 25);
        frame.add(nombreLabel);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(300, 80, 200, 30);
        frame.add(nombreField);

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
                String nombre = nombreField.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                     PreparedStatement pst = con.prepareStatement("SELECT * FROM producto WHERE nombreProducto LIKE ?")) {

                    pst.setString(1, "%" + nombre + "%");
                    ResultSet rs = pst.executeQuery();

                    StringBuilder result = new StringBuilder();
                    while (rs.next()) {
                        result.append("Código: ").append(rs.getInt("codigoProducto")).append("\n");
                        result.append("Nombre: ").append(rs.getString("nombreProducto")).append("\n");
                        result.append("Precio: ").append(rs.getDouble("precioUnitario")).append("\n");
                        result.append("Cantidad: ").append(rs.getInt("cantidadProducto")).append("\n");
                        result.append("Fecha de Vencimiento: ").append(rs.getDate("fechaVencimiento")).append("\n\n");
                    }
                    resultadoArea.setText(result.toString().isEmpty() ? "No se encontraron productos con el nombre proporcionado." : result.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al buscar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
