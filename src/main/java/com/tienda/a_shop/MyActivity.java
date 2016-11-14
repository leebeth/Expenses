package com.tienda.a_shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


public class MyActivity extends Activity {

    public static final int REQUEST_TEXT = 0;
    private ImageView ventas;
    private ImageView productos;
    private ImageView promociones;
    private ImageView estadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ventas = (ImageView)findViewById(R.id.ventas);
        productos = (ImageView)findViewById(R.id.productos);
        promociones=(ImageView)findViewById(R.id.promociones);
        estadisticas = (ImageView)findViewById(R.id.estadisticas);

        ventas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent( MyActivity.this, HistorialVentasActivity.class );
                startActivity(i);
            }
        });

        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent( MyActivity.this, ListActivity.class );
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
