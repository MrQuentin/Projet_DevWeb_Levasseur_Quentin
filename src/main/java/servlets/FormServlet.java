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
import java.util.Map;

@WebServlet("/form")
public class FormServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<Integer, Categorie> catmap = CategorieLibrary.getInstance().listCategories();
        context.setVariable("categories",catmap);

        Map<Integer, Version> verslst = VersionLibrary.getInstance().listVersions();
        context.setVariable("versions",verslst);

        templateEngine.process("form", context, resp.getWriter());
    }
}
