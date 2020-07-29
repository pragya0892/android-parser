package dataproviders;

import constant.DataProviderConstants;
import org.testng.annotations.DataProvider;

/**
 * @author Pragya
 */

public class StringMatcherDP {
    @DataProvider(name = "getMatchingStringParameters")
    public Object[][] getCitiesQueryParameters() {
        String stringParam1 = DataProviderConstants.matchingString1;
        String stringParam2 = DataProviderConstants.matchingString2;
        String stringParam3 = DataProviderConstants.matchingString3;
        String stringParam4 = DataProviderConstants.matchingString4;


        return new Object[][]{
                {stringParam1},
                {stringParam2},
                {stringParam3},
                {stringParam4},
        };
    }
}
