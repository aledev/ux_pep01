package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import salvamemaster.ux.usach.cl.salvamemaster.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import salvamemaster.ux.usach.cl.salvamemaster.R;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class MainMasterActivity extends AppCompatActivity {

    Button btnComenzar;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_master);
        this.setTitle("Master");

        btnComenzar = (Button) findViewById(R.id.btnComenzarMaster);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMasterActivity.this, MainMenuMasterActivity.class);
                startActivity(intent);
            }
        });

    }
}
