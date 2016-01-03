package it.unisa.dottorato.Curriculum;

import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.NameException;
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


/** Servlet incaricata ad effettuare la richiesta di inserimento di un curriculum
 *
 * @author Tommaso Minichiello
 */
@WebServlet(name = "InsertCurriculum", urlPatterns = {"/InsertCurriculum"})
public class InsertCurriculumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods
     * Inserisce un nuovo oggetto curriculum
     *
     * @param request oggetto request per accedere ai parametri inviati attraverso
     * il metodo getParameter per ottenere il nome del curriculum 
     * <code>name</code> e la descrizione del curriculum <code>description</code>
     * per effettuare l'inserimento di un nuovo curriculum
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DescriptionException, NameException, CurriculumException{
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {

            JSONObject result = new JSONObject();
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Curriculum aPhdCurriculum = new Curriculum();
        aPhdCurriculum.setName(name);
        aPhdCurriculum.setDescription(description);
        
        result.put("result", true);

        try {
            CurriculumManager.getInstance().insert(aPhdCurriculum);
        } catch (ClassNotFoundException | SQLException ex)  {
            result.put("result", false);
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.write(result.toString());

        } catch (JSONException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
        } catch (DescriptionException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DescriptionException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NameException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CurriculumException ex) {
            Logger.getLogger(InsertCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
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
