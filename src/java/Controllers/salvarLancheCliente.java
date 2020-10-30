/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DaoIngrediente;
import DAO.DaoLanche;
import Helpers.ValidadorCookie;
import Model.Ingrediente;
import Model.Lanche;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author kener_000
 */
public class salvarLancheCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        
        ////////Validar Cookie
        boolean resultado = false;
        
        try{
        Cookie[] cookies = request.getCookies();
        ValidadorCookie validar = new ValidadorCookie();
        
        resultado = validar.validar(cookies);
        }catch(java.lang.NullPointerException e){}
        //////////////
        
        if ((br != null) && resultado) {
            json = br.readLine();
            byte[] bytes = json.getBytes(ISO_8859_1); 
            String jsonStr = new String(bytes, UTF_8);            
            JSONObject dados = new JSONObject(jsonStr);
            JSONObject ingredientes = dados.getJSONObject("ingredientes");
            
            double precoDoLanche = 0.00;
            
            Lanche lanche = new Lanche();
            
            lanche.setNome(dados.getString("nome"));
            lanche.setDescricao(dados.getString("descricao"));
            
            
            DaoLanche lancheDao = new DaoLanche();
            DaoIngrediente ingredienteDao = new DaoIngrediente();
            
            Iterator<String> keys = ingredientes.keys();
            
            while(keys.hasNext()) {
                
                String key = keys.next(); 
                Ingrediente ingredienteLanche = new Ingrediente();
                ingredienteLanche.setNome(key);
                
                Ingrediente ingredienteComID = ingredienteDao.pesquisaPorNome(ingredienteLanche);
                precoDoLanche += ingredienteComID.getValor_venda() * Double.valueOf(ingredientes.getInt(key));
            }
            
            
            lanche.setValor_venda(precoDoLanche);
            lancheDao.salvarCliente(lanche);
            
            Lanche lancheComID = lancheDao.pesquisaPorNome(lanche);
            
            while(keys.hasNext()) {
                
                String key = keys.next(); 
                Ingrediente ingredienteLanche = new Ingrediente();
                ingredienteLanche.setNome(key);
                
                Ingrediente ingredienteComID = ingredienteDao.pesquisaPorNome(ingredienteLanche);
                ingredienteComID.setQuantidade(ingredientes.getInt(key));
                lancheDao.vincularIngrediente(lancheComID, ingredienteComID);
            }
            
            try (PrintWriter out = response.getWriter()) {
            out.println("../carrinho/carrinho.html?nome="+String.valueOf(lancheComID.getNome())+"&preco="+String.valueOf(lancheComID.getValor_venda()));
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
            out.println("erro");
        }
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
