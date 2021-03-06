package servlets;

import entities.Categorie;
import entities.Version;
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
import java.util.List;
import java.util.Map;

@WebServlet("/version")
public class VersionServlet extends AbstractGenericServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<Integer, Version> verslst = VersionLibrary.getInstance().listVersions();
        context.setVariable("versions",verslst);

        templateEngine.process("version", context, resp.getWriter());
    }
}
