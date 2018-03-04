import role.*;
import skill.*;

import java.util.*;

public class Day22 {
    private static int minCost = Integer.MAX_VALUE;
    private static ArrayList<Class<? extends SkillBase>> skills = new ArrayList<>();

    static {
        skills.add(MagicMissile.class);
        skills.add(Drain.class);
        skills.add(Shield.class);
        skills.add(Poison.class);
        skills.add(Recharge.class);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        infiniteCastSkills(new ArrayList<>());
        System.out.println(minCost);
    }

    private static void infiniteCastSkills(List<Class<? extends SkillBase>> decidedSkills) throws IllegalAccessException, InstantiationException {
        for (Class<? extends SkillBase> c : skills) {
            // ok cast it
            decidedSkills.add(c);

            // try boss
            Player p = bossFight(decidedSkills);
            if (p.mana >= 0 && decidedSkills.size() <= Constants.DFS_DEPTH) {
                // valid sequence
                if (p.hitPoints > 0) {
                    // win
                    if (minCost > p.getCastedMana()) printSkillSequence(decidedSkills);
                    minCost = Math.min(minCost, p.getCastedMana());
                } else {
                    // lost, and cast more
                    infiniteCastSkills(decidedSkills);
                }
            }

            // ok remove the newly-added one
            decidedSkills.remove(decidedSkills.size() - 1);
        }
    }

    private static Player bossFight(final List<Class<? extends SkillBase>> decidedSkills) throws IllegalAccessException, InstantiationException {
        // build skill sequence
        List<SkillBase> skillSequence = new ArrayList<>();
        for (Class<? extends SkillBase> roundSkill : decidedSkills) {
            skillSequence.add(roundSkill.newInstance());
        }

        // fight
        Player player = new Player();
        Boss boss = new Boss();
        List<SkillBase> affectingSkills = new ArrayList<>();
        for (final SkillBase roundSkill : skillSequence) {
            // player's turn
            executeAffectionSkills(player, boss, affectingSkills);
            if (boss.hitPoints <= 0) break;

            roundSkill.preExecute(player, boss);
            if (player.mana < 0) break; // not enough mana for this skill sequence
            if (roundSkill.turnsRemaining() == 1) roundSkill.execute(player, boss);
            else affectingSkills.add(roundSkill);
            if (boss.hitPoints <= 0) break;

            // ----
            // boss's turn
            executeAffectionSkills(player, boss, affectingSkills);
            if (boss.hitPoints <= 0) break;

            int dmg = boss.damage - player.armor;
            if (dmg <= 0) dmg = 1;
            player.hitPoints -= dmg;
            if (player.hitPoints <= 0) break;
        }

        // check whether both of them are alive
        if (player.hitPoints > 0 && boss.hitPoints > 0) {
            player.hitPoints = 0;
        }

        return player;
    }

    private static void executeAffectionSkills(Player p, Boss b, List<SkillBase> affectingSkill) {
        Map<String, SkillBase> skillSet = new HashMap<>();
        for (SkillBase sb : affectingSkill) {
            skillSet.put(sb.getClass().getSimpleName(), sb);
        }
        affectingSkill = new ArrayList<>(skillSet.values());

        for (int i = affectingSkill.size() - 1; i >= 0; i --) {
            SkillBase current = affectingSkill.get(i);
            if (current.turnsRemaining() > 0) {
                // execute it
                current.execute(p, b);
            }
            if (current.turnsRemaining() <= 0) {
                // remove not affecting skill
                affectingSkill.get(i).removeEffect(p, b);
                affectingSkill.remove(i);
            }
        }
    }

    private static void printSkillSequence(final List<Class<? extends SkillBase>> decidedSkills) throws IllegalAccessException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        int countMana = 0;
        for (final Class<? extends SkillBase> c : decidedSkills) {
            SkillBase s = c.newInstance();
            countMana += s.mana;
            sb.append(c.getName() + "(" + s.mana + "), ");
        }
        System.out.println("" + countMana + ": " + sb.toString());
    }
}
