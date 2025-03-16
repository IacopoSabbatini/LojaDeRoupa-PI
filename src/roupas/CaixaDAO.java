/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roupas;

import Connection.Conexao;
import roupas.Caixa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaixaDAO {
 public void inserir(Caixa caixa) throws SQLException {
        String sql = "INSERT INTO caixa (descricao, entrada_ou_saida, data, saldo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caixa.getDescricao());
            stmt.setString(2, caixa.getEntradaOuSaida());
            stmt.setDate(3, new java.sql.Date(caixa.getData().getTime()));
            stmt.setDouble(4, caixa.getSaldo());
            stmt.executeUpdate();
        }
    }

    public List<Caixa> listar() throws SQLException {
        List<Caixa> caixas = new ArrayList<>();
        String sql = "SELECT * FROM caixa";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Caixa caixa = new Caixa();
                caixa.setId(rs.getInt("id"));
                caixa.setDescricao(rs.getString("descricao"));
                caixa.setEntradaOuSaida(rs.getString("entrada_ou_saida"));
                caixa.setData(rs.getDate("data"));
                caixa.setSaldo(rs.getDouble("saldo"));
                caixas.add(caixa);
            }
        }
        return caixas;
    }
}
