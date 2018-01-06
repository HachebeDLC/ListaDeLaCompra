package es.danihb.listadelacompra

import android.os.Parcel
import android.os.Parcelable


class Alimento(private val nombre: String, private val cantidad: Int, private val precio: Float) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readFloat())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(nombre)
        dest.writeInt(cantidad)
        dest.writeFloat(precio)
    }

    override fun describeContents(): Int {
        return 0
    }


    fun getPrecio(): Float = precio
    fun getCantidad(): Int = cantidad
    fun getName(): String = nombre

    companion object CREATOR : Parcelable.Creator<Alimento> {
        override fun createFromParcel(parcel: Parcel): Alimento {
            return Alimento(parcel)
        }

        override fun newArray(size: Int): Array<Alimento?> {
            return arrayOfNulls(size)
        }
    }


}
