/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DaoFuncionario;
import Helpers.ValidadorCookie;
import Model.Funcionario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
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
public class salvarFuncionario extends HttpServlet {

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
        String ID = "1";
        ////////Validar Cookie
        boolean resultado = false;
        
        try{
        Cookie[] cookies = request.getCookies();
        ValidadorCookie validar = new ValidadorCookie();
        
        ID = validar.getCookieIdFuncionario(cookies);
        resultado = validar.validarFuncionario(cookies);
        }catch(java.lang.NullPointerException e){System.out.println(e);}
        //////////////
        
        if ((br != null) && resultado) {
            json = br.readLine();
            byte[] bytes = json.getBytes(ISO_8859_1); 
            String jsonStr = new String(bytes, UTF_8);            
            JSONObject dados = new JSONObject(jsonStr);
            
            Funcionario funcionario = new Funcionario();
            funcionario.setCad_por(Integer.valueOf(ID));
            funcionario.setNome(dados.getString("nome"));
            funcionario.setCargo("cargo");
            funcionario.setSalario(dados.getDouble("salario"));
            funcionario.setUsuario(dados.getString("usuarioFuncionario"));
            funcionario.setSenha(dados.getString("senhaFuncionario"));
            funcionario.setSobrenome("sobrenome");
            funcionario.setFg_ativo(1);
            
            DaoFuncionario funcionarioDAO = new DaoFuncionario();
            funcionarioDAO.salvar(funcionario);
            
            try (PrintWriter out = response.getWriter()) {
            out.println("Funcionario Cadastrado!");
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
