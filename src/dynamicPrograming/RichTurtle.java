package dynamicPrograming;

public class RichTurtle {
    public static void main(String[] args) {
        int[][] wayWithMoney = new int[][] {{0, 0, 1, 0, 0, 0},
                                            {0, 0, 0, 0, 1, 0},
                                            {0, 1, 0, 1, 0, 0},
                                            {0, 0, 0, 1, 0, 0},
                                            {0, 0, 0, 0, 1, 0}};
        System.out.println(new RichTurtle().wayRichTurtle(wayWithMoney));

    }

    public int wayRichTurtle(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] optimalValueArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    optimalValueArr[i][j] = map[i][j];
                    continue;
                }

                int optimalValue = map[i][j];

                if (j == 0) {
                    optimalValue += optimalValueArr[i - 1][j];
                } else if (i == 0) {
                    optimalValue += optimalValueArr[i][j - 1];
                } else {
                    optimalValue += Math.max(optimalValueArr[i - 1][j], optimalValueArr[i][j - 1]);
                }

                optimalValueArr[i][j] = optimalValue;
            }
        }

        return optimalValueArr[n - 1][m - 1];
    }
}
