package salvamemaster.ux.usach.cl.salvamemaster.general;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import salvamemaster.ux.usach.cl.salvamemaster.R;


public class RegistroUsuarioActivity extends AppCompatActivity {

    ArrayList<String> tipoPerfil;
    MyAdapter mAdapter;
    Spinner spTipoPerfil;

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
