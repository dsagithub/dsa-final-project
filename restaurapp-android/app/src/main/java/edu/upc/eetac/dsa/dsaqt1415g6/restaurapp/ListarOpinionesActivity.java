package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.AppException;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Restaurante;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestauranteCollection;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestaurappAPI;

/**
 * Created by xavi on 22/12/14.
 */
public class ListarOpinionesActivity extends ListActivity {



    private ArrayList<Opinion> opinioneslist;
    private OpinionAdapter adapter;


    private final static String TAG = ListarOpinionesActivity.class.toString();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_rest_layout);
        String urlRestaurante = (String) getIntent().getExtras().get("url");

        opinioneslist = new ArrayList<Opinion>();
        adapter = new OpinionAdapter(this, opinioneslist);
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
        (new FetchOpinionTask()).execute(urlRestaurante);
    }



    private class FetchOpinionTask extends AsyncTask<String, Void, Restaurante> {
        private ProgressDialog pd;

        @Override
        protected Restaurante doInBackground(String... params) {
            Restaurante opiniones = null;
            try {
                opiniones = RestaurappAPI.getInstance(ListarOpinionesActivity.this)
                        .getOpiniones(params[0]);
            } catch (AppException e) {
                e.printStackTrace();
            }
            return opiniones;
        }

        @Override
        protected void onPostExecute(Restaurante result) {
            addOpiniones(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(ListarOpinionesActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }

    private void addOpiniones(Restaurante opiniones) {
        opinioneslist.addAll(opiniones.getOpiniones());
        adapter.notifyDataSetChanged();
    }
}
