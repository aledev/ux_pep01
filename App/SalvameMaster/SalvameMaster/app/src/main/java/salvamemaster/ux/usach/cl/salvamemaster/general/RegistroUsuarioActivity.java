package salvamemaster.ux.usach.cl.salvamemaster.general;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.content.pm.PackageManager;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.FileProvider;
import salvamemaster.ux.usach.cl.entities.RecursoDTO;
import android.graphics.BitmapFactory;
public class RegistroUsuarioActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback   {

    ArrayList<String> tipoPerfil;
    MyAdapter mAdapter;
    Spinner spTipoPerfil;
    LinearLayout lnCliente;
    LinearLayout lnMaestro;
    private Button btnTomarFoto;
     ImageView imgMostrarFoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_CODE_CAMERA=1;
    static final int REQUEST_CODE_DISCO=1;
    static final int REQUEST_TAKE_PHOTO = 1;
    public RecursoDTO recursoMultimedia = new RecursoDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        this.setTitle("Registrarse");

        //Gestionar permisos para camara y almacen de fotos
        int permissionCheckCamara = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA);
        int permissionCheckStorage = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if((permissionCheckCamara == PackageManager.PERMISSION_GRANTED) && (permissionCheckStorage==PackageManager.PERMISSION_GRANTED)){
            System.out.println("Camara y almacen con permisos");
        }else{
            System.out.println("Proceso para dar permiso a la camara y almacen");
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

        lnCliente = (LinearLayout) findViewById(R.id.linear_cliente);
        lnMaestro = (LinearLayout) findViewById(R.id.linear_maestro);

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

        imgMostrarFoto = (ImageView) this.findViewById(R.id.imgMostrarFoto);
        btnTomarFoto = (Button) this.findViewById(R.id.btnTomarFoto);

        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent takePictureIntent = new Intent();
                    takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Ensure that there's a camera activity to handle the intent
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {

                            recursoMultimedia.setFoto(photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
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
        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            System.out.println("Entro a mostrar foto");
            //Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            Bitmap imageBitMap = BitmapFactory.decodeFile(recursoMultimedia.getFoto().getPath());
            imgMostrarFoto.setImageBitmap(imageBitMap);
        }else{
            System.out.println("No entro a mostrar foto");
        }
    }

    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso aceptado
                    //abrirCamara();
                    System.out.println("Es posible utilizar la camara y disco");
                }else{
                    // Permiso denegado
                    Toast.makeText(getBaseContext(), "No se ha aceptado el permiso", Toast.LENGTH_SHORT).show();
                }
                return;
            // Gestionar el resto de permisos
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

}
