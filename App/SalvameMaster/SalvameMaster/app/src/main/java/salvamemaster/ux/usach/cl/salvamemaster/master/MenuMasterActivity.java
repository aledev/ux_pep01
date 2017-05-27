package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import salvamemaster.ux.usach.cl.salvamemaster.R;

public class MenuMasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_master);
        this.setTitle("Menú del Master");
    }
}
