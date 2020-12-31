import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter{
    private String content;
    private KeywordList list;
    
    public WordCounter(){
    	this.list = new KeywordList();
    }
    
    private String fetchContent(String urlStr)throws IOException{
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(15000);
//        conn.setReadTimeout(15000);
        conn.setAllowUserInteraction(false);         
        conn.setDoOutput(true);
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    	
    }
    
    public int countKeyword(String urlStr, String keyword) throws IOException{
		if (content == null){
		    content = fetchContent(urlStr);
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
		
		content = null;
	
		return retVal;
    }
    
    public double countScore(String urlStr) {
    	double retVal = 0;
    	for(Keyword keyword : list) {
    		try {
    			int count = countKeyword(urlStr, keyword.name);
    			retVal += count * keyword.weight;
    		}
    		catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return retVal;
    }
}