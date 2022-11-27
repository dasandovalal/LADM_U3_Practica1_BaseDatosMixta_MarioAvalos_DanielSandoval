package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date
import java.sql.SQLData
import java.sql.Time
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrar.setOnClickListener {
            AlumnoInteresado(this).registrarAlumnoInteresado(
                txtNombre,txtEscuelaActual,txtTelefono,spinnerCarreraUNO,
                spinnerCarreraDOS,txtCorreo
            )
        }

        btnMostrar.setOnClickListener {
            val otraVentana = Intent(this,MainActivity2::class.java)
            startActivity(otraVentana)
        }
    }




}