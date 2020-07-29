package parser;
import constant.Constants;

import java.io.*;
import java.util.*;

/**
 * @author Pragya
 */

public class FatalExceptionFinder {
    public static void findFatalExceptions() {
        try{
            FileInputStream fileInputStream = new FileInputStream(Constants.FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            Map<String,Integer> countMap = new HashMap<String,Integer>();
            Map<String,String> stackTraceMap = new HashMap<String,String>();
            while ((strLine = br.readLine()) != null)   {
                if(isFatal(strLine)){
                    br.readLine();
                    String exception = br.readLine();
                    if( exception.contains(Constants.JAVA_LANG)){
                        int index = exception.indexOf(Constants.JAVA_LANG);
                        String substring = exception.substring(index);

                        if(countMap.containsKey(substring))
                            countMap.put(substring, countMap.get(substring)+1);
                        else{
                            countMap.put(substring,1);
                        }

                        if(!stackTraceMap.containsKey(substring)){
                            if(substring.contains(Constants.STACK_TRACE)){
                                stackTraceMap.put(substring,null);
                            }
                            else{
                                String str;
                                StringBuilder sb = new StringBuilder();
                                while((str = br.readLine()).contains("at")){
                                    int i = str.indexOf("at");
                                    String sub = str.substring(i);
                                    sb.append(sub).append("\n");
                                }
                                stackTraceMap.put(substring,sb.toString());
                            }
                        }
                    }
                }
            }

            printFatalExceptionsWithCount(countMap);
            printStackTrace(stackTraceMap);

            fileInputStream.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static boolean isFatal(String strLine){
        if(strLine.contains(Constants.FATAL_EXCEPTION))
            return true;
        return false;
    }

    private static void printFatalExceptionsWithCount(Map<String,Integer> countMap){
        System.out.println();
        System.out.println("FATAL EXCEPTION");
        System.out.println("===============");
        for(Map.Entry<String, Integer> entry: countMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(" | ");
            System.out.println(entry.getValue());
        }
    }

    private static void printStackTrace(Map<String,String> stackTraceMap){
        System.out.println();
        System.out.println("Stacktrace:");
        System.out.println("===============");
        int count=1;
        for(Map.Entry<String, String> stackEntry: stackTraceMap.entrySet()){
            System.out.print("#" + count + ")" + stackEntry.getKey());
            System.out.println();
            if(stackEntry.getValue() != null )
                System.out.println(stackEntry.getValue());
            count++;
        }
    }

}
