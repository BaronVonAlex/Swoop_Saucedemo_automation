package ge.tbcacad.data.swoop;

import org.testng.annotations.DataProvider;

public class SwoopDataProvider {
    @DataProvider(name = "SwoopRangeDP")
    public Object[][] rangeValues() {
        return new Object[][]{
                {"50", "75"},
                {"75", "100"},
                {"150", "250"},
                {"300", "400"},
                {"450", "600"}
        };
    }
}
