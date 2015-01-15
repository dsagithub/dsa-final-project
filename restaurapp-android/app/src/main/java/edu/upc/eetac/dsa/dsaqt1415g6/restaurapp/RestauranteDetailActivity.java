package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.AppException;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Restaurante;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.RestaurappAPI;

/**
 * Created by xavi on 18/12/14.
 */
public class RestauranteDetailActivity extends Activity {

    private final static String TAG = RestauranteDetailActivity.class.getName();
    public String urlRestaurante;
    private String idRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurante_detail_layout);
        urlRestaurante = (String) getIntent().getExtras().get("url");
        (new FetchRestauranteTask()).execute(urlRestaurante);
    }


    private void cargarRestaurante(Restaurante restaurante) {
        TextView tvDetailNombre = (TextView) findViewById(R.id.tvDetailName);
        TextView tvDetailDireccion = (TextView) findViewById(R.id.tvDetailDireccion);
        TextView tvDetailProvincia = (TextView) findViewById(R.id.tvDetailProvincia);
        TextView tvDetailDateCreation = (TextView) findViewById(R.id.tvDetailDateCreation);
        TextView tvDetailTelefono = (TextView) findViewById(R.id.tvDetailTelefono);
        TextView tvDetailHorario = (TextView) findViewById(R.id.tvDetailHorario);
        TextView tvDetailEmail = (TextView) findViewById(R.id.tvDetailEmail);
        TextView tvDetailCategoria = (TextView) findViewById(R.id.tvDetailCategoria);
        TextView tvDetailCreador = (TextView) findViewById(R.id.tvDetailCreador);



        tvDetailCategoria.setText(restaurante.getCategoria());
        tvDetailCreador.setText(restaurante.getCreador());
        tvDetailDateCreation.setText(SimpleDateFormat.getInstance().format(restaurante.getCreationTimeStamp()));
        tvDetailDireccion.setText(restaurante.getDireccion());
        tvDetailEmail.setText(restaurante.getEmail());
        tvDetailHorario.setText(restaurante.getHorario());
        tvDetailNombre.setText(restaurante.getNombre());
        tvDetailProvincia.setText(restaurante.getProvincia());
        tvDetailTelefono.setText(restaurante.getTelefono());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_restaurapp_main, menu);
        return true;
    }


    public void MostrarOpiniones(View v){
        Intent intent = new Intent(this, ListarOpinionesActivity.class);
        intent.putExtra("idRes", idRest);
        startActivity(intent);
    }

    private class FetchRestauranteTask extends AsyncTask<String, Void, Restaurante> {
        private ProgressDialog pd;

        @Override
        protected Restaurante doInBackground(String... params) {
            Restaurante restaurante = null;
            try {
                restaurante = RestaurappAPI.getInstance(RestauranteDetailActivity.this)
                        .getRestaurante(params[0]);
            } catch (AppException e) {
                Log.d(TAG, e.getMessage(), e);
            }
            idRest = String.valueOf(restaurante.getIdRestaurante());
            return restaurante;
        }

        @Override
        protected void onPostExecute(Restaurante result) {
            cargarRestaurante(result);
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(RestauranteDetailActivity.this);
            pd.setTitle("Loading...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

    }
}
