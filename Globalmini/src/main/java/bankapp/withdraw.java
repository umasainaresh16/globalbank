package bankapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class withdraw
 */
@WebServlet("/withdraw")
public class withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("username");
		int pin=Integer.parseInt(request.getParameter("pin"));
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp","root","1234");
			PreparedStatement ps=con.prepareStatement("select username,pin from bank");
			ResultSet rs=ps.executeQuery();
			boolean valid=false;
			while(rs.next()) {
				String validuname=rs.getString(1);
				int validpin=rs.getInt(2);
				if((validuname.equals(username)) && validpin ==pin) {
					valid=true;
					break;
				}
			}
		
			if(valid) {
				HttpSession session1=request.getSession(true);
				session1.setAttribute("username",username);
				session1.setAttribute("pin",pin);
				response.sendRedirect("moneywithdraw.html");
			}
			else {
				pw.print("<html><body><p>invalid details</p><br></br><a href=Bankhome.html>Go to home </a></body></html>");

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
