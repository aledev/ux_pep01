package salvamemaster.ux.usach.cl.salvamemaster.general;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import salvamemaster.ux.usach.cl.salvamemaster.R;

public class RegistroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        this.setTitle("Registrarse");
    }
}
