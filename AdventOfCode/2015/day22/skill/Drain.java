package skill;

import role.Boss;
import role.Player;

public class Drain extends SkillBase {
    public Drain() {
        super(Drain.class.getName(), 73);
    }

    @Override
    public void execute(Player p, Boss b) {
        super.execute(p, b);
        p.hitPoints += 2;
        b.hitPoints -= 2;
    }
}

