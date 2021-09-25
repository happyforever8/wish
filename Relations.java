package wish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Relations {
	public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Helper h1 = new Helper();
        
        List<List<String>> l = new ArrayList<>();
        l.add(Arrays.asList("Bart", "brother", "Lisa"));
        l.add(Arrays.asList("Bart", "son", "Homer"));
        l.add(Arrays.asList("Marge", "wife", "Homer"));
        l.add(Arrays.asList("Lisa", "daughter", "Homer"));
        
        System.out.println(h1.relationship(l, "Bart", "Homer"));
        //Output: ["Bart son Homer", "Bart brother Lisa daughter Homer"]
    }
}

class Helper{
        public class Relative {
        String name;
        String relation;
        
        public Relative(String name, String relation) {
            this.name = name;
            this.relation = relation;
        }
    }
    
    
    public List<String> relationship(List<List<String>> relations, String name1, String name2) {
        Map<String,List<Relative>> map = new HashMap<String, List<Relative>>();
        for(List<String> s: relations) {
            String n1 = s.get(0);
            List<Relative> temp = map.getOrDefault(n1,new ArrayList<Relative>());
            Relative r = new Relative(s.get(2),s.get(1));
            temp.add(r);
            map.put(n1,temp);
        }
        
        List<String> res = new ArrayList<String>();
        dfs(map, name1, name2, res, new StringBuilder(), new HashSet<String>());
        return res;
    }
    
    public void dfs(Map<String, List<Relative>> map,  String n1,  String n2,  List<String> res, 
                    StringBuilder sb, HashSet<String> visited)     
    {
        sb.append(n1);
        if(n1.equals(n2)){
            res.add(sb.toString());
            return;
        }
        sb.append(" ");
        visited.add(n1);
        List<Relative> relatives = map.get(n1);
        int len = sb.length();
        
        for(Relative r: relatives) {
            if(!visited.contains(r.name)) {
                sb.append(r.relation);
                sb.append(" ");
                dfs(map, r.name, n2, res, sb, visited);
                sb.setLength(len);
            }
        }
        
    }
}
