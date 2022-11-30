import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JSONReadV9RN7C {
    public static void main(String[] args)  {
        try{
            System.out.println();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject)parser.parse(new FileReader(System.getProperty("user.dir") + "\\JSONV9RN7C.json"));
            System.out.println(object.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
