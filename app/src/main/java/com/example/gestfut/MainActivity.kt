package com.example.gestfut

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gestfut.adaptador.AdaptadorPartido
import com.example.gestfut.data.EquipoProveedor
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mitoolbar:Toolbar
    lateinit var mibinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //BINDING
        mibinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Inicializo el contexto de ProveedorPartido
        PartidoProveedor.inicializar(this)
       //Inicializo el contexto de ProveedorEquipo
        EquipoProveedor.inicializar(this)

        //Inicializar toolbar
        inicializar_toolbar()




    }



    private fun inicializar_toolbar() {
        this.mitoolbar=findViewById(R.id.toolbar)
        this.mitoolbar.setTitle("GEST-FUT V1.0")
        this.mitoolbar.setLogo(R.drawable.ic_lfp_vector_logo)
        setSupportActionBar(this.mitoolbar)

    }
}