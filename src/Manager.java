import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/*
read config
        web.xml
        <context-param>
        <param-name>myParam</param-name>
        <param-value>the value</param-value>
        </context-param>

        String myContextParam =
        request.getSession()
        .getServletContext()
        .getInitParameter("myParam");
*/


public class Manager extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("管理界面");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1> manage page</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
