package bankapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userdetails")
public class userdetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public userdetails() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        try {
            // DB connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp", "root", "1234");

            // SQL query
            HttpSession session=request.getSession(false);
			String uname=(String) session.getAttribute("username");

            PreparedStatement ps1 = con.prepareStatement("SELECT username,bod,accountno,email,password,pin,phoneno,address,district,state,pincode,firstname,lastname FROM bank where username=?");
            ps1.setString(1, uname); 
            ResultSet rs = ps1.executeQuery();

            // Variable initialization
            String username = "", email = "", password = "", address = "", district = "", state = "", firstname = "", lastname = "";
            int pin = 0, pincode = 0;
            long phoneno = 0,accountno =0;
            Date date = null;

            // Fetching data
            if (rs.next()) {
                username = rs.getString("username");
                date = rs.getDate("bod");
                accountno = rs.getLong("accountno");
                email = rs.getString("email");
                password = rs.getString("password");
                pin = rs.getInt("pin");
                phoneno = rs.getLong("phoneno");
                address = rs.getString("address");
                district = rs.getString("district");
                state = rs.getString("state");
                pincode = rs.getInt("pincode");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
            }

            // Setting attributes to pass to JSP
            request.setAttribute("username", username);
            request.setAttribute("bod", date != null ? date.toString() : "N/A");
            request.setAttribute("accountno", accountno);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("pin", pin);
            request.setAttribute("phoneno", phoneno);
            request.setAttribute("address", address);
            request.setAttribute("district", district);
            request.setAttribute("state", state);
            request.setAttribute("pincode", pincode);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);

            // Forwarding request to JSP
            RequestDispatcher rd = request.getRequestDispatcher("/userdetails.jsp");  // Adjust path if needed
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
