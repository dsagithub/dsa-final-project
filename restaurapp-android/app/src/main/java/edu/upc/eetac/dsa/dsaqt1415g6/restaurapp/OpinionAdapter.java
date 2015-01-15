package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Opinion;
import edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api.Restaurante;

/**
 * Created by xavi on 22/12/14.
 */
public class OpinionAdapter extends BaseAdapter{



    private ArrayList<Opinion> data;
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
        return ((Opinion) getItem(position)).getIdopinion();
    }

    private static class ViewHolder {
        TextView tvTitulo;
        TextView tvUsername;
        TextView tvTexto;
        TextView tvPuntuacion;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_detail_opinion, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitulo = (TextView) convertView
                    .findViewById(R.id.tvTitulo);
            viewHolder.tvUsername = (TextView) convertView
                    .findViewById(R.id.tvUsername);
            viewHolder.tvTexto = (TextView) convertView
                    .findViewById(R.id.tvTexto);
            viewHolder.tvPuntuacion = (TextView) convertView
                    .findViewById(R.id.tvPunctuation);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String titulo = data.get(position).getTitulo();
        String username = data.get(position).getUsername();
        String texto = data.get(position).getTexto();
        String puntuacion = String.valueOf(data.get(position).getPuntuacion());


        Log.d(titulo, "creador");
        Log.d(username,"name");
        Log.d(texto,"provincia");
        viewHolder.tvTitulo.setText(titulo);
        viewHolder.tvUsername.setText(username);
        viewHolder.tvTexto.setText(texto);
        viewHolder.tvPuntuacion.setText("Puntuacion: "+puntuacion);

        return convertView;
    }

    public OpinionAdapter(Context context, ArrayList<Opinion> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
}
