package cz.heroult.pavel.bohatstvoKnihoven.kap02;

import java.util.*;

public class UnboundedWildcard {
  public static void main(String[] args) {
    ArrayList<Object> ar = new ArrayList<Object>();
    ar.add("jedna");                    
    ar.add(new Integer(2));                    
    tisk(ar);      
  }
  
  public static void tisk(ArrayList<?> ar) {
    for (int i = 0; i < ar.size(); i++) {
      Object o = ar.get(i);
      if (o instanceof String) {        
        System.out.println(o.toString());
      }
      if (o instanceof Integer) {        
        System.out.println(((Integer) o).intValue());
      }
    } 
  }
}

