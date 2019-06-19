package net.proselyte.servletstutorial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Fields extends HttpServlet {
    public static DB_BankList db_bankList = new DB_BankList();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String richestUser = db_bankList.RichestUser();
        request.setAttribute("richestUser", richestUser);
        int sum = db_bankList.Sum();
        request.setAttribute("sum", sum);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
}