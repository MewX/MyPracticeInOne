package role;

import static role.Constants.PLAYER_HIT_POINTS;
import static role.Constants.PLAYER_MANA;

public class Player {
    public int hitPoints = PLAYER_HIT_POINTS, mana = PLAYER_MANA, armor = 0;
    private int castedMana = 0;

    public Player() {
    }

    public Player(int hp, int mana, int armor) {
        this.hitPoints = hp;
        this.mana = mana;
        this.armor = armor;
    }

    public void castedMana(int mana) {
        castedMana += mana;
        this.mana -= mana;
    }

    public int getCastedMana() {
        return castedMana;
    }
}
