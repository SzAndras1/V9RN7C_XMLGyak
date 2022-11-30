import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListV9RN7C {
    public static void main(String[] args) {
        JSONArray array = new JSONArray();
        List content = new ArrayList();
        content.add(new String("Bl"));
        content.add(1000000.0);
        content.add(21);
        array.add(content);
        System.out.println(array.toString());
    }
}
