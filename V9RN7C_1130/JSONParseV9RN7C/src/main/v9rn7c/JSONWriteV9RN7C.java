import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONWriteV9RN7C {
    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("JsoninTxt.txt");
        JSONArray array = new JSONArray();
        List content = new ArrayList();
        content.add(new String("Bl"));
        content.add(1000000.0);
        content.add(21);
        array.add(content);
        file.write(array.toJSONString());
        file.close();
    }
}
