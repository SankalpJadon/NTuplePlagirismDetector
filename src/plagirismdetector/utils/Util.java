package plagirismdetector.utils;
public class Util {

	public static boolean isNumber( String input )
    {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
