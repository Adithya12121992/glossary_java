package glossaryApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class wordDefinition extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res ) throws IOException
	{
		File filehandler = new File("/Users/akm/eclipse-workspace/glossary_java/WebContent/json/final_word_list.txt");
		BufferedReader br = new BufferedReader(new FileReader(filehandler));
		String st; 
		LinkedHashMap<String, String> hmap = new LinkedHashMap<String, String>();
		//LinkedHashMap<String, String> hmap_sorted = new LinkedHashMap<String, String>();
		List keysList = new ArrayList();
		List valuesList = new ArrayList();
		
		while ((st = br.readLine()) != null) 
		{
			String[] value_split = st.split("\\:");
			hmap.put(value_split[0], value_split[1]);
			
			keysList.add(value_split[0]);
			valuesList.add(value_split[1]);
		}
				
		
		Map<String, String> result = new HashMap<>();
		Enumeration parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) 
		{
		  String parameterName = (String) parameterNames.nextElement();
		  result.put(parameterName, req.getParameter(parameterName));
		}
		String delimiter = result.toString();
		delimiter = delimiter.substring(6, delimiter.length() - 1);
				
		String meaning= hmap.get(delimiter);
		
		String final_output_of_definition ="";
		List likeTermList = new ArrayList();
			for(int i=0; i< keysList.size();i++)
			{
				
				if (meaning.contains((String) keysList.get(i)))
				{
					
					String [] splitMeaning = meaning.split(" ");
					int j=0;
					String final_definition ="";
					for (j=0;j<splitMeaning.length;j++)
					{
						//System.out.println(splitMeaning[j]);
						if (splitMeaning[j].equalsIgnoreCase((String)keysList.get(i)))
						{
							likeTermList.add(splitMeaning[j]);
							final_definition+="<a style=\"color:blue;\" id=\""+splitMeaning[j]+"\" name=\""+splitMeaning[j]+"\" onclick=\"glossary_click(id)\"> "+splitMeaning[j]+"</a> ";
						}
						else
						{
							final_definition+=splitMeaning[j]+" ";
						}
					}
					
					final_output_of_definition = final_definition;
				}
				else 
				{
					//final_output_of_definition = meaning;
					continue;
					
				}
				
			}
			if (final_output_of_definition == "")
				final_output_of_definition = meaning;
		
			String[] final_splitted_output = meaning.split(" ");
			int k=0;
			for(k=0; k<likeTermList.size();k++)
			{
				int l=0;
				for (l=0;l<final_splitted_output.length;l++)
				{
					if (likeTermList.get(k).equals(final_splitted_output[l]))
					{
						final_splitted_output[l] = "<a style=\"color:blue;\" id=\""+likeTermList.get(k)+"\" name=\""+likeTermList.get(k)+"\" onclick=\"glossary_click(id)\">"+likeTermList.get(k)+"</a>";
					}
				}
			}
			
			String op_final="";
			for (int x=0;x<final_splitted_output.length;x++)
			{
				op_final+=final_splitted_output[x]+" ";
			}
			System.out.println(op_final);
			
		res.getWriter().println(op_final);
	}
}
