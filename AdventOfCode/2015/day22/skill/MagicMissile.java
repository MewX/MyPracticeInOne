package skill;

import role.Boss;
import role.Player;

public class MagicMissile extends SkillBase {
    public MagicMissile() {
        super(MagicMissile.class.getName(), 53);
    }

    @Override
    public void execute(Player p, Boss b) {
        super.execute(p, b);
        b.hitPoints -= 4;
    }
}
