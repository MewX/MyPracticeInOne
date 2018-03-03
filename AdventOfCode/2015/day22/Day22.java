import java.util.ArrayList;

public class Day22 {
    private static class Player {
        int hitPoints = PLAYER_HIT_POINTS, mana = PLAYER_MANA, armor = 0;
    }
    private static class Boss {
        int hitPoints = BOSS_HIT_POINTS, damage = BOSS_DAMAGE;
    }

    // TODO: every function should be used
    private static class Skill {
        String name;
        int mana, turns;

        Skill(String name, int mana) {
            this(name, mana, 1);
        }

        Skill(String name, int mana, int turns) {
            this.name = name;
            this.mana = mana;
            this.turns = turns;
        }

        int turnsRemaining() {
            return turns;
        }

        void execute(Player p, Boss b) {
            p.mana -= mana;
            turns --;
        }

        void removeEffect(Player p, Boss b) { }
    }

    private static class MagicMissile extends Skill {
        MagicMissile() {
            super(MagicMissile.class.getName(), 53);
        }

        @Override
        void execute(Player p, Boss b) {
            super.execute(p, b);
            b.hitPoints -= 4;
        }
    }

    private static class Drain extends Skill {
        Drain() {
            super(Drain.class.getName(), 73);
        }

        @Override
        void execute(Player p, Boss b) {
            super.execute(p, b);
            p.hitPoints += 2;
            b.hitPoints -= 2;
        }
    }

    private static class Shield extends Skill {
        private boolean executed = false;

        Shield() {
            super(Shield.class.getName(), 113, 6);
        }

        @Override
        void execute(Player p, Boss b) {
            super.execute(p, b);
            if (!executed) {
                executed = true;
                p.armor += 7;
            }
        }

        @Override
        void removeEffect(Player p, Boss b) {
            p.armor -= 7;
        }
    }

    private static class Poison extends Skill {
        Poison() {
            super(Poison.class.getName(), 173, 6);
        }

        @Override
        void execute(Player p, Boss b) {
            super.execute(p, b);
            b.hitPoints -= 3;
        }
    }

    private static class Recharge extends Skill {
        Recharge() {
            super(Recharge.class.getName(), 229, 5);
        }

        @Override
        void execute(Player p, Boss b) {
            super.execute(p, b);
            p.mana += 101;
        }
    }

    private static int BOSS_HIT_POINTS = 58;
    private static int BOSS_DAMAGE = 9;
    private static int PLAYER_HIT_POINTS = 50;
    private static int PLAYER_MANA = 500;

    private static ArrayList<Class<? extends Skill>> skils = new ArrayList<>();
    static {
        skils.add(MagicMissile.class);
        skils.add(Drain.class);
        skils.add(Shield.class);
        skils.add(Poison.class);
        skils.add(Recharge.class);
    }

    public static void main(String[] args) {

    }
}
