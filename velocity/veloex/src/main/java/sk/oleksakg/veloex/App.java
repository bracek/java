package sk.oleksakg.veloex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Hello world!
 *
 */
public class App 
{
	
	
	
    public static void main(final  String[] args ) throws IOException
    {
    	final VelocityEngine velocityEngine = new VelocityEngine();
    	
    	System.out.println(new File(".").getCanonicalPath());
    	
    	int[][] numbers = {{1, 2, 3, 4, 5}, {2, 3, 4, 5, 2}, {2, 5, 6, 3, 7}, {1, 2, 3, 4, 5}, {2, 3, 4, 5, 2}, {2, 5, 6, 3, 7}};
    	System.out.println(numbers.length);
    	
    	BufferedReader reader = null;
        try {
            // Opens template
            reader = new BufferedReader(new FileReader(new File("table_tex.vm")));
            
//            String line;
//            while ((line = reader.readLine()) != null) {
//            	System.out.println(line);
//            }
            
            // Output
            final Writer writer = new BufferedWriter(new FileWriter(new File("table.tex")));
            
            final Map < String, Object > params = new HashMap<String, Object>();
            params.put("numbers", numbers);
            
            // Velocity context
            final VelocityContext context = new VelocityContext(params);
            // Generating file according template
            velocityEngine.evaluate(context, writer, "", reader);
            
            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException("Cannot generate output for template " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
