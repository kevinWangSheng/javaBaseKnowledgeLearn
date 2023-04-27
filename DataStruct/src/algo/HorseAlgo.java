package algo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author wang
 * @create 2023-2023-14-18:43
 */
public class HorseAlgo {
    private int X;
    private int Y;
    private boolean[] visited;
    private boolean finished;

    public static void main(String[] args) {
        HorseAlgo horse = new HorseAlgo();
        horse.X = 8;
        horse.Y = 8;

        int row = 1;
        int column = 1;
        int [][] check = new int[horse.Y][horse.X];
        horse.visited = new boolean[horse.X* horse.Y];
        long startTime = System.currentTimeMillis();

        horse.travleHorse(check,row-1,column-1,1);
        long endTime = System.currentTimeMillis();
        System.out.println("the time that you cost is "+(endTime - startTime)+"ms");

        for(int i = 0;i<check.length;i++){
            System.out.println(Arrays.toString(check[i]));
        }
    }


    public void travleHorse(int [][] checks,int row,int column,int step){
        checks[row][column] = step;
        visited[row*X+column] = true;

        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);

        while(!ps.isEmpty())
        {
            Point point = ps.remove(0);
            if(!visited[point.y*X+point.x]){
                travleHorse(checks,point.y,point.x,step+1);
            }
        }

        if(step<X*Y&&!finished){
            checks[row][column] = 0;
            visited[row*X+column] = false;
        }else{
            finished = true;
        }
    }


    public ArrayList<Point> next(Point curPoint){
        if(curPoint==null){
            return null;
        }
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if((p1.x = curPoint.x-2)>=0 && (p1.y = curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x+2)<X && (p1.y = curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x-1)>=0 && (p1.y = curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x+1)<X && (p1.y = curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x+1)<X && (p1.y = curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x-1)>=0 && (p1.y = curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x+2)<X && (p1.y = curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x-2)>=0 && (p1.y = curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }

        return ps;
    }

    public ArrayList<Point> sort(ArrayList<Point> ps){
        ps.sort((o1,o2)->{
            int count1 = next((Point) o1).size();
            int count2 = next((Point) o2).size();

            if(count1<count2){
                return -1;
            }else if(count2==count1)
            {
                return 0;
            }else {
                return 1;
            }
        });

        return ps;
    }
}
