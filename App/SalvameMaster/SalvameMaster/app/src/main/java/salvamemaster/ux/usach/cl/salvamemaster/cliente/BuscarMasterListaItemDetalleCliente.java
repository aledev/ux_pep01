package salvamemaster.ux.usach.cl.salvamemaster.cliente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import salvamemaster.ux.usach.cl.entities.TipoTrabajadorDTO;
import salvamemaster.ux.usach.cl.entities.TrabajadorDTO;
import salvamemaster.ux.usach.cl.salvamemaster.R;

public class BuscarMasterListaItemDetalleCliente extends AppCompatActivity {
    final Gson gson = new Gson();
    ImageView imgFoto;
    TextView txtNombre;
    TextView txtDescripcion;
    TextView txtDireccion;
    TextView txtTelefono;
    TextView txtEmail;
    TextView txtPrecioVisita;
    TextView txtPrecioHora;
    Button btnLlamarMaestro;
    TrabajadorDTO itemTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarmaster_lista_cliente_list_content);
        this.setTitle("Detalle del Maestro");

        this.imgFoto = (ImageView) findViewById(R.id.fotoMaestroDetalle);
        this.txtNombre = (TextView) findViewById(R.id.nombreMaestroDetalle);
        this.txtDescripcion = (TextView) findViewById(R.id.descripcionMaestroDetalle);
        this.txtDireccion = (TextView) findViewById(R.id.direccionMaestroDetalle);
        this.txtTelefono = (TextView) findViewById(R.id.contactoTelefono);
        this.txtEmail = (TextView) findViewById(R.id.contactoEmail);
        this.txtPrecioVisita = (TextView) findViewById(R.id.precioVisitaDetalle);
        this.txtPrecioHora = (TextView) findViewById(R.id.precioHoraDetalle);
        this.btnLlamarMaestro = (Button) findViewById(R.id.btnLlamarMaestro);

        // Obtenemos los datos del Trabajador Seleccionado
        String jsonData = getIntent().getExtras().getString("JsonData");
        this.itemTrabajador = gson.fromJson(jsonData, TrabajadorDTO.class);

        this.txtNombre.setText(itemTrabajador.getPersona().getNombreCompleto());
        this.txtDescripcion.setText(itemTrabajador.getDescripcion());
        this.txtDireccion.setText(itemTrabajador.getDireccion());
        this.txtTelefono.setText("Teléfono: " + itemTrabajador.getTelefono());
        this.txtEmail.setText("Email: " + itemTrabajador.getPersona().getMail());
        this.txtPrecioVisita.setText("Precio Visita: $" + String.valueOf(itemTrabajador.getPrecioVisita()));
        this.txtPrecioHora.setText("Precio Hora: $" + String.valueOf(itemTrabajador.getPrecioHora()));

        if(itemTrabajador.getPersona().getFoto() != ""){
            byte[] decodedString = Base64.decode(itemTrabajador.getPersona().getFoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgFoto.setImageBitmap(decodedByte);
        }

        btnLlamarMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri telUri = Uri.fromParts("tel", itemTrabajador.getTelefono(), null);
                Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
                startActivity(intent);
            }
        });
    }
}
