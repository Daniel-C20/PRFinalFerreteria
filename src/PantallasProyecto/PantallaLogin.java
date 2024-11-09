package PantallasProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaLogin {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public PantallaLogin() {
        frame = new JFrame("Inicio de Sesión");
        frame.setSize(700, 475);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - frame.getWidth()) / 2;
        int y = (pantalla.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        
        int centerX = frame.getWidth() / 2;

        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(centerX - 100, 40, 200, 30);
        frame.add(titleLabel);

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(130, 120, 80, 25);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(centerX - 50, 120, 150, 25);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(130, 170, 80, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX - 50, 170, 150, 25);
        frame.add(passwordField);

        JButton loginButton = new JButton("Ingresar");
        loginButton.setBounds(centerX - 50, 220, 100, 30);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Open Sans", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Verificación de usuario y contraseña
                if (username.equals("Daniel") && password.equals("1234")) {
                    frame.dispose();
                    new MenuPrincipal(); // Ir al menú principal al iniciar sesión
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(loginButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PantallaLogin(); // Iniciar la aplicación desde la pantalla de login
    }
}
