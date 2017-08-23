package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Telefonos {

	public static final String ID_FILA="id";
	public static final String ID_PERSONA="nombre_persona";
	public static final String ID_TELEFONO="nombre_telefono";
	
	private static final String N_BD ="Telefonos";
	private static final String N_TABLA = "Tabla_Telefonos";
	private static final int VERSION_BD =1;
	
	private BDHelper nHelper;
	private final Context nContexto;
	private SQLiteDatabase nBD;
	
	
	private static class BDHelper extends SQLiteOpenHelper{

		public BDHelper(Context context) {
			super(context, N_BD, null, VERSION_BD);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE" + N_TABLA + "("+
			ID_FILA + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
			ID_PERSONA + "TEXT NOT NULL," +
			ID_TELEFONO + "TEXT NOT NULL;" 
					
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + N_TABLA);
			onCreate(db);
		}
		
	}
	
	public Telefonos (Context c){
		nContexto = c;
	}
	public Telefonos abrir() throws Exception{
		nHelper = new BDHelper(nContexto);
		nBD = nHelper.getWritableDatabase();
		
		
		return this;
	}
	public void cerrar() {
		// TODO Auto-generated method stub
		nHelper.close();
	}
	public long crearEntrada(String nom, String tel) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(ID_PERSONA, nom);
		cv.put(ID_TELEFONO, tel);
		return nBD.insert(N_TABLA, null, cv);
	}
	public String recibir() {
		// TODO Auto-generated method stub
		String[]columnas = new String[]{ID_FILA, ID_PERSONA,ID_TELEFONO};
		Cursor c =nBD.query(N_TABLA, columnas, null, null, null, null, null);
		String resultado="";
		
		int iFila =c.getColumnIndex(ID_FILA);
		int iNombre=c.getColumnIndex(ID_PERSONA);
		int iTelefono=c.getColumnIndex(ID_TELEFONO);
		
		for(c.moveToFirst(); !c.moveToLast(); c.moveToNext()){
			resultado = resultado +c.getString(iFila)+""+c.getString(iNombre)+""+c.getString(iTelefono)+"\n";
				
		}
		
		return resultado;
	}
	public String getN(Long lb) {
		// TODO Auto-generated method stub
		String[]columnas = new String[]{ID_FILA, ID_PERSONA,ID_TELEFONO};
		Cursor c =nBD.query(N_TABLA, columnas,ID_FILA + "=" + lb, null, null, null,null);
		if(c !=null){
			c.moveToFirst();
			String nb = c.getString(1);
			return nb;
		}
		
		return null;
	}
	public String getT(Long lb) {
		// TODO Auto-generated method stub
		String[]columnas = new String[]{ID_FILA, ID_PERSONA,ID_TELEFONO};
		Cursor c =nBD.query(N_TABLA, columnas,ID_FILA + "=" + lb, null, null, null,null);
		if(c !=null){
			c.moveToFirst();
			String tb = c.getString(2);
			return tb;
		}
		
		return null;
	}
	public void editar(long eFilal, String eNom, String eTel) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvEditar = new ContentValues();
		cvEditar.put(ID_PERSONA,eNom);
		cvEditar.put(ID_TELEFONO,eTel);
		nBD.update(N_TABLA, cvEditar, ID_FILA +"=" + eFilal,null);
	}
	public void borrar(long elFilal) throws SQLException{
		// TODO Auto-generated method stub
		nBD.delete(N_TABLA, ID_FILA +"=" +elFilal, null);
	}
	
}














