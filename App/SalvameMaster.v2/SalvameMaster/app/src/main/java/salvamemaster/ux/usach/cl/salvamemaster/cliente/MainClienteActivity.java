package salvamemaster.ux.usach.cl.salvamemaster.cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import salvamemaster.ux.usach.cl.salvamemaster.R;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainClienteActivity extends AppCompatActivity {

    Button btnComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        this.setTitle("Cliente");

        btnComenzar = (Button) findViewById(R.id.btnComenzarCliente);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainClienteActivity.this, MainMenuClienteActivity.class);
                startActivity(intent);
            }
        });

    }
}
