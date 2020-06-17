package Servlet;

import java.util.HashMap;
import java.util.Map;

public class FakeDataBase {
    Map<String, String> dataBase;

    public FakeDataBase() {
        dataBase = new HashMap<>();
        dataBase.put("Maylin", "maylin1234");
    }

    public String finePassword(String username) {
        return dataBase.getOrDefault(username, "");
    }

    public void addDataBase(String username, String password) {
        dataBase.put(username, password);
    }

    public Boolean checkDatabase(String username, String password) {  //check if user in data base or not , if it is && is the pass psw same
        if (dataBase.containsKey(username) && password.equals(dataBase.get(username))) {
            return true;
        }
        else {
            return false;
        }
    }
}