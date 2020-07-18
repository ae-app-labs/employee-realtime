package academy.learnprogramming

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_modify_employee.*

class ModifyEmployeeActivity : AppCompatActivity() {

    private var ID: String = ""
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_employee)

        dbHelper = DatabaseHelper()
        dbHelper.open()

        val intent = intent
        ID = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("address")

        name_edittext!!.setText(name)
        address_edittext!!.setText(desc)
    }

    private fun returnToMainActivity() {
        finish()
    }

    fun updateButtonPressed(view: View) {
        val name = name_edittext!!.text.toString()
        val address = address_edittext!!.text.toString()

        dbHelper.update(ID, name, address)

        returnToMainActivity()
    }

    fun deleteButtonPressed(view: View) {
        dbHelper.delete(ID)
        returnToMainActivity()
    }
}
