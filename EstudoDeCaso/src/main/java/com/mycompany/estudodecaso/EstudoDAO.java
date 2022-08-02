package com.mycompany.estudodecaso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudoDAO {
    public Conexao conexao;
    
    private Connection con;
    private PreparedStatement psts;
    private ResultSet rs;
    
    public EstudoDAO() {
        conexao = new Conexao();
    }
    
    public void emitir(Estudo e) throws Exception {
        String sql = "INSERT INTO protocolo (dataEmissaoProtocolo, dataInicioExperimento, dataFimExperimento, JustificativaUsoAnimais, resumoPortugues, resumoIngles, statusProtocolo) VALUES (?, ?, ?, ?, ?, ?, 'Aguardando Envio')";
        
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        
        psts.setString(1, e.getDataEmissaoProtocolo());
        psts.setString(2, e.getDataInicioExperimento());
        psts.setString(3, e.getDataFimExperimento());
        psts.setString(4, e.getJustificativaUsoAnimais());
        psts.setString(5, e.getResumoPortugues());
        psts.setString(6, e.getResumoIngles());
        psts.execute();
    }
    
    public void enviarParaParecer(Estudo e) throws Exception {
        String sql ="UPDATE protocolo SET dataEnvioParecer = curdate(), statusProtocolo = 'Enviado' WHERE id = ?";
                
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        
        psts.setInt(1, e.getId());
        psts.execute();
    }
    
    public void emitirParecer(Estudo e) throws Exception {
        String sql ="UPDATE protocolo SET descricaoParecer = ?, statusParecer = ?, dataEmissaoParecer = curdate(), statusProtocolo = 'Aguardando Deliberação' WHERE id = ?";
                
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        
        psts.setInt(1, e.getId());
        psts.setString(2, e.getDescricaoParecer());
        psts.setString(3, e.getStatusParecer());

        psts.execute();
    }
    
    public void deliberarProtocolo(Estudo e) throws Exception {
        String sql ="UPDATE protocolo SET dataDeliberacaoProtocolo = curdate(), statusProtocolo = 'Deliberado', statusDeliberacao = ? WHERE id = ?";
        
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        
        psts.setInt(1, e.getId());
        psts.setString(2, e.getStatusDeliberacao());
        psts.execute();
    }
    
    public Estudo selecionar(int id) throws Exception {
        String sql = "SELECT * FROM protocolo WHERE id = ?";
        Estudo e = new Estudo();
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        psts.setInt(1, id);
        rs = psts.executeQuery();
        
        
        while (rs.next()) {
            e.setId(rs.getInt("id"));
            e.setDataEmissaoProtocolo(rs.getString(2));
            e.setDataInicioExperimento(rs.getString(3));
            e.setDataFimExperimento(rs.getString(4));
            e.setJustificativaUsoAnimais(rs.getString(5));
            e.setResumoIngles(rs.getString(6));
            e.setResumoPortugues(rs.getString(7));
            e.setDataEmissaoParecer(rs.getString(8));
            e.setDescricaoParecer(rs.getString(9));
            e.setDataDeliberacaoProtocolo(rs.getString(10));
            e.setStatusParecer(rs.getString(11));
            e.setStatusDeliberacao(rs.getString(12));
            e.setStatusProtocolo(rs.getString(13));
        }
        
        rs.close();
        psts.close();
        return e;
        
    }
    
}
