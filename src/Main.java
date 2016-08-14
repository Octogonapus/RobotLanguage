import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ryan Benasutti on 8/13/2016.
 */

public class Main
{
    public static void main(String[] args)
    {
        String output = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("spec/example.txt")))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                output += Parser.parseLine(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Parser.getHeader().forEach(System.out::println);
        System.out.println(output);
    }
}
