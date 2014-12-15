package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xavi on 7/12/14.
 */
public class RestaurappRootAPI {


    private Map<String, Link> links;

    public RestaurappRootAPI() {
        links = new HashMap<String, Link>();
    }

    public Map<String, Link> getLinks() {
        return links;
    }
}
