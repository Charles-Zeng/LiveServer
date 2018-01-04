import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        /*resp.setContentType("text/html; charset=utf-8");
        ServletContext sc = getServletContext();
        RequestDispatcher rd = null;
        rd = sc.getRequestDispatcher("/login.jsp");
        rd.forward(req, resp);*/

        System.out.println("注册界面");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>"
                + "username:" + req.getParameter("username")
                + " password:" + req.getParameter("password")
                + "confirmPwd:" + req.getParameter("confirmPwd")
                + "</h1>");

        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
