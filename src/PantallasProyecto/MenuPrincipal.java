package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    private JFrame frame;

    public MenuPrincipal() {
        frame = new JFrame("Menú Principal");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - frame.getWidth()) / 2;
        int y = (pantalla.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        JLabel titleLabel = new JLabel("Menú Principal");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(300, 20, 300, 40);
        frame.add(titleLabel);

        JButton ingresarButton = new JButton("Ingresar Producto");
        ingresarButton.setBounds(50, 100, 200, 50);
        configureButton(ingresarButton);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IngresoProducto(frame);
            }
        });
        frame.add(ingresarButton);

        JButton consultarButton = new JButton("Consultar Producto");
        consultarButton.setBounds(300, 100, 200, 50);
        configureButton(consultarButton);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaProducto(frame);
            }
        });
        frame.add(consultarButton);

        JButton actualizarButton = new JButton("Actualizar Producto");
        actualizarButton.setBounds(550, 100, 200, 50);
        configureButton(actualizarButton);
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ActualizarProducto(frame);
            }
        });
        frame.add(actualizarButton);

        JButton eliminarButton = new JButton("Eliminar Producto");
        eliminarButton.setBounds(50, 200, 200, 50);
        configureButton(eliminarButton);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarProducto(frame);
            }
        });
        frame.add(eliminarButton);

        JButton buscarCodigoButton = new JButton("Buscar por Código");
        buscarCodigoButton.setBounds(300, 200, 200, 50);
        configureButton(buscarCodigoButton);
        buscarCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarProductoPorCodigo(frame);
            }
        });
        frame.add(buscarCodigoButton);

        JButton buscarNombreButton = new JButton("Buscar por Nombre");
        buscarNombreButton.setBounds(550, 200, 200, 50);
        configureButton(buscarNombreButton);
        buscarNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarProductoPorNombre(frame);
            }
        });
        frame.add(buscarNombreButton);

        JButton transaccionButton = new JButton("Realizar Venta");
        transaccionButton.setBounds(50, 300, 200, 50);
        configureButton(transaccionButton);
        transaccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransaccionVenta(frame);
            }
        });
        frame.add(transaccionButton);

        JButton reporteButton = new JButton("Generar Reporte");
        reporteButton.setBounds(300, 300, 200, 50);
        configureButton(reporteButton);
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReporteProductos(frame);
            }
        });
        frame.add(reporteButton);

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setBounds(550, 300, 200, 50);
        configureButton(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PantallaLogin();
            }
        });
        frame.add(logoutButton);

        frame.setVisible(true);
    }

    private void configureButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Open Sans", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        new PantallaLogin(); // Iniciar desde la pantalla de login
    }
}
