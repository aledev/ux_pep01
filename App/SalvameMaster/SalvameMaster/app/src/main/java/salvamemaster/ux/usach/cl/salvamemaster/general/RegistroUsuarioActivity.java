package salvamemaster.ux.usach.cl.salvamemaster.general;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import android.os.Environment;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import salvamemaster.ux.usach.cl.salvamemaster.R;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import salvamemaster.ux.usach.cl.entities.RecursoDTO;
import android.graphics.BitmapFactory;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegistroUsuarioActivity extends AppCompatActivity
        implements OnRequestPermissionsResultCallback,GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    ArrayList<String> tipoPerfil;
    MyAdapter mAdapter;
    Spinner spTipoPerfil;
    CardView lnCliente;
    CardView lnMaestro;
    Button btnTomarFoto;
    ImageView imgMostrarFoto;
    Button btnTomarFotoMaestro;
    ImageView imgMostrarFotoMaestro;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_CODE_CAMERA=1;
    static final int REQUEST_TAKE_PHOTO = 1;
    public RecursoDTO recursoMultimedia = new RecursoDTO();
    File imagenTemporal=null;
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        this.setTitle("Registrarse");

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        //Gestionar permisos para camara y almacen de fotos
        int permissionCheckCamara = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA);
        int permissionCheckStorage = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if((permissionCheckCamara == PackageManager.PERMISSION_GRANTED) && (permissionCheckStorage==PackageManager.PERMISSION_GRANTED)){
            Log.i("RegistroUsuarioActivity","Camara y almacen con permisos");
        }else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(getBaseContext(), "El permiso es necesario para utilizar la cámara.", Toast.LENGTH_LONG).show();
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getBaseContext(), "El permiso es necesario para alamacenar la fotografía.", Toast.LENGTH_LONG).show();
            }
            //Entregar el permiso
            ActivityCompat.requestPermissions(RegistroUsuarioActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CAMERA);

        }
        //Fin de gestión de permisos de camara y almacen

        spTipoPerfil = (Spinner) findViewById(R.id.tipo_perfil_spinner);

        if (savedInstanceState == null) {
            tipoPerfil = new ArrayList<>();
            tipoPerfil.add("Cliente");
            tipoPerfil.add("Maestro");
        } else {
            tipoPerfil = savedInstanceState.getStringArrayList("tipoPerfil");
        }

        mAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1, tipoPerfil);
        spTipoPerfil.setAdapter(mAdapter);

        lnCliente = (CardView) findViewById(R.id.card_view_usuario);
        lnMaestro = (CardView) findViewById(R.id.card_view_maestro);

        //Evento de selección
        spTipoPerfil.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spTipoPerfil.getSelectedItem().toString().equals("Cliente")) {
                    lnCliente.setVisibility(View.VISIBLE);
                    lnMaestro.setVisibility(View.INVISIBLE);
                } else {
                    lnCliente.setVisibility(View.INVISIBLE);
                    lnMaestro.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        imgMostrarFoto = (CircleImageView) this.findViewById(R.id.imgMostrarFoto);

        btnTomarFoto = (Button) this.findViewById(R.id.btnTomarFoto);

        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent takePictureIntent = new Intent();
                    takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        File archivoFoto=null;
                        try {
                            archivoFoto = crearFoto();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if (archivoFoto != null) {

                            recursoMultimedia.setFoto(archivoFoto);
                            recursoMultimedia.setPerfil("Cliente");
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(archivoFoto));
                            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        }
                    }

            }

        });

        imgMostrarFotoMaestro = (CircleImageView) this.findViewById(R.id.imgMostrarFotoMaestro);

        btnTomarFotoMaestro = (Button) this.findViewById(R.id.btnTomarFotoMaestro);

        btnTomarFotoMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent takePictureIntent = new Intent();
                takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File archivoFoto=null;
                    try {
                        archivoFoto = crearFoto();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (archivoFoto != null) {

                        recursoMultimedia.setFoto(archivoFoto);
                        recursoMultimedia.setPerfil("Maestro");
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(archivoFoto));
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
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
        private MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try{

            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Log.i("RegistroUsuarioActivity","Es posible ver la fotografía.");
                Bitmap imageBitMap = BitmapFactory.decodeFile(recursoMultimedia.getFoto().getPath());

                if(recursoMultimedia.getPerfil().equals("Cliente")){
                    imgMostrarFoto.setImageBitmap(imageBitMap);
                }else{
                    imgMostrarFotoMaestro.setImageBitmap(imageBitMap);
                }

                imagenTemporal.deleteOnExit();
            }else{
                Log.w("RegistroUsuarioActivity","No fue posible visualizar la fotografía.");
            }

        }catch(Exception e){
            Log.e("RegistroUsuarioActivity",e.getMessage());
        }

    }

    private File crearFoto() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        imagenTemporal = File.createTempFile(imageFileName,".jpg", storageDir);

        return imagenTemporal;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("RegistroUsuarioActivity","Es posible utilizar la camara y disco");
                }else{
                    // Permiso denegado
                    Toast.makeText(getBaseContext(), "No se ha aceptado el permiso", Toast.LENGTH_SHORT).show();
                }
                return;
            case PETICION_PERMISO_LOCALIZACION:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i("RegistroUsuarioActivity","Permiso concedido");
                    @SuppressWarnings("MissingPermission")
                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                    updateUI(lastLocation);
                }else{
                    Log.e("RegistroUsuarioActivity","Permiso denegado");
                }
                return;
            // Gestionar el resto de permisos
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.e("RegistroUsuarioActivity", "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("RegistroUsuarioActivity","Conectado correctamente a Google Play Services");

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        }else{
              Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
              updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("RegistroUsuarioActivity", "Se ha interrumpido la conexión con Google Play Services");
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            Log.i("RegistroUsuarioActivity","Latitud "+loc.getLatitude());
            Log.i("RegistroUsuarioActivity","Longitud"+loc.getLongitude());
        } else {
            Log.w("RegistroUsuarioActivity","Latitud desconocida");
            Log.w("RegistroUsuarioActivity","Longitud desconocida");
        }
    }



}
