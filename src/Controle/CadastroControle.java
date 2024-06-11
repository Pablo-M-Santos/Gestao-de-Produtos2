package Controle;

import Modelo.CadastroLogica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroControle {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/gestaoDeProdutos";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "aluno";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean persistirUsuario(CadastroLogica usuario) {
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
