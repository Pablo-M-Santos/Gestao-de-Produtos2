package Visao;

import Controle.Conexao;
import Modelo.CadastroLogica;
import Controle.CadastroControle;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cadastro extends JFrame {

    private JPanel TelaCadastro;
    private JLabel opcoes;
    private JTextField NomeInput;
    private JTextField EmailInput;
    private JTextField CPFInput;
    private JPasswordField SenhaInput;
    private JButton cadastrarButton;
    private JButton cancelarButton;
    private JComboBox<String> userTypeComboBox;
    private JPasswordField ConfirmarSenha;

    public Cadastro() {
        setContentPane(TelaCadastro);
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Criar placeholders
        addPlaceholder(NomeInput, "Nome");
        addPlaceholder(EmailInput, "Email");
        addPlaceholder(CPFInput, "CPF");
        addPasswordPlaceholder(SenhaInput, "Senha");
        addPasswordPlaceholder(ConfirmarSenha, "Confirmar Senha");

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

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = NomeInput.getText();
                String email = EmailInput.getText();
                String cpf = CPFInput.getText();
                String senha = new String(SenhaInput.getPassword());
                String confirmarSenha = new String(ConfirmarSenha.getPassword());
                String perfil = (String) userTypeComboBox.getSelectedItem();

                if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else if (!CadastroLogica.validateEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um email válido.");
                } else if (!CadastroLogica.validateCPF(cpf)) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um CPF válido.");
                } else if (!CadastroLogica.validatePasswordChars(senha)) {
                    JOptionPane.showMessageDialog(null, "A senha deve conter pelo menos um número, uma letra e um caractere especial.");
                } else if (!senha.equals(confirmarSenha)) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
                } else {
                    CadastroControle.InserirUsuario(nome, email, cpf, senha, perfil);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    dispose();
                    new Login();
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void clearFields() {
        resetPlaceholder(NomeInput, "Nome");
        resetPlaceholder(EmailInput, "Email");
        resetPlaceholder(CPFInput, "CPF");
        resetPasswordPlaceholder(SenhaInput, "Senha");
        resetPasswordPlaceholder(ConfirmarSenha, "Confirmar Senha");
    }

    private void resetPlaceholder(JTextComponent textComponent, String placeholder) {
        textComponent.setForeground(Color.GRAY);
        textComponent.setText(placeholder);
    }

    private void resetPasswordPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0); // Mostrar o texto do placeholder
        passwordField.setText(placeholder);
    }

    // Método para adicionar placeholder em campos de texto
    private void addPlaceholder(JTextComponent textComponent, String placeholder) {
        textComponent.setForeground(Color.GRAY);
        textComponent.setText(placeholder);

        textComponent.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (textComponent.getText().equals(placeholder)) {
                    textComponent.setText("");
                    textComponent.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setForeground(Color.GRAY);
                    textComponent.setText(placeholder);
                }
            }
        });
    }

    // Método para adicionar placeholder em campos de senha
    private void addPasswordPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0); // Mostrar o texto do placeholder
        passwordField.setText(placeholder);

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('*'); // Começar a mascarar a senha
                }
            }

            public void focusLost(FocusEvent evt) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0); // Mostrar o texto do placeholder
                    passwordField.setText(placeholder);
                }
            }
        });
    }
}
