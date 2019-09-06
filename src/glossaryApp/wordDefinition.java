package glossaryApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class wordDefinition extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{
		Map<String, String> result = new HashMap<>();
		  Enumeration parameterNames = req.getParameterNames();
		  while (parameterNames.hasMoreElements()) {
		    String parameterName = (String) parameterNames.nextElement();
		    result.put(parameterName, req.getParameter(parameterName));
		  }
		  String delimiter = result.toString();
		  delimiter = delimiter.substring(6, delimiter.length() - 1);
		  System.out.println(delimiter);
	}
}
