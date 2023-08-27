package dynamicPrograming;

public class FindMinCoins {
    public static void main(String[] args) {
        System.out.println(new FindMinCoins().findMinCoins(new int[]{10, 20, 25}, 40));
        System.out.println(new FindMinCoins().findMinCoins2(new int[]{1, 5, 10, 20, 25}, 40));
    }

    public int findMinCoins(int[] valueCoins, int sum) {
        // если сумма равна 0, монеты не нужны
        if (sum == 0) {
            return 0;
        }

        // возвращаем бесконечность, если сумма становится отрицательной
        if (sum < 0) {
            return Integer.MAX_VALUE;
        }

        // инициализируем минимальное количество необходимых монет до бесконечности
        int coins = Integer.MAX_VALUE;

        // делаем для каждой монеты
        for (int coin : valueCoins) {
            // повторяемся, чтобы увидеть, можно ли достичь общей суммы, включив текущую монету c
            int result = findMinCoins(valueCoins, sum - coin);

            // обновляем минимально необходимое количество монет, если выбираем текущий
            // монета привела к решению
            if (result != Integer.MAX_VALUE) {
                coins = Integer.min(coins, result + 1);
            }
        }

        // вернуть минимально необходимое количество монет
        return coins;
    }


    // Функция для нахождения минимально необходимого количества монет
    public int findMinCoins2(int[] valueCoins, int target) {
        // dp[i] хранит минимальное количество монет, необходимое для получения в сумме i
        int[] dp = new int[target + 1];

        for (int i = 1; i <= target; i++) {
            // инициализируем минимальное количество необходимых монет до бесконечности
            dp[i] = Integer.MAX_VALUE;
            int result;

            // делаем для каждой монеты
            for (int coin : valueCoins) {
                if (i - coin < 0) {
                    continue;
                }

                result = dp[i - coin];

                // если общая сумма может быть достигнута путем включения текущей монеты c,
                // обновляем минимально необходимое количество монет dp[i]
                if (result != Integer.MAX_VALUE) {
                    dp[i] = Integer.min(dp[i], result + 1);
                }
            }
        }

        // dp[target] хранит минимальное количество монет, необходимое для
        // получаем всего target
        return dp[target];
    }
}
