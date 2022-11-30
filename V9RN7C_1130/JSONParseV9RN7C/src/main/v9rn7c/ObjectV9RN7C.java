import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectV9RN7C {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("Név","BLászló");
        object.put("Fizetes",1000000.0);
        object.put("Kor", 21);
        System.out.println(object.toString());
    }
}
