package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        AlumnoInteresado(this).mostrarRegistros()

        btnAtras.setOnClickListener {
            finish()
        }

    }

    override fun onRestart() {
        super.onRestart()
        AlumnoInteresado(this).mostrarRegistros()
    }


}