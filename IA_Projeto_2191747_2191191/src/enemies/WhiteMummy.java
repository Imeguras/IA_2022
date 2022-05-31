package enemies;

import gui.GameArea;
import gui.GameArea.state_abst;
import mummymaze.MummyMazeState;
import gui.PointDimension;

public class WhiteMummy extends Enemy
{
	@Override

	//verifies if can kill the hero
	public boolean canKill()
	{
		MummyMazeState test_state = move();

		return test_state.hero_dead;
	}

	public WhiteMummy(PointDimension<Integer> enemy_position, String name){
        super(enemy_position, "White Mummy");
        EnemyOrderComparator enemyOrderComparator = new EnemyOrderComparator();
        Enemy.enemies.add(this);
        Enemy.enemies.sort(enemyOrderComparator);
    }

    //white mummy moves 2 squares max
    @Override
    public MummyMazeState move()
	{
		boolean hero_dead = false;
		state_abst t[][]=this.boardState.getMatrix();
        //validate if white mummy is on the same column as the hero
		//System.out.println(enemy_position.col.intValue()+" pos heroi col: "+boardState.getHero_pos().col.intValue());
        if(enemy_position.col.intValue() != boardState.getHero_pos().col.intValue()){
            if(canMoveLeft()){
				System.out.println("mumia esquerda");
				t=moveLeft();
            }else if(canMoveRight()){

				System.out.println("mumia direita");
				t=moveRight();
			}else{
				System.out.println("Quieto Horizontal");
			}

        }else if(enemy_position.line.intValue() != boardState.getHero_pos().line.intValue())
        {
        	if(canMoveUp())
			{
				System.out.println("mumia cima");
				t=moveUp();
            }
			else if(canMoveRight())
            {
				System.out.println("mumia baixo");
				t=moveDown();
			}else
			{
				System.out.println("Quieto Vertical");
			}
		}else
		{
			System.out.println("Morto!!!");
			hero_dead = true;
		}
        MummyMazeState return_state = new MummyMazeState(t);
        return_state.hero_dead = hero_dead;
		return return_state;
    }

	@Override
	public state_abst getSymbol(){
		return state_abst.MUMMY_WHITE;
	}
}