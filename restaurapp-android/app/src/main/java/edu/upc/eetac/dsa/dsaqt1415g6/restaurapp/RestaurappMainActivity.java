package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;


public class RestaurappMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurapp_main);
    }

    public void BuscarBarcelona(View v){
        String provincia = "Barcelona";
        StartListActivity(provincia);
    }
    public void BuscarTarragona(View v){
        String provincia = "Tarragona";
        StartListActivity(provincia);
    }
    public void BuscarLleida(View v){
        String provincia = "Lleida";
        StartListActivity(provincia);
    }
    public void BuscarGirona(View v){
        String provincia = "Girona";
        StartListActivity(provincia);
    }

    private void StartListActivity(String provincia) {
        Intent intent = new Intent(this, ListarRestaurantesActivity.class);
        intent.putExtra("provincia", provincia);
        startActivity(intent);
    }

    public void MostrarMapa (View v){
        StartMapActivity();
        System.out.println("mostrar mapa boton");
    }

    private void StartMapActivity() {
        System.out.println("mostrar mapa startmapactivity");
        Intent intent = new Intent(this, map_activity.class);
        startActivity(intent);
    }
}
