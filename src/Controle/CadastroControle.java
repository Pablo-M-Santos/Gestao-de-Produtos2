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

    public static void InserirUsuario(String nome, String email, String cpf, String senha, String perfil) {
        String sql = "INSERT INTO usuario (nome, email, cpf, senha, perfil) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, cpf);
            pstmt.setString(4, senha);
            pstmt.setString(5, perfil);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

