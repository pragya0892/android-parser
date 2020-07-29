package parser;

import constant.Constants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pragya
 */

public class UniqueErrorFinder {

    public static void findUniqueErrors(){
        try {
            FileInputStream fileInputStream = new FileInputStream(Constants.FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            Map<String, Integer> errorMap = new HashMap<String, Integer>();
            while((strLine = br.readLine()) != null) {
                if(strLine.contains("Exception") || strLine.contains("Error")){
                    String[] tokens = strLine.split("\\s");
                    if(tokens.length>4) {
                        if (tokens[4].equals("E")) {
                            String[] newTokens = strLine.split(":");
                            int length = newTokens.length;
                            String key = newTokens[length-2] + ":" +newTokens[length-1];
                            key = key.trim();
                            if (errorMap.containsKey(key)) {
                                errorMap.put(key, errorMap.get(key) + 1);
                            } else {
                                errorMap.put(key, 1);
                            }
                        }
                    }
                }
            }
            printUniqueErrors(errorMap);
        }
        catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printUniqueErrors(Map<String, Integer> errorMap){
        System.out.println();
        System.out.println("Errors");
        System.out.println("===============");
        for(Map.Entry<String,Integer> entry : errorMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(" | ");
            System.out.println(entry.getValue());
        }
    }
}
