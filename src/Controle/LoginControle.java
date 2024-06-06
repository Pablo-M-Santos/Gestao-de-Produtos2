package Controle;

import Visao.Home;

import javax.swing.JOptionPane;

public class LoginControle {
    private String email;
    private String senha;

    public LoginControle(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public void fazerLogin() {
        if (Conexao.verificarLogin(email, senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            // Lógica para abrir a tela Home após o login bem-sucedido
            new Home().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
        }
    }
}
