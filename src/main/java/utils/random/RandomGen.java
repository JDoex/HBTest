package utils.random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGen {
    public static int getNumberBetween(int low, int high) {
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

    public static int getNumericRandom(int count){
        return Integer.parseInt(RandomStringUtils.randomNumeric(count));
    }
}