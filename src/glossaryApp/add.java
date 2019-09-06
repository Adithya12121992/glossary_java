package glossaryApp;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class add extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{
		int i=Integer.parseInt(req.getParameter("num1"));
		int j=Integer.parseInt(req.getParameter("num2"));
		int k = i+j;
		res.getWriter().println("result is "+k);
		//return k;
	}
}
