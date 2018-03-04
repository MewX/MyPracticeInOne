package skill;

import role.Boss;
import role.Player;

public class Recharge extends SkillBase {
    public Recharge() {
        super(Recharge.class.getName(), 229, 5);
    }

    @Override
    public void execute(Player p, Boss b) {
        super.execute(p, b);
        p.mana += 101;
    }
}
