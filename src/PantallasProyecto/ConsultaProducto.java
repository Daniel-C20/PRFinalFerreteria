package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaProducto {

    public ConsultaProducto(JFrame frame) {
        // Configurar la ventana para consultar productos
        frame.getContentPane().removeAll();
        frame.setTitle("Consulta de Productos");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Consulta de Productos", JLabel.CENTER);
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Open Sans", Font.PLAIN, 14));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton consultarButton = new JButton("Consultar");
        consultarButton.setBackground(new Color(70, 130, 180));
        consultarButton.setForeground(Color.WHITE);
        frame.add(consultarButton, BorderLayout.SOUTH);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDNegocio", "root", "12345678");
                     Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM producto")) {

                    StringBuilder result = new StringBuilder();
                    while (rs.next()) {
                        result.append("Código: ").append(rs.getInt("codigoProducto")).append("\n");
                        result.append("Nombre: ").append(rs.getString("nombreProducto")).append("\n");
                        result.append("Precio: ").append(rs.getDouble("precioUnitario")).append("\n");
                        result.append("Cantidad: ").append(rs.getInt("cantidadProducto")).append("\n");
                        result.append("Fecha de Vencimiento: ").append(rs.getDate("fechaVencimiento")).append("\n\n");
                    }
                    textArea.setText(result.toString().isEmpty() ? "No hay productos para mostrar." : result.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al consultar productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
}
