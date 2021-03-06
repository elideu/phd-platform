package it.unisa.dottorato.account;

import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**Servlet incaricata ad effettuare la richiesta di ricerca di un utente
 *
 * @author Rembor
 */
@WebServlet(name = "SearchUser", urlPatterns = {"/SearchUser"})
public class SearchUserServlet extends HttpServlet{
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods
     * Ricostruisce un array list di tutti gli utenti con il nome <code>name</code>
     *
     * @param request  oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome <code>name</code> dell'utente da ricercare
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a sql error occurs
     * @throws ConnectionException if a connection error occurs
    
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ConnectionException, ProfileException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject result = new JSONObject();
            String name = request.getParameter("name");
            ArrayList<Account> cycles = AccountManager.getInstance().searchUser(name);
                JSONArray resultArray = new JSONArray(cycles);
                
                result.put("account", resultArray);
                out.write(result.toString());
            } 
        catch (JSONException ex) {
                Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
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
     * @throws java.sql.SQLException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProfileException ex) {
                Logger.getLogger(SearchUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConnectionException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(ChangeTypeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProfileException ex) {
            Logger.getLogger(SearchUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
