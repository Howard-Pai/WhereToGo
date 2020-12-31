import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		ArrayList<Web> arr = new ArrayList<Web>();
		WordCounter counter = new WordCounter();
		
		System.out.println("�٧A�o�g�n�h��");
		System.out.print("��J�j�M����r�G");
		Scanner in = new Scanner(System.in);
		String searchWord = in.next();
		
		GoogleQuery google = new GoogleQuery(searchWord); 
		try {
			HashMap<String, String> query = google.query();
			for(String key : query.keySet()) {
				int endIndex = query.get(key).indexOf("&");
				String url = query.get(key).substring(7, endIndex);
				double score = counter.countScore(url);
				//System.out.println(key);
				//System.out.println(url);
				//System.out.println("�`���G " + score);
				//System.out.println();
				arr.add(new Web(key,url,score));
			}
		}
		catch(IOException e) {
			System.out.print("Exception teace= ");
			 e.printStackTrace();
		}
		Collections.sort(arr,new Sort());
		for (int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i).toString());
		}
	}
	public static class Web{
		String url,name;
		double score;
		public Web (String name,String url,double score){
			this.name = name;
			this.url = url;
			this.score = score;
		}
		public String toString() {
			return name+"\n"+url+"\n�`���G "+score+"\n";
		}
	}
	public static class Sort implements Comparator<Web>{
		public int compare(Web a,Web b) {
			return (int)(b.score - a.score);
			
		}
	}

}
