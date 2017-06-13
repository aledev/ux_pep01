package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import salvamemaster.ux.usach.cl.salvamemaster.R;


public class EstadoActualMasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_actual_master);
        this.setTitle("Estado Actual del Maestro");
    }

}
