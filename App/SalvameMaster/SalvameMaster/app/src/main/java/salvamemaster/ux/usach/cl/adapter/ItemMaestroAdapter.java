package salvamemaster.ux.usach.cl.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
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
import salvamemaster.ux.usach.cl.salvamemaster.R;

/**
 * Created by Alejandro on 12-06-2017.
 */

public class ItemMaestroAdapter extends ArrayAdapter<TipoTrabajadorDTO> implements View.OnClickListener{

    private ArrayList<TipoTrabajadorDTO> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView descripcion;
        ImageView item_info;
    }

    public ItemMaestroAdapter(ArrayList<TipoTrabajadorDTO> data, Context context) {
        super(context, R.layout.activity_detalletiposervicio_cliente, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        TipoTrabajadorDTO dataModel = (TipoTrabajadorDTO) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TipoTrabajadorDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_detalletiposervicio_cliente, parent, false);
            viewHolder.descripcion = (TextView)convertView.findViewById(R.id.descripcion);
            viewHolder.item_info = (ImageView)convertView.findViewById(R.id.item_info);

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

        viewHolder.descripcion.setText(dataModel.getDescripcion());
        viewHolder.item_info.setOnClickListener(this);
        viewHolder.item_info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}