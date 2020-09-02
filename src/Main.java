import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;

class cell{
    int x, y;
    int id;
    cell(int xx, int yy){
        x = xx;
        y = yy;
        String str = xx +""+yy;
        id = Integer.parseInt(str);
    }
    cell(String idd){
        StringTokenizer st = new StringTokenizer(idd, ",");
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        String str = x +""+y;
        id = Integer.parseInt(str);
    }
    
}
class point{
    int x, y;
    point(int xx, int yy){
        x = xx;
        y = yy;
    }
}
public class Main {
    static ArrayList<cell> alive;
    static int [][] area;
    static ArrayList<point> over3;
    static void mapping(){
        area = new int[10000][10000];
        over3 = new ArrayList<point>();
        if(!alive.isEmpty())
        for (cell cell : alive) {
            for(int i = cell.y-1; i < cell.y+2; ++i)
                for(int j = cell.x-1; j < cell.x+2; ++j){
                    if(i >= 0 && i <= 99999 && j >= 0 && j <= 99999){
                        area[i][j]++;
                        if(i != cell.y && j != cell.x && area[i][j] >= 3)
                            over3.add(new point(i, j));
                    }
                }
        }
    }
    static void check_alive(){
        int count = alive.size();
        if(count != 0)
        for(int i = 0; i < count; ++i){
            System.out.print("(" + alive.get(i).x + " , " + alive.get(i).y + ") ");
            if(area[alive.get(i).x][alive.get(i).y] > 3 ||area[alive.get(i).x][alive.get(i).y] < 2){
                alive.remove(i--);
                count--;
            }
        }
        System.out.println("");
    }
    static void spawn(){
        if(!over3.isEmpty())
            for(point p : over3)
                alive.add(new cell(p.x, p.y));
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String seed = bf.readLine();
        alive = new ArrayList<cell>();
        StringTokenizer st = new StringTokenizer(seed);
        while(st.hasMoreTokens())
            alive.add(new cell(st.nextToken()));
        for(int i = 0; i < 100; ++i){
            mapping();
            check_alive();
            spawn();
            TimeUnit.SECONDS.sleep(1);
        }
         
    }
    
}
