package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import salvamemaster.ux.usach.cl.salvamemaster.R;

public class RegistrarMaestroActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> tipoTrabajo;
    ArrayList<String> anioExperiencia;
    MyAdapter mAdapter;
    Spinner spTipoTrabajo;
    Spinner spAnioExperiencia;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_maestro);

        spTipoTrabajo = (Spinner) findViewById(R.id.tipo_trabajo_spinner);
        spAnioExperiencia = (Spinner) findViewById(R.id.anio_experiencia_spinner);
        btnAceptar = (Button) findViewById(R.id.btnIngresarMaestro);

        if (savedInstanceState == null) {
            tipoTrabajo = new ArrayList<>();
            tipoTrabajo.add("Carpintería");
            tipoTrabajo.add("Gafitería");
            tipoTrabajo.add("Cerrajería");
        } else {
            tipoTrabajo = savedInstanceState.getStringArrayList("tipoTrabajo");
        }

        mAdapter = new MyAdapter(this,android.R.layout.simple_list_item_1,tipoTrabajo);
        spTipoTrabajo.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            anioExperiencia = new ArrayList<>();
            for(int i=1; i < 10;i++){
                anioExperiencia.add(i+" año");
            }
        } else {
            anioExperiencia = savedInstanceState.getStringArrayList("anioExperiencia");
        }

        mAdapter = new MyAdapter(this,android.R.layout.simple_list_item_1,anioExperiencia);
        spAnioExperiencia.setAdapter(mAdapter);

        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();
                CharSequence text = "¡Ha sido registrado con exito!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(RegistrarMaestroActivity.this, PanelAdminMasterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("tipoTrabajo", tipoTrabajo);
        outState.putStringArrayList("anioExperiencia", anioExperiencia);
    }

    private class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }
    }

    @Override
    public void onClick(View v) {

    }
}