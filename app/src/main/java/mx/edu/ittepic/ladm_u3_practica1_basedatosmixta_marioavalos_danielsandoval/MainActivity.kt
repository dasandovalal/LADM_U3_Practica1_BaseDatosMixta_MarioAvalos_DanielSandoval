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
            if (validarCampos()) {
                /*val sePudoRegistrar =
                    AlumnoInteresado(this).registrarAlumnoInteresado(
                        txtNombre, txtEscuelaActual, txtTelefono, spinnerCarreraUNO,
                        spinnerCarreraDOS, txtCorreo
                    )
                if (sePudoRegistrar){
                    limpiarCampos()*/
                    if (AlumnoInteresado(this).isInternetAvailable()){
                        AlertDialog.Builder(this)
                            .setTitle("RESPALADAR")
                            .setMessage("¿DESEAS GUARDAR EN LA NUBE?")
                            .setPositiveButton("OK"){d,i->
                                registrarLocalmente()
                                AlumnoInteresado(this).guardarEnLaNube()
                            }
                            .setNegativeButton("NO, EN MEMORIA"){d,i->
                                registrarLocalmente()
                                Toast.makeText(this,"REGISTRO GUARDADO LOCALMENTE", Toast.LENGTH_SHORT).show()
                            }
                            .setNeutralButton("CANCELAR"){d,i->
                                Toast.makeText(this,"NO SE GUARDO EL REGISTRO EN NINGUN LADO",Toast.LENGTH_SHORT).show()
                            }
                            .show()
                    }else{
                        AlertDialog.Builder(this)
                            .setTitle("VERIFICAR CONEXION")
                            .setMessage("NO HAY INTERNET. ¿DESEAS GUARDAR EN MEMORIA?")
                            .setPositiveButton("OK"){d,i->
                                registrarLocalmente()
                                Toast.makeText(this,"REGISTRO GUARDADO LOCALMENTE", Toast.LENGTH_SHORT).show()
                            }
                            .setNeutralButton("CANCELAR"){d,i->
                                Toast.makeText(this,"NO SE GUARDO EL REGISTRO EN NINGUN LADO",Toast.LENGTH_SHORT).show()
                            }
                            .show()
                    }
                /*}else{
                    AlertDialog.Builder(this)
                        .setTitle("ERROR")
                        .setMessage("NO SE PUDO INSERTAR")
                        .setPositiveButton("OK"){d,i->}
                        .show()
                }// Intentar insertar*/
            }else{
                AlertDialog.Builder(this)
                    .setTitle("REGLAS PARA REGISTRAR")
                    .setMessage("1. NO Campos vacios.\n" +
                            "2. 10 digitos para el telefono, numeros juntos.\n" +
                            "3. Escoger diferentes opciones de carrera.")
                    .setPositiveButton("OK"){d,i->}
                    .show()
            }//Validar campos

        }//btnRegistrar

        btnMostrarNube.setOnClickListener {
            if(AlumnoInteresado(this).isInternetAvailable()) {
                val otraVentana = Intent(this, MainActivity2::class.java)
                otraVentana.putExtra("BD", "NUBE")
                startActivity(otraVentana)
            }else{
                Toast.makeText(this,"NO HAY CONEXION A INTERNET",Toast.LENGTH_SHORT).show()
            }
        }//Mostrar registros de la nube en otra ventana

        btnMostrarLocales.setOnClickListener {
            val otraVentana = Intent(this,MainActivity2::class.java)
            otraVentana.putExtra("BD","LOCAL")
            startActivity(otraVentana)
        }//Mostrar registros locales en otra ventana


        btnFirebase.setOnClickListener {
            if(AlumnoInteresado(this).isInternetAvailable()){
                AlumnoInteresado(this).guardarEnLaNube()
            }else{
                Toast.makeText(this,"NO HAY CONEXION A INTERNET",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun limpiarCampos(){
        txtNombre.text.clear()
        txtEscuelaActual.text.clear()
        txtTelefono.text.clear()
        txtCorreo.text.clear()
    }

    fun validarCampos():Boolean{
        if (txtNombre.text.toString()==""||txtEscuelaActual.text.toString()==""
            ||txtTelefono.text.toString()==""||txtCorreo.text.toString()==""||
            txtTelefono.text.toString().length>10||txtTelefono.text.toString().length<10||
            spinnerCarreraUNO.selectedItem == spinnerCarreraDOS.selectedItem){
            return false
        }
        return true
    }

    fun registrarLocalmente(){
        val sePudoRegistrar = AlumnoInteresado(this).registrarAlumnoInteresado(
            txtNombre, txtEscuelaActual, txtTelefono, spinnerCarreraUNO,
            spinnerCarreraDOS, txtCorreo
        )
        if (sePudoRegistrar){
            limpiarCampos()
        }else {
            AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage("NO SE PUDO INSERTAR")
                .setPositiveButton("OK") { d, i -> }
                .show()
        }
    }


}