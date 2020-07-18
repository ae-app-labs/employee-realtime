package academy.learnprogramming

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_emp_list.*

class MainActivity : AppCompatActivity() {

    private var adapter: UserAdapter? = null
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_emp_list)

        val arrayOfUsers = ArrayList<User>()

        list_view!!.emptyView = findViewById(R.id.empty)

        adapter = UserAdapter(this, arrayOfUsers)

        list_view!!.adapter = adapter

        // TODO:  invoke allEmployees function in database helper, passing in the adapter to add results to
        dbHelper = DatabaseHelper()
        dbHelper.open()
        dbHelper.allEmployees(adapter)

        list_view!!.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                var user = adapter?.getItem(position)

                val modify_intent = Intent(applicationContext, ModifyEmployeeActivity::class.java)
                modify_intent.putExtra("name", user?.name)
                modify_intent.putExtra("address", user?.address)
                modify_intent.putExtra("id", user?._id)

                startActivity(modify_intent)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.add_record) {
            val add_mem = Intent(this, AddEmployeeActivity::class.java)
            startActivity(add_mem)
        }
        return super.onOptionsItemSelected(item)
    }
}
