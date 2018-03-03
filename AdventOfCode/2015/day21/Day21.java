public class Day21 {
    private static class Item {
        String name;
        int cost, damage, armor;

        Item(String name, int cost, int damage, int armor) {
            this.name = name;
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }
    }

    // boss stats as inputs
    private static final int BOSS_HIT_POINTS = 100;
    private static final int BOSS_DAMAGE = 8;
    private static final int BOSS_ARMOR = 2;
    private static final int PLAYER_HIT_POINTS = 100;
    private static int totalCost = 0;
    private static int totalDamage = 0;
    private static int totalArmor = 0;

    private static final Item[] weapons, armors, rings;

    static {
        // regex used to generate those:
        // (\w+?)\s+(\w+?)\s+(\w+?)\s+(\w+)  ->  new Item("\1", \2, \3, \4),
        weapons = new Item[] {
                new Item("Dagger", 8, 4, 0),
                new Item("Shortsword", 10, 5, 0),
                new Item("Warhammer", 25, 6, 0),
                new Item("Longsword", 40, 7, 0),
                new Item("Greataxe", 74, 8, 0),
        };
        armors = new Item[] {
                new Item("NoArmor", 0, 0, 0), // trick
                new Item("Leather", 13, 0, 1),
                new Item("Chainmail", 31, 0, 2),
                new Item("Splintmail", 53, 0, 3),
                new Item("Bandedmail", 75, 0, 4),
                new Item("Platemail", 102, 0, 5),
        };
        rings = new Item[] {
                new Item("NoRing1", 0, 0, 0), // trick
                new Item("NoRing2", 0, 0, 0), // trick
                new Item("Damage +1", 25, 1, 0),
                new Item("Damage +2", 50, 2, 0),
                new Item("Damage +3", 100, 3, 0),
                new Item("Defense +1", 20, 0, 1),
                new Item("Defense +2", 40, 0, 2),
                new Item("Defense +3", 80, 0, 3),
        };
    }

    public static void main(String[] args) {
        int minCost = Integer.MAX_VALUE;
        // choose an armor
        for (final Item curWeapon : weapons) {
            addStats(curWeapon);

            // choose an armor
            for (final Item curArmor : armors) {
                addStats(curArmor);

                // choose 0-2 rings and fight with boss
                for (int idxRing1 = 0; idxRing1 < rings.length; idxRing1++) {
                    final Item curRing1 = rings[idxRing1];
                    addStats(curRing1);

                    for (int idxRing2 = idxRing1 + 1; idxRing2 < rings.length; idxRing2++) {
                        final Item curRing2 = rings[idxRing2];
                        addStats(curRing2);

                        if (canWinBoss()) {
                            System.out.println("Can win at cost: " + totalCost);
                            minCost = Math.min(minCost, totalCost);
                        }

                        removeStats(curRing2);
                    }
                    removeStats(curRing1);
                }
                removeStats(curArmor);
            }
            removeStats(curWeapon);
        }

        // output
        System.out.println(minCost);
    }

    private static void addStats(final Item item) {
        totalCost += item.cost;
        totalDamage += item.damage;
        totalArmor += item.armor;
    }

    private static void removeStats(final Item item) {
        totalCost -= item.cost;
        totalDamage -= item.damage;
        totalArmor -= item.armor;
    }

    private static boolean canWinBoss() {
        int bossHitPoints = BOSS_HIT_POINTS;
        int playerHitPoints = PLAYER_HIT_POINTS;

        int playerDmgBoss = totalDamage - BOSS_ARMOR;
        if (playerDmgBoss <= 0) playerDmgBoss = 1;
        int bossDmgPlayer = BOSS_DAMAGE - totalArmor;
        if (bossDmgPlayer <= 0) bossDmgPlayer = 1;

        while (true) {
            bossHitPoints -= playerDmgBoss;
            if (bossHitPoints <= 0) break;

            playerHitPoints -= bossDmgPlayer;
            if (playerHitPoints <= 0) break;
        }
        return bossHitPoints <= 0;
    }
}
