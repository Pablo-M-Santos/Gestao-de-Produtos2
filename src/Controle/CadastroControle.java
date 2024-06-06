package Controle;

import Modelo.UsuarioModelo;

public class CadastroControle {
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
            // Lógica para persistir o usuário no banco de dados
            return true; // Cadastro realizado com sucesso
        }
    }
}
