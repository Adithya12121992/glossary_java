package glossaryApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

public class readFile extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{
		File filehandler = new File("/Users/akm/eclipse-workspace/glossary_java/WebContent/json/glossary_list.txt");
		BufferedReader br = new BufferedReader(new FileReader(filehandler));
		String st; 
		HashMap<String, String> hmap = new HashMap<String, String>();
		  while ((st = br.readLine()) != null) 
		  {
			  String[] value_split = st.split("\\:");
			  hmap.put(value_split[0], value_split[1]);
		  }
		  
		Set set = hmap.entrySet();
	    Iterator iterator = set.iterator();
	    while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
	    }
		//res.getWriter().println("from the readFile Servlet");
	}
}
