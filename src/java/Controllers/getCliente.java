package Controllers;

import DAO.DaoCliente;
import DAO.DaoEndereco;
import Helpers.ValidadorCookie;
import Model.Cliente;
import Model.Endereco;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kener_000
 */
public class getCliente extends HttpServlet {

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
        
        ////////Validar Cookie
        boolean resultado = false;
        
        try{
        Cookie[] cookies = request.getCookies();
        ValidadorCookie validar = new ValidadorCookie();
        
        resultado = validar.validar(cookies);
        }catch(java.lang.NullPointerException e){System.out.println(e);}
        //////////////
        
        if(resultado){
            
            DaoCliente clienteDao = new DaoCliente();
            
            DaoEndereco enderecoDao = new DaoEndereco();
            ValidadorCookie validar = new ValidadorCookie();
            
            Cookie[] cookies = request.getCookies();
            String ID = validar.getCookieIdCliente(cookies);
           
            Cliente cliente = clienteDao.pesquisaPorID(ID);
            Endereco endereco = enderecoDao.pesquisarEnderecoPorID(cliente.getId_cliente());
            
            Object[] arr = new Object[2];
            arr[0] = cliente;
            arr[1] = endereco;
            
            Gson gson = new Gson();
            String json = gson.toJson(arr);

        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
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
