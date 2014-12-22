package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp;

import android.content.Context;
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
        TextView tvAutor;
        TextView tvHorario;
        TextView tvTelefono;
        TextView tvEmail;
        TextView tvCategoria;
        TextView tvProvincia;
        TextView tvCreacionDate;
        TextView tvDireccion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listar_rest_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvAutor = (TextView) convertView
                    .findViewById(R.id.tvDetailCreador);
            viewHolder.tvCategoria = (TextView) convertView
                    .findViewById(R.id.tvDetailCategoria);
            viewHolder.tvCreacionDate = (TextView) convertView
                    .findViewById(R.id.tvDetailDateCreation);
            viewHolder.tvDireccion = (TextView) convertView
                    .findViewById(R.id.tvDetailDireccion);
            viewHolder.tvEmail = (TextView) convertView
                    .findViewById(R.id.tvDetailEmail);
            viewHolder.tvHorario = (TextView) convertView
                    .findViewById(R.id.tvDetailHorario);
            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tvDetailName);
            viewHolder.tvTelefono = (TextView) convertView
                    .findViewById(R.id.tvDetailTelefono);
            viewHolder.tvProvincia = (TextView) convertView
                    .findViewById(R.id.tvDetailProvincia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String nombre = data.get(position).getNombre();
        String autor = data.get(position).getCreador();
        String horario = data.get(position).getHorario();
        String categoria = data.get(position).getCategoria();
        String direccion = data.get(position).getDireccion();
        String creacion_date = SimpleDateFormat.getInstance().format(
                data.get(position).getCreationTimeStamp());
        String email = data.get(position).getEmail();
        String provincia = data.get(position).getProvincia();
        String telefono = data.get(position).getTelefono();



        viewHolder.tvAutor.setText(autor);
        viewHolder.tvCreacionDate.setText(creacion_date);
        viewHolder.tvProvincia.setText(provincia);
        viewHolder.tvTelefono.setText(telefono);
        viewHolder.tvName.setText(nombre);
        viewHolder.tvCategoria.setText(categoria);
        viewHolder.tvDireccion.setText(direccion);
        viewHolder.tvHorario.setText(horario);
        viewHolder.tvEmail.setText(email);
        return convertView;
    }

    public RestauranteAdapter(Context context, ArrayList<Restaurante> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }



}
