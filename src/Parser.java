import java.util.*;

/**
 * Created by Ryan Benasutti on 8/13/2016.
 *
 * Parses one line of code at a time.
 */

public class Parser
{
    private static Parser ourInstance = new Parser();

    public static Parser getInstance()
    {
        return ourInstance;
    }

    private static String keywords[] = {"define", "use", "run", "as", "for", "with", "and", "//"};

    private static Map<String, List<String>> definitions = new HashMap<>();

    private static List<String> header = new ArrayList<>();

    private Parser()
    {
    }

    public static String parseLine(String line)
    {
        if (line.startsWith("//"))
            return "";

        String tokens[] = line.split(" ");

        if (!tokens[0].startsWith("//"))
        {
            switch (tokens[0])
            {
                case "define":
                    return parseDefinition(tokens);

                case "use":
                    try
                    {
                        return parseUse(tokens) + "\n";
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;

                default:
                    return line + "\n";
            }
        }

        return "";
    }

    private static String parseDefinition(String tokens[])
    {
        if (tokens[0].equals("define") && tokens[2].equals("as"))
            definitions.put(tokens[1], parseAndList(tokens, 3));

        return "";
    }

    private static String parseUse(String tokens[]) throws Exception
    {
        if (tokens[0].equals("use") && tokens[2].equals("for") && tokens[4].equals("with"))
        {
            String packageName = tokens[1];
            String definition = tokens[3];
            List<String> members = definitions.get(definition);

            if (members != null)
            {
                List<String> parameters = parseAndList(tokens, 5);
                String result = "";

                if (packageName.equals("SLEW"))
                    result += PackageSLEW.parse(members, parameters);
                else if (packageName.equals("TBH"))
                    result += PackageTBH.parse(members, parameters);

                return result;
            }
            else
                throw new Exception("Invalid definition for tokens: " + Arrays.toString(tokens));
        }
        else
            throw new Exception("Invalid tokens: " + Arrays.toString(tokens));
    }

    private static List<String> parseAndList(String tokens[], int start)
    {
        List<String> items = new ArrayList<>();

        for (int i = start; i < tokens.length;)
        {
            try
            {
                items.add(tokens[i]);

                if (tokens[i + 1].equals("and"))
                    i += 2;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                break;
            }
        }

        return items;
    }

    public static void addToHeader(String data)
    {
        header.add(data);
    }

    public static Map<String, List<String>> getDefinitions()
    {
        return definitions;
    }

    public static List<String> getHeader()
    {
        return header;
    }
}
