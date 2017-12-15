package servlets;

import entities.Categorie;
import entities.SousVersion;
import manager.CategorieLibrary;
import manager.VersionLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/edit")
public class EditServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idSv = Integer.parseInt(req.getParameter("id"));
        SousVersion sv = VersionLibrary.getInstance().getSousVersion(idSv);

        context.setVariable("sousVersion", sv);

        templateEngine.process("edit", context, resp.getWriter());

    }
}
