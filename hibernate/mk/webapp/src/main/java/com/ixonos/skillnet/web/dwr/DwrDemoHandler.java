package com.ixonos.skillnet.web.dwr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 6, 2009
 * Time: 2:50:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("dwrDemoHandler")
public class DwrDemoHandler {
    
    @Secured("ROLE_USER")
    public List<String> getResult() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("sample1");
        arr.add("sample2");
        arr.add("sample3");
        arr.add("sample4");
        arr.add("sample5");
        return arr;
    }
    
}
