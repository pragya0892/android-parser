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
public class MatchingStringFinder {

    public static void findMatchingStrings(String str){
        try {
            FileInputStream fileInputStream = new FileInputStream(Constants.FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            Map<String,Integer> occuranceMap = new HashMap<String, Integer>();

            while((strLine = br.readLine()) != null) {
                if(strLine.contains(str)){
                    int index = strLine.indexOf(str);
                    String substring = strLine.substring(index);
                    if (occuranceMap.containsKey(substring)) {
                        occuranceMap.put(substring, occuranceMap.get(substring) + 1);
                    } else {
                        occuranceMap.put(substring, 1);
                    }
                }
            }

            printMatchingStrings(occuranceMap);
        }
        catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printMatchingStrings(Map<String, Integer> occuranceMap){
        System.out.println();
        System.out.println("Matching Strings");
        System.out.println("===============");
        for(Map.Entry<String,Integer> entry : occuranceMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.print("|");
            System.out.println(entry.getValue());
        }
    }
}
