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
        String sql = "INSERT INTO protocolo (dataEmissaoProtocolo, dataInicioExperimento, dataFimExperimento, JustificativaUsoAnimais, resumoPortugues, resumoIngles) VALUES (?, ?, ?, ?, ?, ?)";
        
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
}
