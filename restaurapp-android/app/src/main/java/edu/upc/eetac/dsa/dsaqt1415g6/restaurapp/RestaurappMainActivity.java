package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class RestaurappMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurapp_main);
    }


    public void BuscarBarcelona(View v){

        StartListActivity();
    }

    private void StartListActivity() {
        Intent intent = new Intent(this, ListarRestaurantesActivity.class);
        startActivity(intent);
        finish();
    }
}
