package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val index = this.intent.extras!!.getInt("INDEX")

        AlumnoInteresado(this).settearDatosParaActualizar(index,act_txtNombre,act_txtEscuelaActual,
            act_txtTelefono,act_CarreraUNO,act_CarreraDOS,act_txtCorreo,idDocument)
        //val idRegistro = this.intent.extras!!.getString("idRegistro")!!

        /*var NOMBRE = this.intent.extras!!.getString("NOMBRE")
        var ESCUELA = this.intent.extras!!.getString("ESCUELA")
        var TELEFONO = this.intent.extras!!.getString("TELEFONO")
        var CARRERA_UNO = this.intent.extras!!.getString("CARRERA_UNO")
        var CARRERA_DOS = this.intent.extras!!.getString("CARRERA_DOS")
        var CORREO = this.intent.extras!!.getString("CORREO")
        act_txtNombre.setText(NOMBRE)
        act_txtEscuelaActual.setText(ESCUELA)
        act_txtTelefono.setText(TELEFONO)
        act_CarreraUNO.setText(CARRERA_UNO)
        act_CarreraDOS.setText(CARRERA_DOS)
        act_txtCorreo.setText(CORREO)*/

        btnActualizar.setOnClickListener {
            AlumnoInteresado(this).actualizar(act_txtNombre,act_txtEscuelaActual,
            act_txtTelefono,act_CarreraUNO,act_CarreraDOS,act_txtCorreo,idDocument)
        }

        btnEliminar.setOnClickListener {
            AlumnoInteresado(this).eliminar(idDocument)
        }

        btnVolverActualizar.setOnClickListener { finish() }
    }
}