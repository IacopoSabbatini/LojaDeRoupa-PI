/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roupas;

import Connection.Conexao;
import roupas.Informacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformacaoDAO {
    public void inserir(Informacao informacao) throws SQLException {
        String sql = "INSERT INTO informacao (nomeDaEmpresa, cnpj, endereco, contato, campoDeObservacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, informacao.getNomeDaEmpresa());
            stmt.setString(2, informacao.getCnpj());
            stmt.setString(3, informacao.getEndereco());
            stmt.setString(4, informacao.getContato());
            stmt.setString(5, informacao.getCampoDeObservacao());
            stmt.executeUpdate();
        }
    }

    public List<Informacao> listar() throws SQLException {
        List<Informacao> informacoes = new ArrayList<>();
        String sql = "SELECT * FROM informacao";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Informacao informacao = new Informacao();
                informacao.setId(rs.getInt("id"));
                informacao.setNomeDaEmpresa(rs.getString("nome_da_empresa"));
                informacao.setCnpj(rs.getString("cnpj"));
                informacao.setEndereco(rs.getString("endereco"));
                informacao.setContato(rs.getString("contato"));
                informacao.setCampoDeObservacao(rs.getString("campo_de_observacao"));
                informacoes.add(informacao);
            }
        }
        return informacoes;
    }
}

