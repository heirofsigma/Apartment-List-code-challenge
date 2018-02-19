package solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;



/**
 *
 * @author Alwxander
 */
public class Solution{
    public Solution() {
    }
    
    public void computeFriend(List<String> library) {
        
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        int count = 1;
        queue.offer(library.get(0));  
        hash.add(library.get(0)); 
        List<List<String>> lib = new ArrayList<>();
        int maxlength = 0;
       
        for (int i = 0; i < library.size(); i++) {
            int length = library.get(i).length();
            if (length > maxlength) {
                maxlength = length;
            }
        }//end of for, compute the max length
       
        for (int i = 0; i < maxlength; i++) {
            List<String> newone = new ArrayList<>();
            lib.add(newone);
        }//add new list
       
        for (int i = 0; i < library.size(); i++) {
            String node = library.get(i);
            int length = node.length();
            lib.get(length - 1).add(node); 
        }//put elements into different lists in term of their length 
        
        while (!queue.isEmpty()) {
            String node = queue.poll();
            int len = node.length();
            
            for (int i = 0; i < lib.get(len - 1).size(); i++) {
                String newnode = lib.get(len - 1).get(i);
                
                if (hash.contains(newnode)) {
                    continue;
                }
                
                if (isOneEditDistance(node, newnode)) {
                    queue.offer(newnode);
                    hash.add(newnode);
                    count++;          
                }
            }//search same size list
            
            if (len > 1) {
            
                for (int i = 0; i < lib.get(len - 2).size(); i++) {
                    String newnode = lib.get(len - 2).get(i);
                    
                    if (hash.contains(newnode)) {
                        continue;
                    }
                
                    if (isOneEditDistance(node, newnode)) {
                        queue.offer(newnode);
                        hash.add(newnode);
                        count++;          
                    }
                    
                }
             
            }//search the list that the length of the elements in it is 1 size smaller that current element's length
             
            if (len < maxlength) {
             
                for (int i = 0; i < lib.get(len).size(); i++) {
                    String newnode = lib.get(len).get(i);
                
                    if (hash.contains(newnode)) {
                        continue;
                    }
                
                    if (isOneEditDistance(node, newnode)) {
                        queue.offer(newnode);
                        hash.add(newnode);
                        count++;          
                    }
                }//same size + 1     
            }
            
        System.out.println(count);
        
        }//end of while
        
        System.out.print(count);
        return;               
    }
    
    
    public boolean isOneEditDistance(String s, String t) {
    if(s==null || t==null)
        return false;
 
    int m = s.length();
    int n = t.length();
 
    if(Math.abs(m-n)>1){
        return false;
    }
 
    int i=0; 
    int j=0; 
    int count=0;
 
    while(i<m&&j<n){
        if(s.charAt(i)==t.charAt(j)){
            i++;
            j++;
        }else{
            count++;
            if(count>1)
                return false;
 
            if(m>n){
                i++;
            }else if(m<n){
                j++;
            }else{
                i++;
                j++;
            }
        }
    }
 
    if(i<m||j<n){
        count++;
    }
 
    if(count==1)
        return true;
 
    return false;
}
    
    
    
    
    public static void main(String[] args){
      
       
      try{
        List<String> library = new ArrayList<>();
        Solution s = new Solution();
        FileInputStream inputStream = new FileInputStream("dictionary.txt");  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
              
        String str = null;
        
        while((str = bufferedReader.readLine()) != null)  
        {  
            //System.out.println(str);
            library.add(str);
        }  
        
        s.computeFriend(library);
        inputStream.close();  
        bufferedReader.close();  
        
      } catch(Exception e){
          System.out.print(e);
      }
      
      return;
      
    }//end of main
}
