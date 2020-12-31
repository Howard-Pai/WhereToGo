import java.util.*;
public class KeywordList extends LinkedList<Keyword>{
	
	public KeywordList(){
		this.add(new Keyword("見面會", 20));
		this.add(new Keyword("展覽", 20));
		this.add(new Keyword("演唱會", 20));
		this.add(new Keyword("活動", 10));
		this.add(new Keyword("售票", 5));
		this.add(new Keyword("演出", 3));
		this.add(new Keyword("表演",2));
		this.add(new Keyword("維基",-10000));
		//add other keyword
    }
	
}