package servlets;

import entities.Categorie;
import manager.CategorieLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/test")
public class TestServlet extends AbstractGenericServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<Integer, Categorie> verslst = CategorieLibrary.getInstance().listCategories();
        context.setVariable("versions",verslst);

        templateEngine.process("testing", context, resp.getWriter());
    }
}

