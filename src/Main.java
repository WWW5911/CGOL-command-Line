import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;

class cell{
    int x, y;
    String id;
    cell(int xx, int yy){
        x = xx;
        y = yy;
        id = xx +","+yy;
    }
    cell(String idd){
        StringTokenizer st = new StringTokenizer(idd, ",");
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        id = x +","+y;
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
    static int live = 3;
    static ArrayList<point> over3;
    static HashMap<String, cell> hm;
    static void mapping(){
        area = new int[15][15];
        over3 = new ArrayList<point>();
        if(!alive.isEmpty())
        for (cell cell : alive) {
            for(int i = cell.y-1; i < cell.y+2; ++i)
                for(int j = cell.x-1; j < cell.x+2; ++j){
                    if(i >= 0 && i <= 15 && j >= 0 && j <= 15){
                        area[i][j]++;
                        if(i != cell.y && j != cell.x && area[i][j] == 3)
                            over3.add(new point(j, i));
                    }
                }
        }
    }
    static void check_alive(){
        int count = alive.size();
        if(count != 0)
        for(int i = 0; i < count; ++i){
            System.out.print("(" + alive.get(i).x + " , " + alive.get(i).y + ") ");
            if(area[alive.get(i).y][alive.get(i).x] != live){
                alive.remove(i--);
                count--;
            }
        }
        System.out.println("");
    }
    static void spawn(){
        if(!over3.isEmpty())
            for(point p : over3){
                if(hm.get(p.x+","+p.y) == null)
                    alive.add(new cell(p.x, p.y));
            }
    }
    static void print_area(int num){
        for(int i = 0; i <num; ++i){
            for(int j = 0; j < num; ++j)
                System.out.print(area[i][j] + " ");
            System.out.println("");
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    //    System.out.println("live_count seed");
        String seed = bf.readLine();
        alive = new ArrayList<cell>();
        hm = new HashMap<String, cell>();
        StringTokenizer st = new StringTokenizer(seed);
//        live = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()){
            cell ce = new cell(st.nextToken());
            alive.add(ce);
            hm.put(ce.id, ce);
        }
        for(int i = 0; i < 100; ++i){
            mapping();
            print_area(15);
            check_alive();
            spawn();
            TimeUnit.SECONDS.sleep(1);
        }
         
    }
    
}
