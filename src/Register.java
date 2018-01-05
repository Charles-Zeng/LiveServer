import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Register extends HttpServlet{
    private String confirmPwd = "";
    private String tel = "";
    private String name = "";
    private String address = "";
    private String idCardNum = "";
    private String pushAddress = "";
    private Integer autoStopPushMinutes;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("注册界面");

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(req.getParameter("username"));
        userInfo.setPassword(req.getParameter("password"));
        confirmPwd = req.getParameter("confirmPwd");
        tel = req.getParameter("tel");
        name = req.getParameter("name");
        address = req.getParameter("address");
        idCardNum = req.getParameter("idCardNum");
        pushAddress = req.getParameter("pushAddress");
        autoStopPushMinutes = Integer.valueOf(req.getParameter("autoStopPushMinutes"));

        UserInfoDao dao = new UserInfoDao();
        dao.addUserInfo(userInfo);

        resp.sendRedirect("/login.jsp");
    }
}
