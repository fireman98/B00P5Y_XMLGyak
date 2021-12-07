package objectB00P5Y;

import org.json.simple.JSONObject;

public class ObjectB00P5Y {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

        obj.put("nev", "BMarton");
        obj.put("fizetes", 10000);
        obj.put("kor", 21);

        System.out.print(obj);
	}

}
