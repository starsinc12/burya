package elements.enemies;

import elements.Enemy;
import elements.Hero;
import logic.GameLogic;

import java.awt.*;

public class Tower extends Enemy {

    private int attackTimer;
    private int attackSpeed;
    private int attackDelay;

    @Override
    public void hit(double heroDamage) {

    }

    public Tower(int level, int tilex, int tiley) {
        this.level = level;
        x = tilex;
        y = tiley;
        health = 20 + (level - 1) * 4;
        expForKill = 15 + (level - 1) * 4;
        damage = 200;
        attackTimer = 0;
        attackSpeed = 120;
        attackDelay = 3600 / (attackSpeed + 60);
        if (attackDelay < 6) attackDelay = 6;

    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getR() {
        return 0;
    }

    @Override
    public void xpifDed() {
        Hero.setHxp(Hero.getHxp() + expForKill);
    }

    @Override
    public void update() {
        if (attackTimer == 0) {
            GameLogic.hero.hit(damage);
            attackTimer = attackDelay;
        }
        --attackTimer;
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
