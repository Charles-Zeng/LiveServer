import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html");
        System.out.println("登录界面");

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        if (userName.isEmpty() || password.isEmpty())
        {
            req.setAttribute("message", "用户名密码不能为空");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        if (!userName.equals("111"))
        {
            req.setAttribute("message", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/manager.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
