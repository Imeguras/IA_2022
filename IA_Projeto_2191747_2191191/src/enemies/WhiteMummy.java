package enemies;

import gui.GameArea;
import gui.PointDimension;

public class WhiteMummy extends Enemy
{
    public WhiteMummy(PointDimension<Integer> enemy_position, PointDimension<Integer> hero_position, String name)
    {
        super(enemy_position, hero_position, "White Mummy");
        EnemyOrderComparator enemyOrderComparator = new EnemyOrderComparator();
        Enemy.enemies.add(this);
        Enemy.enemies.sort(enemyOrderComparator);
    }



    //white mummy moves 2 squares max
    @Override
    public void move()
    {
        //validate if white mummy is on the same column as the hero
        if(enemy_position.col != boardState.getHero_pos().col)
        {
            if(canMoveLeft())
            {

            }
        }
    }
}
