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
        String sql ="UPDATE protocolo SET statusProtocolo = 'Aguardando Deliberação', statusParecer = ? WHERE id = ?";
                
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        
        psts.setInt(1, e.getId());
        psts.setString(2, e.getStatusParecer());
        psts.execute();
    }
    
    public void deliberarProtocolo(Estudo e) throws Exception {
        
    }
    
    public List<Estudo> selecionar(int id) throws Exception {
        String sql = "SELECT nome FROM contatos WHERE id = ?";
        
        con = Conexao.conexaoMySQL();
        psts = con.prepareStatement(sql);
        ResultSet rs = psts.executeQuery();
        
        Estudo e = new Estudo();
        
        List<Estudo> cont = new ArrayList<>();
        
        psts.setInt(1, e.getId());
        
        while (rs.next()) {
            e.setId(rs.getInt("id"));
            cont.add(e);
        }
        psts.execute();
        rs.close();
        psts.close();
        return cont;
        
    }
    
    public void obterPercentualParecer(Estudo e) throws Exception {
        
    }
    
}
