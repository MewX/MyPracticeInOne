package skill;

import role.Boss;
import role.Player;

public class Shield extends SkillBase {
    public Shield() {
        super(Shield.class.getName(), 113, 6);
    }

    @Override
    public void preExecute(Player p, Boss b) {
        super.preExecute(p, b);
        p.armor += 7;
    }

    @Override
    public void removeEffect(Player p, Boss b) {
        super.removeEffect(p, b);
        p.armor -= 7;
    }
}
