package mx.edu.ittepic.ladm_u3_practica1_basedatosmixta_marioavalos_danielsandoval

import android.R
import android.content.ContentValues
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.sql.Date

data class AlumnoInteresado(val m:AppCompatActivity){

    private var baseDatos = BaseDatos(m,"REGISTROS_6",null,1)

    fun registrarAlumnoInteresado(nombre:EditText,escuela:EditText,
                                  telefono:EditText,carreraUno:Spinner,
                                  carreraDos:Spinner,correo:EditText) {
        val NOMBRE = nombre.text.toString()
        val ESCUELA = escuela.text.toString()
        val TELEFONO = telefono.text.toString()
        val CARRERA_UNO = carreraUno.selectedItem.toString()
        val CARRERA_DOS = carreraDos.selectedItem.toString()
        val CORREO = correo.text.toString()

        var datos = ContentValues()
        datos.put("NOMBRE",NOMBRE)
        datos.put("ESCUELA_ACTUAL",ESCUELA)
        datos.put("TELEFONO",TELEFONO)
        datos.put("CARRERA_DOS",CARRERA_UNO)
        datos.put("CARRERA_UNO",CARRERA_DOS)
        datos.put("CORREO",CORREO)


        var res = baseDatos.writableDatabase.insert("ALUMNO_INTERESADO","ID",datos)
        if (res == -1L){
            AlertDialog.Builder(m)
                .setTitle("ERROR")
                .setMessage("NO SE PUDO GUARDAR").show()
        }else{
            Toast.makeText(m,"Se INSERTO CON EXITO", Toast.LENGTH_LONG).show()

        }


        /*
        var datos = ContentValues()
        datos.put("NOMBRE",nombre.text.toString())
        datos.put("ESCUELA",escuela.text.toString())
        datos.put("TELEFONO",telefono.text.toString())
        datos.put("CARRERA_UNO",carreraUno.selectedItem.toString())
        datos.put("CARRERA_DOS",carreraDos.selectedItem.toString())
        datos.put("CORREO",correo.text.toString())

        var cadena = "$nombre\n$escuela\n$telefono\n$carrera_uno" +
                "\n$carrera_dos\n$correo"

        if (camposNoVacios(nombre,escuela,telefono,correo)){
            AlertDialog.Builder(m)
                .setTitle("DATOS")
                .setMessage(cadena)
                .show()
            limpiarCampos()
        }else{
            AlertDialog.Builder(m)
                .setTitle("ERROR")
                .setMessage("Existen campos vacios")
                .show()
        }

         */



    }

    fun mostrarRegistros(){
        var registros = baseDatos.readableDatabase
        val lista = ArrayList<String>()
        var resultado = registros.query("ALUMNO_INTERESADO", arrayOf("*"), null,
            null, null, null, null)

        if (resultado.moveToFirst()) {
            do {
                val datos = "${resultado.getString(1)}" +
                        "\n${resultado.getString(2)}" +
                        "\n${resultado.getString(3)}" +
                        "\n${resultado.getString(4)}" +
                        "\n${resultado.getString(5)}" +
                        "\n${resultado.getString(6)}" +
                        "\n${resultado.getString(7)}" +
                        "\n${resultado.getString(8)}"
                lista.add(datos)
                //IDs.add(resultado.getInt(0))
            } while (resultado.moveToNext())

        } else {
            lista.add("LA TABLA ESTA VACIA")
        }

        m.listaRegistros.adapter = ArrayAdapter<String>(m,
            R.layout.simple_list_item_1, lista)
    }

    fun camposNoVacios(nombre:String,escuela:String,
                       telefono:String,correo:String):Boolean{
        if (nombre==""||escuela==""||telefono==""||correo==""){
            return false
        }
        return true
    }

    fun limpiarCampos(){
        m.txtNombre.text.clear()
        m.txtEscuelaActual.text.clear()
        m.txtTelefono.text.clear()
        m.txtCorreo.text.clear()
    }
}
