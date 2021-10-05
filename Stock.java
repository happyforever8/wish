implement a trading system which has the following components:
1. buy(num_of_product, price)
Called by buyer to buy certain number of products with a maximum price
num_of_product (INTEGER): number of products to buy
price (FLOAT): maximal price/unit the buyer is willing to pay
Return (INTEGER): number of products can be bought

2. sell(num_of_product, price)
Called by seller to sell certain number of products with a minimum price
num_of_product (INTEGER): number of products to sell
price (FLOAT): minimal price/unit the seller is willing to sell
Return (INTEGER): number of products can be sold

Examples:
system = TradingSystem()
system.sell(50, 1.5)  
return: 0
system.sell(20, 1.4)  
return: 0
system.buy(60, 1.51) 
return: 60
system.buy(20, 1.5) 
return: 10
system.sell(20, 0.7)
return: 10
system.buy(100, 0.6)
return: 0

My Solution: Using Heaps

Maintain min-heap for outstanding sellOrders
Maintain max-heap for outstanding buyOrders
When a buy order comes in match it with outstanding sell orders starting min selling price available, 
i.e. from sellOrders until all requested no. of products can be fulfilled or min selling price available is >
	buy price in the order. If can't fulfill the order add it in buyOrders heap with the remaining no. of items available.
When a sell order comes in match it with outstanding buy order starting max buying price available, 
i.e. from buyOrders until all requested no. of
products can be fulfilled or max buying price available is < selling price in the order.
	If can't fulfill the order add it in sellOrder heap with the remaining no. of items available.
Wondering if there is a more optimized way to solve the problem using any other data structure.

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
