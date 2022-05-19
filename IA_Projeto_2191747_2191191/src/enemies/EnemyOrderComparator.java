package enemies;

import java.util.Comparator;

public class EnemyOrderComparator implements Comparator<Enemy>
{
    @Override
    public int compare(Enemy o1, Enemy o2)
    {
        return o1.getName().compareTo(o2.getName());
    }
}
