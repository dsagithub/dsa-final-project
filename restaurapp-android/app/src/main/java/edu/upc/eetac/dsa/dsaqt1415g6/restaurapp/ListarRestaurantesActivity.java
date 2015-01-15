package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.AppException;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Restaurante;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteCollection;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestaurappAPI;

/**
 * Created by xavi on 18/12/14.
 */
public class ListarRestaurantesActivity extends ListActivity{


    private ArrayList<Restaurante> restaurantelist;
    private RestauranteAdapter adapter;
    private final static String TAG = ListarRestaurantesActivity.class.toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_rest_layout);

        restaurantelist = new ArrayList<Restaurante>();
        adapter = new RestauranteAdapter(this, restaurantelist);
        setListAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("restapp-profile",
                Context.MODE_PRIVATE);
        final String username = prefs.getString("username", null);
        final String password = prefs.getString("password", null);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password
                        .toCharArray());
            }
        });
        (new FetchLibrosTask()).execute();
    }

    private class FetchLibrosTask extends
            AsyncTask<Void, Void, RestauranteCollection> {
        private ProgressDialog pd;

        @Override
        protected RestauranteCollection doInBackground(Void... params) {
            RestauranteCollection restaurantes = null;
            try {
                restaurantes = RestaurappAPI.getInstance(ListarRestaurantesActivity.this)
                        .getRestaurantes();
            } catch (AppException e) {
                e.printStackTrace();
            }
            return restaurantes;
        }

        @Override
        protected void onPostExecute(RestauranteCollection result) {
            addRestaurantes(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(ListarRestaurantesActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

    private void addRestaurantes(RestauranteCollection restaurantes) {
        restaurantelist.addAll(restaurantes.getRestaurantes());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Restaurante restaurante = restaurantelist.get(position);
        Log.d(TAG, restaurante.getLinks().get("self").getTarget());

        Intent intent = new Intent(this, RestauranteDetailActivity.class);
        intent.putExtra("url", restaurante.getLinks().get("self").getTarget());
        startActivity(intent);
    }
}
