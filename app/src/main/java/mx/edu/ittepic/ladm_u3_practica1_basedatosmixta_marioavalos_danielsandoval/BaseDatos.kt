package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("CREATE TABLE ALUMNO_INTERESADO(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE VARCHAR(200) NOT NULL, ESCUELA_ACTUAL VARCHAR(200) NOT NULL, " +
                "TELEFONO CHAR(10) UNIQUE NOT NULL, CARRERA_UNO VARCHAR(24) NOT NULL, " +
                "CARRERA_DOS VARCHAR(24) NOT NULL, CORREO VARCHAR(200) UNIQUE NOT NULL, " +
                "FECHA DATE DEFAULT CURRENT_DATE, HORA TIME DEFAULT CURRENT_TIME)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}