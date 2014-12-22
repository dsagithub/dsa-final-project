package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Restaurante;

/**
 * Created by xavi on 18/12/14.
 */
public class RestauranteAdapter extends BaseAdapter{


    private ArrayList<Restaurante> data;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Restaurante) getItem(position)).getIdRestaurante();
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvCreador;
        TextView tvProvincia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.detalles_listado_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvCreador = (TextView) convertView
                    .findViewById(R.id.tvCreador);
            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tvNombre);
            viewHolder.tvProvincia = (TextView) convertView
                    .findViewById(R.id.tvProvincia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String nombre = data.get(position).getNombre();
        String creador = data.get(position).getCreador();
        String provincia = data.get(position).getProvincia();


        Log.d(creador,"creador");
        Log.d(nombre,"name");
        Log.d(provincia,"provincia");
        viewHolder.tvCreador.setText(creador);
        viewHolder.tvName.setText(nombre);
        viewHolder.tvProvincia.setText(provincia);

        return convertView;
    }

    public RestauranteAdapter(Context context, ArrayList<Restaurante> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }



}
