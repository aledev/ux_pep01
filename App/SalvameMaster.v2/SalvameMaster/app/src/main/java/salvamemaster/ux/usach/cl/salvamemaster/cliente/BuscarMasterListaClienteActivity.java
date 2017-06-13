package salvamemaster.ux.usach.cl.salvamemaster.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import retrofit2.converter.gson.GsonConverterFactory;
import salvamemaster.ux.usach.cl.adapter.ItemMaestroAdapter;
import salvamemaster.ux.usach.cl.adapter.ItemMaestroDetalleAdapter;
import salvamemaster.ux.usach.cl.entities.PersonaDTO;
import salvamemaster.ux.usach.cl.entities.TipoTrabajadorDTO;
import salvamemaster.ux.usach.cl.entities.TrabajadorDTO;
import salvamemaster.ux.usach.cl.salvamemaster.R;

public class BuscarMasterListaClienteActivity extends AppCompatActivity {
    private TipoTrabajadorDTO itemTrabajador;
    ListView lstMaestros;
    ArrayList<TrabajadorDTO> trabajadorList;
    private static ItemMaestroDetalleAdapter adapter;
    final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarmaster_lista_cliente);
        // Rescatamos el valor del item seleccionado en la pantalla anterior
        String jsonData = getIntent().getExtras().getString("JsonData");
        itemTrabajador = gson.fromJson(jsonData, TipoTrabajadorDTO.class);
        //int idTipoTrabajador = getIntent().getExtras().getInt("Id");
        //String descripcionTipoTrabajador = getIntent().getExtras().getString("Descripcion");
        //itemTrabajador = new TipoTrabajadorDTO((short)idTipoTrabajador, descripcionTipoTrabajador);

        this.setTitle("Lista de " + itemTrabajador.getDescripcion());

        lstMaestros = (ListView)findViewById(R.id.lstMaestros);
        trabajadorList = new ArrayList<>();

        Random rdm = new Random();
        int totalList = rdm.nextInt(21) + 1;

        for(int x = 0; x < totalList; x++) {
            PersonaDTO personaItem = new PersonaDTO();
            personaItem.setId((long)x + 1);
            personaItem.setFechaNacimiento(new Date());
            personaItem.setFechaRegistro(new Date());
            personaItem.setFoto("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");
            personaItem.setFechaRegistro(new Date());
            personaItem.setMail("xxxyyyzzz@gmail.com");
            personaItem.setIdTipoPersona(2);
            personaItem.setTipoSexo("M");
            personaItem.setNombre("Trabajador");
            personaItem.setNombreCompleto("Trabajador Prueba " + String.valueOf(x + 1));

            TrabajadorDTO item = new TrabajadorDTO(
                    (long) x + 1,
                    (long) x + 1,
                    personaItem,
                    this.itemTrabajador.getId(),
                    this.itemTrabajador,
                    new Date(),
                    (double) 10000 * (x + 1),
                    (double) 1000 * (x + 1),
                    "+56912345678",
                    "Av. Lopez #2230",
                    (short)1,
                    "-33.4402977",
                    "-70.6577421",
                    "Maestro con más de " + String.valueOf(x + 2) + " años de Experiencia"
            );

            trabajadorList.add(item);
        }

        adapter = new ItemMaestroDetalleAdapter(trabajadorList, getApplicationContext());
        lstMaestros.setAdapter(adapter);
        lstMaestros.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                TrabajadorDTO item = trabajadorList.get(position);
                String jsonItemData = gson.toJson(item);
                Intent intent = new Intent(BuscarMasterListaClienteActivity.this, BuscarMasterListaItemDetalleCliente.class);
                intent.putExtra("JsonData", jsonItemData);
                startActivity(intent);
            }
        });
    }
}
