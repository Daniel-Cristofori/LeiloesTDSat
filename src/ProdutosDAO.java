/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
        
            conn = new conectaDAO().connectDB();
       
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            
        
             } catch (SQLException ex) {
                 
                 System.out.println("Erro ao conectar: " + ex.getMessage());
                 
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
                try {
                    
                    conn = new conectaDAO().connectDB();
                    prep = conn.prepareStatement("SELECT * FROM produtos");
                    resultset = prep.executeQuery();            
                    
                    while (resultset.next()) { 
                        
                        ProdutosDTO produto = new ProdutosDTO();
                        
                        produto.setId(resultset.getInt("id"));
                        produto.setNome(resultset.getString("nome"));
                        produto.setValor(resultset.getInt("valor"));
                        produto.setStatus(resultset.getString("status"));
                            
                        listaProdutos.add(produto);    
                        
                    }
                    
                    return listaProdutos;
                    
                } catch (SQLException e) {
                    
                    return null;
                    
                }
        }
    
}

