package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val idDocument = this.intent.extras!!.getString("ID")!!

        AlumnoInteresado(this).settearDatosParaActualizar(idDocument,act_txtNombre,act_txtEscuelaActual,
            act_txtTelefono,act_CarreraUNO,act_CarreraDOS,act_txtCorreo)

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