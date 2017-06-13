package salvamemaster.ux.usach.cl.salvamemaster.master;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import salvamemaster.ux.usach.cl.salvamemaster.R;
import salvamemaster.ux.usach.cl.salvamemaster.login.LoginActivity;
import salvamemaster.ux.usach.cl.salvamemaster.master.fragmento.FragmentEditarDatosMaestro;
import salvamemaster.ux.usach.cl.salvamemaster.master.fragmento.FragmentEstadoActual;
import salvamemaster.ux.usach.cl.salvamemaster.master.fragmento.FragmentVerDatosMaestro;

public class MainMenuMasterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_master);
        this.setTitle("Menú del Master");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu_master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragmento = null;

        int id = item.getItemId();

        if(id == R.id.men_cerrar_sesion){
            Intent intent = new Intent(MainMenuMasterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.men_ver_datos){
            fragmento = new FragmentVerDatosMaestro();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_menu_master,fragmento).commit();
        }else if(id == R.id.men_ver_estado){
            fragmento = new FragmentEstadoActual();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_menu_master,fragmento).commit();
        }else if(id == R.id.men_editar_datos){
            fragmento = new FragmentEditarDatosMaestro();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_menu_master,fragmento).commit();
        }else if(id==R.id.men_editar_estado) {
            fragmento = new FragmentEstadoActual();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_menu_master,fragmento).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
