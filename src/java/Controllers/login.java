/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DaoCliente;
import DAO.DaoToken;
import Model.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.Instant;
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
public class login extends HttpServlet {

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
       //Seta o tipo de Conteudo que será recebido, nesse caso, um JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        //Pra receber JSONs, é necessario utilizar esse Buffer pra receber os dados,
        //Então tem que ser Feito assim:
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        boolean resultado = false;
        
        
        //Aqui ele checa se os Dados não tão vazios, por motivos de vai que
        if (br != null) {
            
            //Converte os dados do JSON pra um Formato de Objeto que o Java consiga lidar
            json = br.readLine();
            JSONObject dados = new JSONObject(json);
            
            //Aqui, ele Instancia um objeto do Model Cliente, e Popula ele com os dados do JSON
            Cliente cliente = new Cliente();
            cliente.setUsuario(dados.getString("usuario"));
            cliente.setSenha(dados.getString("senha"));
            
            /////////////////////////
            //E Para finalizar, salva no Banco usando o DAO dele
            
            DaoCliente clienteDAO = new DaoCliente();
            DaoToken tokenDAO = new DaoToken();
            resultado = clienteDAO.login(cliente);
            
            if(resultado == true){
                Cliente clienteCompleto = clienteDAO.pesquisaPorUsuario(cliente);
                
                Cookie cookie = new Cookie("token", clienteCompleto.getId_cliente()+"-"+Instant.now().toString());
                tokenDAO.salvar(cookie.getValue());
                response.addCookie(cookie);
            }
        }
        try (PrintWriter out = response.getWriter()) {
            
            //Aqui é onde a Resposta é mandada para o Cliente, dando um Feedback de que tudo deu certo.
            
            if(resultado == true){
                out.println("../resumo/resumo.html");
            } else {
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
