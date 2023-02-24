package com.example.weatherapp.dialog

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.example.weatherapp.R

object DialogSearchCity {
    fun searchByCity(context: Context, listener: ListenerCity){
        val builder = AlertDialog.Builder(context)
        val city = EditText(context)
        builder.setView(city)
        val dialog = builder.create()
        dialog.setTitle("Название города:")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {
                _, _ -> listener.onClick(city.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL") {
                _, _ -> dialog.dismiss()
        }
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getColor(R.color.black));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getColor(R.color.black));
    }

    interface ListenerCity{
        fun onClick(name: String)
    }
}