package Controle;

import Modelo.UsuarioModelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroControle {
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/gestaoDeProdutos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "aluno";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarUsuario(String nome, String email, String cpf, String senha, String confirmarSenha, String perfil) {
        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            return false; // Por favor, preencha todos os campos
        } else if (!UsuarioModelo.validateEmail(email)) {
            return false; // Por favor, insira um email válido
        } else if (!UsuarioModelo.validateCPF(cpf)) {
            return false; // Por favor, insira um CPF válido
        } else if (!UsuarioModelo.validatePasswordChars(senha)) {
            return false; // A senha deve conter pelo menos um número, uma letra e um caractere especial
        } else if (!senha.equals(confirmarSenha)) {
            return false; // As senhas não coincidem
        } else {
            UsuarioModelo usuario = new UsuarioModelo(nome, email, cpf, senha, perfil);
            return persistirUsuario(usuario);
        }
    }

    private boolean persistirUsuario(UsuarioModelo usuario) {
        String sql = "INSERT INTO usuario (nome, email, cpf, senha, perfil) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getCpf());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setString(5, usuario.getPerfil());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
