package salvamemaster.ux.usach.cl.salvamemaster.general;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import salvamemaster.ux.usach.cl.salvamemaster.R;


public class RegistroUsuarioActivity extends AppCompatActivity {

    ArrayList<String> tipoPerfil;
    MyAdapter mAdapter;
    Spinner spTipoPerfil;
    LinearLayout lnCliente;
    LinearLayout lnMaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        this.setTitle("Registrarse");

        spTipoPerfil = (Spinner) findViewById(R.id.tipo_perfil_spinner);

        if (savedInstanceState == null) {
            tipoPerfil = new ArrayList<>();
            tipoPerfil.add("Cliente");
            tipoPerfil.add("Maestro");
        } else {
            tipoPerfil = savedInstanceState.getStringArrayList("tipoPerfil");
        }

        mAdapter = new MyAdapter(this,android.R.layout.simple_list_item_1,tipoPerfil);
        spTipoPerfil.setAdapter(mAdapter);

        lnCliente = (LinearLayout) findViewById(R.id.linear_cliente);
        lnMaestro = (LinearLayout) findViewById(R.id.linear_maestro);

        //Evento de selección
        spTipoPerfil.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(spTipoPerfil.getSelectedItem().toString().equals("Cliente")){
                    lnCliente.setVisibility(View.VISIBLE);
                    lnMaestro.setVisibility(View.INVISIBLE);
                }else{
                    lnCliente.setVisibility(View.INVISIBLE);
                    lnMaestro.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("tipoPerfil", tipoPerfil);
    }

    private class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }
    }
}
