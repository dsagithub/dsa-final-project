package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xavi on 14/12/14.
 */
public class RestauranteCollection {



    private long newestTimestamp;
    private long oldestTimestamp;

    private Map<String, Link> links = new HashMap<String, Link>();
    private List<Restaurante> restaurantes;


    public RestauranteCollection(){
        super();
        restaurantes = new ArrayList<Restaurante>();
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
    public void addRestaurantes(Restaurante restaurante) {
        restaurantes.add(restaurante);
    }

    public Restaurante getRestaurante (int idrestaurante){
        for( int i= 0 ;i<restaurantes.size();i++){
            if(restaurantes.get(i).getIdRestaurante()==idrestaurante){
                return restaurantes.get(i);
            }

        }
        return null;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

}
