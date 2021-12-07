package listB00P5Y;

import org.json.simple.JSONArray;

public class ListB00P5Y {

	public static void main(String[] args) {
		JSONArray json=new JSONArray();
		json.add("BL");
		json.add(1000000);
		json.add(21);
		
		for(Object obj : json) {
			System.out.print(obj+" ");
		}
	}

}
