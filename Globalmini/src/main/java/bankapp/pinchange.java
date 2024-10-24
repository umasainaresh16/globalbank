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

/**
 * Servlet implementation class pinchange
 */
@WebServlet("/pinchange")
public class pinchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pinchange() {
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
		int cpin=Integer.parseInt(request.getParameter("cpin"));
		int npin=Integer.parseInt(request.getParameter("npin"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp","root","1234");
			PreparedStatement ps=con.prepareStatement("select username,pin from bank");
			ResultSet rs=ps.executeQuery();
			Boolean bo=false;

			while(rs.next()) {
				String validusername=rs.getString(1);
				String validpin=rs.getString(2);
				if(username.equals(validusername) && validpin.equals(npin)) {
					bo=true;
					break;
				}
			}
			if(bo==true) {
				PreparedStatement ps1=con.prepareStatement("update bank set pin="+npin+" where pin= "+cpin+" and username='"+username+"'");
				ps1.executeUpdate();
				response.sendRedirect("transationcomplete.html");
			}
			else {
				response.sendRedirect("invalidpindetails.html");
			}
			
		}
		catch(Exception e) {
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