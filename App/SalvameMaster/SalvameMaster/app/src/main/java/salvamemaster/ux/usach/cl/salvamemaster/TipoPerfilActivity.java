package salvamemaster.ux.usach.cl.salvamemaster;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TipoPerfilActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<String> tipoPerfil;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_perfil);

        mListView = (ListView) findViewById(R.id.tipo_perfil_list_view);
        if (savedInstanceState == null) {
            tipoPerfil = new ArrayList<>();
            tipoPerfil.add("¡Soy maestro!");
            tipoPerfil.add("¡Soy cliente!");
        } else {
            tipoPerfil = savedInstanceState.getStringArrayList("tipoPerfil");
        }

        mAdapter = new MyAdapter(this,android.R.layout.simple_list_item_1,tipoPerfil);
        mListView.setAdapter(mAdapter);

        //Crear evento de la lista
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String perfilSeleccionado = mAdapter.getItem(position);

                if(perfilSeleccionado.equals("¡Soy maestro!")){
                    Intent intent = new Intent(TipoPerfilActivity.this, RegistrarMaestroActivity.class);
                    startActivity(intent);
                }

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
