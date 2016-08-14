import java.util.List;

/**
 * Created by Ryan Benasutti on 8/14/2016.
 */

public class PackageSLEW
{
    private static PackageSLEW ourInstance = new PackageSLEW();

    public static PackageSLEW getInstance()
    {
        return ourInstance;
    }

    private PackageSLEW()
    {
    }

    public static String parse(List<String> members, List<String> parameters)
    {
        String result = "";

        for (String member : members)
        {
            if (parameters != null && parameters.size() == 1)
                result += "addMotor(" + member + ", " + parameters.get(0) + ");";
            else
                result += "addMotor(" + member + ");";
        }

        return result;
    }
}
