package salvamemaster.ux.usach.cl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import salvamemaster.ux.usach.cl.entities.TipoTrabajadorDTO;
import salvamemaster.ux.usach.cl.entities.TrabajadorDTO;
import salvamemaster.ux.usach.cl.salvamemaster.R;

/**
 * Created by Alejandro on 12-06-2017.
 */

public class ItemMaestroDetalleAdapter extends ArrayAdapter<TrabajadorDTO> implements View.OnClickListener{

    private ArrayList<TrabajadorDTO> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView fotoMaestro;
        TextView nombreMaestro;
        TextView descripcionMaestro;
        TextView direccionMaestro;
        TextView precioVisita;
        TextView precioHora;
    }

    public ItemMaestroDetalleAdapter(ArrayList<TrabajadorDTO> data, Context context) {
        super(context, R.layout.content_listview_buscarmaster_layout, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        TrabajadorDTO dataModel = (TrabajadorDTO) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TrabajadorDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_listview_buscarmaster_layout, parent, false);

            viewHolder.fotoMaestro = (ImageView)convertView.findViewById(R.id.fotoMaestro);
            viewHolder.nombreMaestro = (TextView)convertView.findViewById(R.id.nombreMaestro);
            viewHolder.descripcionMaestro = (TextView)convertView.findViewById(R.id.descripcionMaestro);
            viewHolder.direccionMaestro = (TextView)convertView.findViewById(R.id.direccionMaestro);
            viewHolder.precioHora = (TextView)convertView.findViewById(R.id.precioHora);
            viewHolder.precioVisita = (TextView)convertView.findViewById(R.id.precioVisita);

            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ?
                R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.nombreMaestro.setText(dataModel.getPersona().getNombreCompleto());
        viewHolder.descripcionMaestro.setText(dataModel.getDescripcion());
        viewHolder.direccionMaestro.setText(dataModel.getDireccion());
        viewHolder.precioVisita.setText("Precio Visita: $" + String.valueOf(dataModel.getPrecioVisita()));
        viewHolder.precioHora.setText("Precio Hora: $" + String.valueOf(dataModel.getPrecioHora()));

        if(dataModel.getPersona() != null){
            if(dataModel.getPersona().getFoto() != ""){
                byte[] decodedString = Base64.decode(dataModel.getPersona().getFoto(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                viewHolder.fotoMaestro.setImageBitmap(decodedByte);
            }
        }
        else {
            viewHolder.fotoMaestro.setImageResource(R.mipmap.ic_worker_black_48dp);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}