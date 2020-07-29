package logparserTest;

import dataproviders.StringMatcherDP;
import org.testng.annotations.Test;
import parser.FatalExceptionFinder;
import parser.MatchingStringFinder;
import parser.UniqueErrorFinder;

/**
 * @author Pragya
 */

public class ParserTest {

     @Test(priority=1)
     public void testFatalException() {
         FatalExceptionFinder.findFatalExceptions();
     }

     @Test(priority=2)
     public void testUniqueErrorFinder(){
         UniqueErrorFinder.findUniqueErrors();
     }

    @Test(priority=3,dataProvider = "getMatchingStringParameters", dataProviderClass = StringMatcherDP.class)
    public void testMatchingStringFinder(String matchingString){
        MatchingStringFinder.findMatchingStrings(matchingString);
     }
}

