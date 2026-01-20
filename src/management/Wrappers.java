package management;

public class Wrappers {
    
    public static void VerifyMinValue(int value, int min_value) {
        if (value < min_value) {
            throw new IllegalArgumentException("Value is below minimum allowed value");
        }
    }

    public static void VerifyMaxValue(int value, int max_value) {
        if (value > max_value) {
            throw new IllegalArgumentException("Value is above maximum allowed value");
        }
    }
}