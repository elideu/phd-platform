package it.unisa.dottorato.Curriculum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/** Servlet incaricaricata ad effettuare la richiesta di cancellazione di un 
 * curriculum dato il nome
 *
 * @author Tommaso Minichiello
 */
@WebServlet(name = "DeleteCurriculum", urlPatterns = {"/DeleteCurriculum"})
public class DeleteCurriculumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods 
     * Effettua la cancellazione di un curriculum dato il nome
     * 
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome del curriculum 
     * <code>nameCurriculum</code> per effettuare la cancellazione
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
            String nameCurriculum = request.getParameter("nameCurriculum");
            
            result.put("result", true);

            try {
                CurriculumManager.getInstance().delete(nameCurriculum);
            } catch (ClassNotFoundException | SQLException ex) {
                result.put("result", false);
                Logger.getLogger(DeleteCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(DeleteCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            out.close();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DeleteCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(DeleteCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
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
