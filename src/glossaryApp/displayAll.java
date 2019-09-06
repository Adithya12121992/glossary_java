package glossaryApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;


public class displayAll extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{
		
		HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("apple", "A fruit");
        hmap.put("company", "A entity that engages in business");
        hmap.put("business", "Business is the activity of making one's living or making money by producing or buying and selling products");
        hmap.put("products", "an article or substance that is manufactured or refined for sale");
        
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        String final_json = "{\"glossary\":[";
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            String intermediate_json;
            final_json+="{\"word\":\""+mentry.getKey()+"\",\"meaning\":\""+mentry.getValue()+"\"},";
        }
        final_json = final_json.substring(0, final_json.length() - 1);
        final_json += "]}";

		String k = "This is from onload";
		res.getWriter().println(final_json);
	}
}
