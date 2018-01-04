import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");
        System.out.println("登录界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        String message = "用户名或密码错误";

        if (userName == null || password == null)
        {
            req.setAttribute("message", message);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
