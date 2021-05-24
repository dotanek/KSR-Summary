package database;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {
    private String dataPath;

    public DataLoader(String dataPath) {
        this.dataPath = dataPath;
    }

    public JSONObject loadMembershipParams() {
        try {
            Scanner scanner = new Scanner(new File(dataPath + "\\member_params_readable.json"));

            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            return new JSONObject(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
