import java.util.List;

/**
 * Created by Ryan Benasutti on 8/14/2016.
 */

public class PackageTBH
{
    private static PackageTBH ourInstance = new PackageTBH();

    public static PackageTBH getInstance()
    {
        return ourInstance;
    }

    private static int tbhCount = 0;

    private PackageTBH()
    {
    }

    public static String parse(List<String> members, List<String> parameters)
    {
        String result = "";

        if (parameters != null && parameters.size() == 3)
        {
            result += "vel_TBH_InitController(&tbh" + tbhCount + ", " + parameters.get(0) + ", " + parameters.get(1) + ", " + parameters.get(2) + ");";
        }

        Parser.addToHeader("vel_TBH tbh" + tbhCount + ";");
        tbhCount++;

        return result;
    }
}
