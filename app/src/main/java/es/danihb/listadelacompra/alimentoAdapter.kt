package es.danihb.listadelacompra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class alimentoAdapter(private var mContext: Context?, private var alimentos: ArrayList<Alimento>) : BaseAdapter() {

    override fun getCount(): Int = alimentos.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val alimento = alimentos[position]
        var vista = convertView
        // 2
        if (vista == null) {
            val layoutInflater = LayoutInflater.from(mContext)
            vista = layoutInflater.inflate(R.layout.fragment_alimento, null)
        }
        val nombre: TextView = vista!!.findViewById(R.id.tv_alimento)
        val precio: TextView = vista.findViewById(R.id.tv_precio)
        val cantidad: TextView = vista.findViewById(R.id.tv_cantidad)

        nombre.text = "Alimento: ${alimento.getName()}"
        precio.text = "Precio: ${alimento.getPrecio()}"
        cantidad.text = "Cantidad: ${alimento.getCantidad()}"

        //TextViewCompat.setAutoSizeTextTypeWithDefaults(nameTextView!!, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        return vista
    }


    /**
     * Run a pass through each item and force a measure to determine the max height for each row
     */

}