package dynamicPrograming;

import java.util.HashMap;
import java.util.Map;

public class GrassHopper {
    public static void main(String[] args) {
        System.out.println(new GrassHopper().jumpGrassHopper(10));
//        System.out.println(new GrassHopper().jumpGrassHopperWithMemo(10));
//        System.out.println(new GrassHopper().jumpGrassHopperWithArrayMemo(10));
    }

    public int jumpGrassHopper(int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 3) {
            return n;
        }

        return jumpGrassHopper(n - 1) + jumpGrassHopper(n - 2) + jumpGrassHopper(n - 3);
    }









    Map<Integer, Integer> valueForCoordinates = new HashMap<>() {{
        put(0, 1);
        put(1, 1);
        put(2, 2);
    }};

    public int jumpGrassHopperWithMemo(int n) {
        if (valueForCoordinates.containsKey(n)) {
            return valueForCoordinates.get(n);
        }

        int value = jumpGrassHopperWithMemo(n - 1) + jumpGrassHopperWithMemo(n - 2) + jumpGrassHopperWithMemo(n - 3);
        valueForCoordinates.put(n, value);

        return value;
    }


    public int jumpGrassHopperWithArrayMemo(int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
