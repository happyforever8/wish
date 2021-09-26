# Insert Delete GetRandom O(1) - Duplicates allowed

```java
class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> mp;
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        mp = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contains = mp.containsKey(val);
        if (!contains) mp.put(val, new HashSet<Integer>());
        mp.get(val).add(list.size());
        list.add(val);
        return !contains;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!mp.containsKey(val)) return false;
        
        int indexOfDeleteVal = mp.get(val).iterator().next();
        mp.get(val).remove(indexOfDeleteVal);
        if (mp.get(val).size() == 0) mp.remove(val);
        
        if (indexOfDeleteVal < list.size() - 1) {
            int lastVal = list.get(list.size() - 1);
            list.set(indexOfDeleteVal, lastVal);
            
            mp.get(lastVal).remove(list.size() - 1);
            mp.get(lastVal).add(indexOfDeleteVal);
        }
        
        list.remove(list.size() - 1);
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
   
