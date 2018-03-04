package skill;

import role.Boss;
import role.Player;

public class Poison extends SkillBase {
    public Poison() {
        super(Poison.class.getName(), 173, 6);
    }

    @Override
    public void execute(Player p, Boss b) {
        super.execute(p, b);
        b.hitPoints -= 3;
    }
}
