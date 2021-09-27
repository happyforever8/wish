package wish;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockFun {

	static class Stock{
        int n;
        float p;
        
        public Stock(int n, float p) {
            this.n = n;
            this.p = p;
        }
    }
	static PriorityQueue<Stock> sellOrders = new PriorityQueue<>((a,b)-> (int)a.p - (int)b.p);
    static PriorityQueue<Stock> buyOrders = new PriorityQueue<>((a,b)-> (int)b.p - (int)a.p);
    
    public static int sell(int n, float p) {
        int remaining = n;
        while(!buyOrders.isEmpty() && remaining>0 && buyOrders.peek().p >= p) {
        	Stock t = buyOrders.peek();
                if(t.n > remaining) {
                    t.n = t.n - remaining;
                    remaining = 0;
                    break;
                } else {
                    remaining  = remaining - t.n;
                    buyOrders.poll();
                }
        }
        
        if(remaining != 0) {
        	Stock s = new Stock(remaining,p);
            sellOrders.offer(s);
        }
        return (n - remaining);
    }
    
    public static int buy(int n, float p) {
        int remaining = n;
        while(!sellOrders.isEmpty() && remaining>0 && sellOrders.peek().p <= p) {
        	Stock s = sellOrders.peek();
                if(s.n > remaining) {
                    s.n = s.n - remaining;
                    remaining = 0;
                    break;
                } else {
                    remaining  = remaining - s.n;
                    sellOrders.poll();
                }
        }
        
        if(remaining != 0) {
        	Stock t = new Stock(remaining,p);
            buyOrders.offer(t);
        }
        return (n - remaining);
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        System.out.println("sell(50,(float)1.5) "+sell(50,(float)1.5));
        System.out.println("sell(20,(float)1.4) "+sell(20,(float)1.4));
        System.out.println("buy(60,(float)1.5) "+buy(60,(float)1.51));
      System.out.println("buy(20,(float)1.5) "+buy(20,(float)1.5));
       
      System.out.println("sell(20,(float)0.7) "+sell(20,(float)0.7));
        
//        System.out.println("sell(1,(float)0.10) "+sell(1,(float)0.10));
//        System.out.println("sell(1,(float)0.11) "+sell(1,(float)0.11));
//        System.out.println("sell(1,(float)0.12) "+sell(1,(float)0.12));
//        System.out.println("sell(1,(float)0.13) "+sell(1,(float)0.13));
//        System.out.println("sell(1,(float)0.14) "+sell(1,(float)0.14));
//        System.out.println("sell(1,(float)0.15) "+sell(1,(float)0.15));
//        System.out.println("sell(1,(float)0.16) "+sell(1,(float)0.16));
//        System.out.println("sell(1,(float)0.17) "+sell(1,(float)0.17));
//        
//        System.out.println("buy(100,(float)0.5) "+buy(100,(float)0.5));      
//        System.out.println("sell(93,(float)0.2) "+sell(93,(float)0.2));

}
}
