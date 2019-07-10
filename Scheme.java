import java.util.*;

public class Scheme{

    private List<String> attributes;

    public Scheme(String[] pParam){
        attributes = new ArrayList();
        for(String el: pParam)
        {
        	attributes.add(el);
        }
    }

    public void ShowScheme(){
    	String schemeChart = null;
    	
    	for(String el: attributes)
    	{
    		if(schemeChart == null) {
    			schemeChart = el;
    		}
    		else {
    			schemeChart+=el;
    		}
    		schemeChart += "\t";
    	}
    	
    	System.out.println(schemeChart);
    }
}