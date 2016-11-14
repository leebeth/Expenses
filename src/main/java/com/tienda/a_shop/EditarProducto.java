package com.tienda.a_shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Lorena on 18/10/2014.
 */
public class EditarProducto extends Activity
{
    private EditText txtNombre;
    private EditText txtPosicion;
    private Button butAceptar;
    private Button butCancelar;

    private BDProductos dbProductos;

    private String nombre;
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_agregar_producto);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtPosicion = (EditText)findViewById(R.id.txtPosicion);

        butAceptar = (Button)findViewById(R.id.butAceptar);
        butCancelar=(Button)findViewById(R.id.butCancelar);

        nombre = getIntent().getExtras().getString("nombre");
        txtNombre.setText(getIntent().getExtras().getString("nombre"));
        txtPosicion.setText(getIntent().getExtras().getString("posicion"));

           butAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent( EditarProducto.this, EditarProducto.class );
                i.putExtra("nombre", nombre );
                i.putExtra("nombreN", txtNombre.getText().toString() );
                i.putExtra("posicion", txtPosicion.getText().toString() );
                setResult( Activity.RESULT_OK, i );
                EditarProducto.this.finish();
            }
        });

        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
