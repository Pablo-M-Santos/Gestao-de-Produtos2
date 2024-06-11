package Visao;

import Controle.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

    private JPanel TelaLogin;
    private JLabel opcoes;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton cancelarButton;


    public Login() {
        setContentPane(TelaLogin);
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Configurar placeholders
        configurePlaceholders();

        // Criar um JPopupMenu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem2 = new JMenuItem("Login");
        JMenuItem menuItem3 = new JMenuItem("Cadastro");

        // Adicionar os itens de menu ao JPopupMenu
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);

        // ActionListener para menuItem2 (Login)
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cadastro();
                dispose();
            }
        });

        // Adicionar um ouvinte de mouse à JLabel "opcoes" para exibir o menu pop-up quando o botão direito do mouse for clicado
        opcoes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popupMenu.show(opcoes, e.getX(), e.getY());
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String email = textField2.getText();
                    String senha = new String(passwordField1.getPassword());

                if ("admin".equals(email) && "admin".equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo, administrador!");
                    new adminHome(); // Abre a tela de administrador
                    dispose(); // Fecha a tela de login
                } else if (Conexao.verificarLogin(email, senha)) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    new Home(); // Abre a tela Home
                    dispose(); // Fecha a tela de login
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetarPlaceholder();
            }
        });
    }

    private void resetarPlaceholder() {
        resetarPlaceholder(textField2, "Digite seu Email: ");
        if (String.valueOf(passwordField1.getPassword()).equals("Digite sua Senha: ")) {
            passwordField1.setEchoChar((char) 0);
        }
        resetarPlaceholder(passwordField1, "Digite sua Senha: ");
    }


    private void resetarPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
    }

    private void configurePlaceholders() {
        Color placeholderColor = new Color(128, 128, 128); // Usar um tom de cinza mais claro

        textField2.setForeground(placeholderColor);
        textField2.setText("Digite seu Email: ");
        textField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Digite seu Email: ")) {
                    textField2.setText("");
                    textField2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setForeground(placeholderColor);
                    textField2.setText("Digite seu Email: ");
                }
            }
        });

        passwordField1.setForeground(placeholderColor);
        passwordField1.setEchoChar((char) 0);
        passwordField1.setText("Digite sua Senha: ");
        passwordField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField1.getPassword()).equals("Digite sua Senha: ")) {
                    passwordField1.setText("");
                    passwordField1.setEchoChar('*');
                    passwordField1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField1.getPassword()).isEmpty()) {
                    passwordField1.setForeground(placeholderColor);
                    passwordField1.setText("Digite sua Senha: ");
                    passwordField1.setEchoChar((char) 0);
                }
            }
        });
    }
}
