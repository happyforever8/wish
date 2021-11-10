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
   



==== without duplicate===========


class RandomizedSet {
    
    // follow up duplicate allow, check below 
    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    //-231 <= val <= 231 - 1
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }
  // Retrieve an index of element to delete from the hashmap.

  //Move the last element to the place of the element to delete,O(1) time.

//Pop the last element out, O(1) time.
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        int index = map.remove(val);
        int value = list.remove(list.size() - 1);
        
        if (val != value){
            map.put(value, index);
            list.set(index, value);
        }
        return true;
    }
    
    //There will be at least one element in the data structure when getRandom is called.
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
