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
import android.widget.ImageView;
import android.widget.ListView;

import com.tienda.a_shop.domain.ItemCarrito;
import com.tienda.a_shop.domain.Producto;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Lorena on 10/10/2014.
 */
public class ListActivity extends Activity
{
    public static final int REQUEST_TEXT = 0;
    public static final int REQUEST_ADD = 1;
    private ListView listaProductos;
    private ArrayList<Producto> productos;
    private ImageView agregarProducto;
    private ImageView carrito;
    private BDProductos bdProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //productos = new ArrayList<Producto>();
        final Producto p1 = new Producto(0,"Rosquillas", 0);
        final Producto p2= new Producto(0,"Choclitos", 1);
        final Producto p3 = new Producto(0,"Maduritos", 2);
        Producto p4 = new Producto(0,"Picada", 3);
        Producto p5 = new Producto(0,"Boliqueso",4);
        Producto p6 = new Producto(0,"Gomita", 5);
        Producto p7 = new Producto(0,"Mu", 6);
        Producto p8= new Producto(0,"Choclitos", 7);
        Producto p9 = new Producto(0,"Maduritos", 8);
        Producto p10 = new Producto(0,"Picada", 9);
        Producto p11 = new Producto(0,"Boliqueso", 10);
        Producto p12 = new Producto(0,"Gomita", 11);

        bdProductos = new BDProductos(getApplicationContext());
        bdProductos.guardarProducto(p1.getNombre(),p1.getPosicion());
        bdProductos.guardarProducto(p2.getNombre(),p2.getPosicion());
        bdProductos.guardarProducto(p3.getNombre(),p3.getPosicion());
     /**   bdProductos.guardarProducto(p4.getNombre(),p4.getCantidad(),p4.getCosto(),p4.getPrecioVenta());
        bdProductos.guardarProducto(p5.getNombre(),p5.getCantidad(),p5.getCosto(),p5.getPrecioVenta());
        bdProductos.guardarProducto(p6.getNombre(),p6.getCantidad(),p6.getCosto(),p6.getPrecioVenta());
        bdProductos.guardarProducto(p7.getNombre(),p7.getCantidad(),p7.getCosto(),p7.getPrecioVenta());
        bdProductos.guardarProducto(p8.getNombre(),p8.getCantidad(),p8.getCosto(),p8.getPrecioVenta());
        bdProductos.guardarProducto(p9.getNombre(),p9.getCantidad(),p9.getCosto(),p9.getPrecioVenta());
        bdProductos.guardarProducto(p10.getNombre(),p10.getCantidad(),p10.getCosto(),p10.getPrecioVenta());
        bdProductos.guardarProducto(p11.getNombre(),p11.getCantidad(),p11.getCosto(),p11.getPrecioVenta());
        bdProductos.guardarProducto(p12.getNombre(),p12.getCantidad(),p12.getCosto(),p12.getPrecioVenta());*/


        productos=bdProductos.listaProductos();
  /**      productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
        productos.add(p8);
        productos.add(p9);
        productos.add(p10);
        productos.add(p11);
        productos.add(p12);*/

        listaProductos = (ListView)findViewById(R.id.listaProductos);
        registerForContextMenu(listaProductos);
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_spinner_dropdown_item, productos);
        listaProductos.setAdapter(adapter);

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Intent i = new Intent( ListActivity.this, CarritoDeVenta.class );
                String itemsJson = new JSONArray(productos.get(position).getItems()).toString();
                i.putExtra("items", itemsJson);
                i.putExtra("idProducto", productos.get(position).getId());
                startActivity(i);
            }
        });

        //agregar producto

        agregarProducto = (ImageView)findViewById(R.id.agregarProducto);
        agregarProducto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent( ListActivity.this, AgregarProducto.class );
                startActivityForResult(i, REQUEST_ADD);
            }
        });

        //ver carrito
        carrito = (ImageView)findViewById(R.id.lista_productos_carrito);
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( ListActivity.this, CarritoDeVenta.class );
                Bundle b = new Bundle();
                ItemCarrito a = new ItemCarrito(p1.getNombre());
                ItemCarrito bb = new ItemCarrito(p2.getNombre(), 3);
                ItemCarrito c = new ItemCarrito(p3.getNombre(), 6);

                b.putParcelable("uno",a);
                b.putParcelable("dos",bb);
                b.putParcelable("tres",c);

                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.agregar_producto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.action_editar_producto:
                Intent i = new Intent(ListActivity.this, EditarProducto.class);
                Producto p = productos.get(info.position);
                i.putExtra("nombre", p.getNombre() );
                i.putExtra("posicion", p.getPosicion()+"");

                startActivityForResult(i, REQUEST_TEXT);
                return true;
            case R.id.action_eliminar_producto:
                eliminarProducto(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agregar_producto, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == REQUEST_TEXT ){
            if ( resultCode == Activity.RESULT_OK ){
                String nombre = data.getExtras().getString("nombre");
                String nombreN = data.getExtras().getString("nombreN").toString();
                int cantidad = Integer.parseInt(data.getExtras().getString("cantidad").toString());
                int costo = Integer.parseInt(data.getExtras().getString("costo").toString());
                int precio_venta = Integer.parseInt(data.getExtras().getString("precio_venta").toString());
                int costoUnidad = Integer.parseInt(data.getExtras().getString("costo_unidad").toString());

                editarProducto(nombreN, nombre, cantidad, costo, costoUnidad, precio_venta);
            }
        }
        if(requestCode == REQUEST_ADD)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                actualizarLista();
            }
        }
    }

    private void editarProducto(String nombreN, String nombre, int cantidad, int costo, int costo_unidad, int precio_venta) {
        bdProductos.editarProducto(nombreN, nombre, cantidad, costo, costo_unidad, precio_venta);
        actualizarLista();
    }

    public void eliminarProducto(int posicion)
    {
        String nomP =productos.get(posicion).getNombre();
        bdProductos.eliminarProducto(nomP);
        actualizarLista();
    }

    public void actualizarLista()
    {
        productos =  bdProductos.listaProductos();
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_spinner_dropdown_item, productos);
        listaProductos.setAdapter(adapter);
    }



}
