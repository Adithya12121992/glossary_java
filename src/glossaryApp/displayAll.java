package glossaryApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


public class displayAll extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{		
		File filehandler = new File("/Users/akm/eclipse-workspace/glossary_java/WebContent/json/final_word_list.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(filehandler));
		String st;
		
		// Code for the Java Hashmap, this will have the values for the key and value , ie : The word and its meaning. 
		LinkedHashMap<String, String> hmap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> hmap_sorted = new LinkedHashMap<String, String>();
		
		  while ((st = br.readLine()) != null) 
		  {
			  String[] value_split = st.split("\\:");
			  hmap.put(value_split[0], value_split[1]);
		  }
		  
		hmap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> hmap_sorted.put(x.getKey(), x.getValue()));
		  
		Set set = hmap_sorted.entrySet();
	    Iterator iterator = set.iterator();
	    String final_json = "{\"glossary\":[";
	    
	    while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            final_json+="{\"word\":\""+mentry.getKey()+"\",\"meaning\":\""+mentry.getValue()+"\"},";
	    }
	    
	    final_json = final_json.substring(0, final_json.length() - 1);
        final_json += "]}";
		
		res.getWriter().println(final_json);
		
	}
}
