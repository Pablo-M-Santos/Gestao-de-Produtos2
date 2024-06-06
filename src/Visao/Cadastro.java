    package Visao;

    import Controle.CadastroControle;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.FocusEvent;
    import java.awt.event.FocusListener;
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
        private CadastroControle Controle;

        public Cadastro() {
            Controle = new CadastroControle();
            setContentPane(TelaCadastro);
            setSize(850, 700);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);

            // Configurar placeholders
            configPlaceholders();

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

            // ActionListener para menuItem3 (Cadastro)
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

            // ActionListener para o botão de cadastro
            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = NomeInput.getText();
                    String email = EmailInput.getText();
                    String cpf = CPFInput.getText();
                    String senha = new String(SenhaInput.getPassword());
                    String confirmarSenha = new String(ConfirmarSenha.getPassword());
                    String perfil = (String) userTypeComboBox.getSelectedItem();

                    if (Controle.cadastrarUsuario(nome, email, cpf, senha, confirmarSenha, perfil)) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                        dispose();
                        new Login();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao realizar cadastro. Verifique os dados e tente novamente.");
                    }
                }
            });

            // ActionListener para o botão de cancelar
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetarPlaceholder();
                }
            });
        }

        // Método para configurar os placeholders
        private void configPlaceholders() {
            addPlaceholder(NomeInput, "Digite seu Nome: ");
            addPlaceholder(EmailInput, "Digite seu Email: ");
            addPlaceholder(CPFInput, "Digite seu CPF: ");
            addPlaceholder(SenhaInput, "Digite sua Senha: ");
            addPlaceholder(ConfirmarSenha, "Digite sua Senha Novamente: ");
        }

        // Método para adicionar placeholder a um JTextField
        private void addPlaceholder(JTextField textField, String placeholder) {
            textField.setForeground(Color.GRAY);
            textField.setText(placeholder);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (textField.getText().equals(placeholder)) {
                        textField.setText("");
                        textField.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textField.getText().isEmpty()) {
                        textField.setForeground(Color.GRAY);
                        textField.setText(placeholder);
                    }
                }
            });
        }

        // Método para redefinir os placeholders
        private void resetarPlaceholder() {
            resetarPlaceholder(NomeInput, "Digite seu Nome: ");
            resetarPlaceholder(EmailInput, "Digite seu Email: ");
            resetarPlaceholder(CPFInput, "Digite seu CPF: ");
            resetarPlaceholder(SenhaInput, "Digite sua Senha: ");
            resetarPlaceholder(ConfirmarSenha, "Digite sua Senha Novamente: ");
        }

        // Método para redefinir placeholder de um JTextField
        private void resetarPlaceholder(JTextField textField, String placeholder) {
            textField.setForeground(Color.GRAY);
            textField.setText(placeholder);
        }

        // Método principal para inicializar o cadastro
        public static void main(String[] args) {
            new Cadastro();
        }
    }
