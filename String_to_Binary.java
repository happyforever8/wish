public class HelloWorld{

    // Driver code
    public static void main(String[] args)
    {
        String s = "geeks";
        strToBinary(s);
    }
    // utility function
    static void strToBinary(String s)
    {
        int n = s.length();
 
        for (int i = 0; i < n; i++)
        {
            // convert each char to
            // ASCII value
            int val = Integer.valueOf(s.charAt(i));
 
            // Convert ASCII value to binary
            StringBuilder sb = new StringBuilder();
            
            while (val > 0)
            {
                if (val % 2 == 1){
                    sb.append("1");
                } else{
                    sb.append("0");
                }
                val /= 2;
            }
            String result = reverse(sb.toString());
 
            System.out.print(result + " ");
        }
    }
 
    static String reverse(String input)
    {
        char[] a = input.toCharArray();
        int l = 0;
        int r = a.length - 1;
 
        while(l < r){
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            l++;
            r--;
        }
        return String.valueOf(a);
    }
 


}
