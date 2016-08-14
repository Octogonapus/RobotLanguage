/**
 * Created by Ryan Benasutti on 8/13/2016.
 */

public class Main
{
    public static void main(String[] args)
    {
        Parser.parseLine("define driveTrain as leftDrive and rightDrive and");
        Parser.parseLine("define flywheel as leftFlywheel and rightFlywheel");

        System.out.println(Parser.getDefinitions());

        System.out.println(Parser.parseLine("use SLEW for driveTrain with MOTOR_FAST_SLEW_RATE"));
        System.out.println(Parser.parseLine("use TBH for flywheel with flywheelQuad and 0.25 and 75"));

        System.out.println(Parser.parseLine("run initializeSensors with 2"));

        System.out.println(Parser.getHeader());
    }
}
