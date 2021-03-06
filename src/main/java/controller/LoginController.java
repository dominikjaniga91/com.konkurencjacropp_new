package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static String ADMIN_LOGIN = "croppcrew";
    private final static String ADMIN_PASSWORD = "kontrolerzy";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

        String action = request.getParameter("action");
        try {
            switch (action) {
                case "LOGIN": {
                    logIn(request, response);
                    break;
                }
                case "LOG_OUT": {
                    logOut(request, response);
                    break;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        getServletContext().setAttribute("login", login);
        getServletContext().setAttribute("password", password);

        if(login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD)){

            RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
            dispatcher.forward(request, response);

        } else{
            String theMessage = "Nieprawidłowy login lub hasło";
            request.setAttribute("information", theMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession(false) != null){
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        } else{
            System.out.println("No session found");
        }
    }

}
