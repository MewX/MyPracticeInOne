package skill;

import role.Boss;
import role.Player;

// TODO: every function should be used
public class SkillBase {
    public final String name;
    public final int mana;
    private int turns;

    SkillBase(String name, int mana) {
        this(name, mana, 1);
    }

    SkillBase(String name, int mana, int turns) {
        this.name = name;
        this.mana = mana;
        this.turns = turns;
    }

    public int turnsRemaining() {
        return turns;
    }

    /**
     * Executed before execute() function
     */
    public void preExecute(Player p, Boss b) {
        p.castedMana(mana);
    }

    /**
     * Executed each turn
     */
    public void execute(Player p, Boss b) {
        turns--;
    }

    /**
     * Executed after all turns used
     */
    public void removeEffect(Player p, Boss b) {
    }
}
