package com.tienda.a_shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.tienda.a_shop.domain.ItemCarrito;
import com.tienda.a_shop.domain.ItemGasto;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class CarritoDeVenta extends Activity {

    private static final int REQUEST_ADD = 1;
    private ArrayList<ItemGasto> items;
    private ListView listaProductos;
    private Button ok;
    private Button editar;
    private Button cancelar;
    private int idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_de_venta);

        items = new ArrayList<ItemGasto>();
        String jsonItems = getIntent().getExtras().getParcelable("items");
        idProducto = getIntent().getIntExtra("idProducto", 0);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ItemGasto>>(){}.getType();
        items = gson.fromJson(jsonItems, type);

        listaProductos = (ListView)findViewById(R.id.carrito_lista);
        registerForContextMenu(listaProductos);
        ArrayAdapter<ItemGasto> adapter = new ArrayAdapter<ItemGasto>(this, android.R.layout.simple_spinner_dropdown_item, items);
        listaProductos.setAdapter(adapter);



        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();

            }

        });

        cancelar = (Button)findViewById(R.id.carrito_cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok = (Button)findViewById(R.id.carrito_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( CarritoDeVenta.this, AgregarItemGasto.class );
                startActivityForResult(i, REQUEST_ADD);
            }
        });

        calcularPrecioTotal();
    }

    private void calcularPrecioTotal()
    {
        TextView total = (TextView)findViewById(R.id.carrito_Total);
        int tot = 0;
        BDProductos bd = new BDProductos(getApplicationContext());
        for( ItemGasto  i: items )
        {
            /**tot += (bd.buscarProducto(
                    i.getProducto() )
                    .getPrecioVenta())*
                    (i.getCantidad());**/
        }
        total.setText("Total: $ " + tot);
    }

    public String eliminarItemCarrito(int pos)
    {
        String nombre = items.get(pos).getNombre();
        items.remove(pos);
        ArrayAdapter<ItemGasto> adapter = new ArrayAdapter<ItemGasto>(this, android.R.layout.simple_spinner_dropdown_item, items);
        listaProductos.setAdapter(adapter);
        return nombre;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.carrito_de_venta, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.action_editar:

                Toast.makeText(getApplicationContext(),"editando el item " + info.id, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_eliminar:
                String nombre = eliminarItemCarrito(info.position);
                Toast.makeText(getApplicationContext(),"Eliminado el item " + nombre + ".", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.carrito_de_venta, menu);
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
