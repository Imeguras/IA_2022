package enemies;

import gui.GameArea;
import gui.GameArea.state_abst;
import mummymaze.MummyMazeState;
import gui.PointDimension;

public class WhiteMummy extends Enemy{
    public WhiteMummy(PointDimension<Integer> enemy_position, String name){
        super(enemy_position, "White Mummy");
        EnemyOrderComparator enemyOrderComparator = new EnemyOrderComparator();
        Enemy.enemies.add(this);
        Enemy.enemies.sort(enemyOrderComparator);
    }



    //white mummy moves 2 squares max
    @Override
    public MummyMazeState move(){
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

        }else if(enemy_position.line.intValue() != boardState.getHero_pos().line.intValue()){
			if(canMoveUp()){
				
				System.out.println("mumia cima");
				t=moveUp();
            }else if(canMoveRight()){

				System.out.println("mumia baixo");
				t=moveDown();
			}else{
				System.out.println("Quieto Vertical");
			}

		}else{
			System.out.println("Morto!!!");
		}
		return new MummyMazeState(t);
		
    }



	@Override
	public state_abst getSymbol(){
		return state_abst.MUMMY_WHITE;
	}
}
