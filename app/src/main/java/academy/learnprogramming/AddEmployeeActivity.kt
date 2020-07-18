package academy.learnprogramming

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_employee.*

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var dbHelper:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        dbHelper = DatabaseHelper()
        dbHelper.open()
    }

    fun addButtonPressed(view: View) {
        val name = name_edittext!!.text.toString()
        val address = address_edittext!!.text.toString()

        dbHelper.add(name, address)

        finish()
    }
}
