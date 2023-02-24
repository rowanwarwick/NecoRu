package com.example.weatherapp.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import com.example.weatherapp.R

object DialogGPS {
    fun locationSettingsDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("GPS отключен")
        dialog.setMessage("Включить?")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {
            _, _ -> listener.onClick()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL") {
            _, _ -> dialog.dismiss()
        }
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getColor(R.color.black));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getColor(R.color.black));
    }

    interface Listener{
        fun onClick()
    }
}