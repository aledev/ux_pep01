package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import salvamemaster.ux.usach.cl.salvamemaster.R;

public class PanelAdminMasterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEstadoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_admin_master);

        btnEstadoActual = (Button) findViewById(R.id.btnEstadoActual);

        btnEstadoActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelAdminMasterActivity.this, EstadoActualMasterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
