import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
    static int live2 = 2;
    static int max = 15; 
    static ArrayList<point> over3;
    static HashMap<String, cell> hm;
    static void mapping(){
        area = new int[max][max];
        over3 = new ArrayList<point>();
        if(!alive.isEmpty())
        for (cell cell : alive) {
            for(int i = cell.y-1; i < cell.y+2; ++i)
                for(int j = cell.x-1; j < cell.x+2; ++j){
                    if(i >= 0 && i < max && j >= 0 && j < max){
                        if(i == cell.y && j == cell.x) continue;
                            area[i][j]++;
                            if(area[i][j] == 3)
                                if(hm.get(j+","+i) == null)
                                    over3.add(new point(j, i));
                    }
                }
        }
    }
    static void check_alive(){
        int count = alive.size();
        if(count != 0)
        for(int i = 0; i < count; ++i){
   //         System.out.print("(" + alive.get(i).x + " , " + alive.get(i).y + ") ");
            if(area[alive.get(i).y][alive.get(i).x] != live && area[alive.get(i).y][alive.get(i).x] != live2){
                hm.remove(alive.get(i).x+","+alive.get(i).y);
                alive.remove(i--);
                count--;
            }
        }
 //       System.out.println("");
    }
    static void spawn(){
        if(!over3.isEmpty())
            for(point p : over3){
                if(hm.get(p.x+","+p.y) == null){
                    cell temp = new cell(p.x, p.y);
                    alive.add(temp);
                    hm.put(temp.id, temp);
                }
            }
    }
    static void print_area(int num){
        for(int i = 0; i <num; ++i){
            for(int j = 0; j < num; ++j)
                System.out.print(area[i][j] + " ");
            System.out.println("");
        }
    }
    static void display(){
        for(int i = 0; i < max; ++i){
            for(int j = 0; j < max; ++j){
                if(hm.get(j+","+i) != null){
                    System.out.print("  ");
                }else System.out.print("██");
            }
            System.out.println("");
        }
        System.out.flush();  
        System.out.println("");
    }
    static void intiSeed(int seed){
        Random r1 = new Random(seed);
        int maxx = r1.nextInt(max*max);
        for(int i = 0; i < maxx ; ++i){
            int x = r1.nextInt(max), y = r1.nextInt(max);
            if(hm.get(x+","+y) == null){
                cell ce = new cell(x, y);
                alive.add(ce);
                hm.put(ce.id, ce);
            } 
        }
    }
    static void appoint_inti(String str){
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()){
            cell ce = new cell(st.nextToken());
            alive.add(ce);
            hm.put(ce.id, ce);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String seed = bf.readLine();
        alive = new ArrayList<cell>();
        hm = new HashMap<String, cell>();
     //   appoint_inti(seed);
        intiSeed(Integer.parseInt(seed));
        for(int i = 0; i < 100; ++i){
            display();
            mapping();
     //       print_area(max);
            check_alive();
            spawn();
            TimeUnit.MILLISECONDS.sleep(500);
        }
         
    }
    
}
