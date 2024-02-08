package com.example.myapplication.ui

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    fun navigateTo(dir: NavDirections) {
        try {
            findNavController().navigate(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun snackBarMessage(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.green))
        snackBar.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        snackBar.show()
    }

}