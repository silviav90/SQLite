package com.example.sqlite;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteActivity extends Activity implements OnClickListener{
	
	EditText nombre,telefono, ebuscar;
	
	Button insertar,ver, buscar,editar,eliminar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		nombre=(EditText)findViewById(R.id.etNombre);
		telefono=(EditText)findViewById(R.id.etTelefono);
		insertar=(Button)findViewById(R.id.btInsertar);
		
		ebuscar=(EditText)findViewById(R.id.etBuscar);
		buscar=(Button)findViewById(R.id.btBuscar);
		editar=(Button)findViewById(R.id.btEditar);
		eliminar=(Button)findViewById(R.id.btEliminar);
		
		ver=(Button)findViewById(R.id.btVer);
		
		insertar.setOnClickListener(this);
		ver.setOnClickListener(this);
		buscar.setOnClickListener(this);
		editar.setOnClickListener(this);
		eliminar.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.btInsertar:
			
			boolean funciona =true;
			try{
			String nom = nombre.getText().toString();
			String tel = telefono.getText().toString();
			
			nombre.setText("");
			telefono.setText("");
			
			Telefonos entrada =new Telefonos (SQLiteActivity.this);
			entrada.abrir();
			entrada.crearEntrada(nom, tel);
			entrada.cerrar();
			
			}catch (Exception e){
				funciona=false;
				String error=e.toString();
				Dialog d =new Dialog(this);
				d.setTitle("No funciona");
				TextView tv= new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
				
			}finally{
			if (funciona){
			Dialog d = new Dialog(this);
			d.setTitle("Funciona");
			TextView tv = new TextView(this);
			tv.setText("Funciona!!!");
			d.setContentView(tv);
			d.show();
			}
			
			}
			break;
			
		case R.id.btVer:
		
			Intent i = new Intent("com.example.sqlite.SQLVista");
			startActivity(i);
			break;
		case R.id.btBuscar:
			String b = ebuscar.getText().toString();
			Long lb = Long.parseLong(b);
			
			Telefonos tel=new Telefonos(this);
			try{
			tel.abrir();
			}catch(Exception e){
				e.printStackTrace();
			}
			String bN = tel.getN(lb);
			String bT = tel.getT(lb);
			
			tel.cerrar();
			
			nombre.setText(bN);
			telefono.setText(bT);
			
			break;
		
		case R.id.btEditar:
			try {
				
			
			String eNom=nombre.getText().toString();
			String eTel=telefono.getText().toString();
			String eFila=ebuscar.getText().toString();
			long eFilal= Long.parseLong(eFila);
			
			Telefonos editar =new Telefonos(this);
			editar.abrir();
			editar.editar(eFilal, eNom, eTel);
			editar.cerrar();
			
			}catch (Exception e){
				
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funciono");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.btEliminar:
			try{
			String elFila = ebuscar.getText().toString();
			long elFilal = Long.parseLong(elFila);
			
			Telefonos borrar = new Telefonos(this);
			borrar.abrir();
			borrar.borrar(elFilal);
			borrar.cerrar();
}catch (Exception e){
				
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("No funciono");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		
		}
	}

}
















