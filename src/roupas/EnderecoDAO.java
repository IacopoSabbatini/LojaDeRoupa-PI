/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roupas;

import Connection.Conexao;

import roupas.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
     public void inserir(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (nome, contato, cpf, endereco, pontoDeReferencia) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getNome());
            stmt.setString(2, endereco.getContato());
            stmt.setString(3, endereco.getCpf());
            stmt.setString(4, endereco.getEndereco());
            stmt.setString(5, endereco.getPontoDeReferencia());
            stmt.executeUpdate();
        }
    }

    public List<Endereco> listar() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setNome(rs.getString("nome"));
                endereco.setContato(rs.getString("contato"));
                endereco.setCpf(rs.getString("cpf"));
                endereco.setEndereco(rs.getString("endereco"));
                endereco.setPontoDeReferencia(rs.getString("pontoDeReferencia"));
                enderecos.add(endereco);
            }
        }
        return enderecos;
    }
}
    
