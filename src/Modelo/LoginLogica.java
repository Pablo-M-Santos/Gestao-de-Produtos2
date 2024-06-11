package Modelo;

import Controle.Conexao;
import Visao.Home;
import Visao.adminHome;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Controle.Conexao.conectar;

public class LoginLogica extends JFrame{
    private String email;
    private String senha;

    public LoginLogica(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, senha);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // retorna true se encontrar um resultado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void fazerLogin() {
        if ("admin".equals(email) && "admin".equals(senha)) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, administrador!");
            new adminHome(); // Abre a tela de administrador
            dispose(); // Fecha a tela de login
        }else if (verificarLogin(email, senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            // Lógica para abrir a tela Home após o login bem-sucedido
            new Home().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
        }
    }
}
