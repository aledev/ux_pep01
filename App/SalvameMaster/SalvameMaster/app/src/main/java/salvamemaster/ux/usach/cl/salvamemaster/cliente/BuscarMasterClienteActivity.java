package salvamemaster.ux.usach.cl.salvamemaster.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;
import salvamemaster.ux.usach.cl.adapter.ItemMaestroAdapter;
import salvamemaster.ux.usach.cl.entities.TipoTrabajadorDTO;
import salvamemaster.ux.usach.cl.salvamemaster.R;

public class BuscarMasterClienteActivity extends AppCompatActivity {
    ListView lstTipoServicio;
    ArrayList<TipoTrabajadorDTO> servicioList;
    private static ItemMaestroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarmaster_cliente);
        this.setTitle("Búsqueda de Maestro");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        lstTipoServicio = (ListView)findViewById(R.id.lstTipoServicio);

        servicioList = new ArrayList<>();
        servicioList.add(new TipoTrabajadorDTO((short)1, "Gasfiter"));
        servicioList.add(new TipoTrabajadorDTO((short)2, "Cerrajero"));
        servicioList.add(new TipoTrabajadorDTO((short)3, "Mecánico"));
        servicioList.add(new TipoTrabajadorDTO((short)4, "Pintor"));
        servicioList.add(new TipoTrabajadorDTO((short)5, "Carpintero"));
        servicioList.add(new TipoTrabajadorDTO((short)6, "Electricista"));
        servicioList.add(new TipoTrabajadorDTO((short)7, "Topógrafo"));
        servicioList.add(new TipoTrabajadorDTO((short)8, "Albañil"));

        adapter = new ItemMaestroAdapter(servicioList, getApplicationContext());
        lstTipoServicio.setAdapter(adapter);
        lstTipoServicio.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                TipoTrabajadorDTO item = servicioList.get(position);
                Gson gson = new Gson();
                String jsonData = gson.toJson(item);
                Intent intent = new Intent(BuscarMasterClienteActivity.this, BuscarMasterListaClienteActivity.class);
                intent.putExtra("JsonData", jsonData);
                startActivity(intent);
            }
        });
    }
}
