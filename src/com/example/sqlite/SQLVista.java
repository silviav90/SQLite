package com.example.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SQLVista extends Activity{
	
	TextView texto;
	
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.vista);
			
			texto=(TextView)findViewById(R.id.tvTexto);
			
			Telefonos info = new Telefonos(this);
			try {
				info.abrir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String datos = info.recibir();
			info.cerrar();
			texto.setText(datos);
}
}