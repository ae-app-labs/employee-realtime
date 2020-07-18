package academy.learnprogramming

import android.util.Log
import com.google.firebase.database.*

class DatabaseHelper {

    private lateinit var database: DatabaseReference

    fun allEmployees(adapter: UserAdapter?) {
        val userReference = database.child("users")

        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adapter?.clear()

                var counter = 1;

                for(myUser in snapshot.children){
                    val user = myUser.getValue(User::class.java)

                    if(null == user){
                        Log.e("DatabaseHelper", "User data is null")
                        return
                    }

                    user._id = myUser.key!!
                    user.counter = (counter++).toString()
                    adapter?.add(user)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseHelper", "Failed to read user", error.toException())
            }
        })
    }

    fun open() {
        database = FirebaseDatabase.getInstance().reference
    }

    fun add(name: String, address: String) {
        val key = database.child("users").push().key
        database.child("users").child(key!!).child("name").setValue(name)
        database.child("users").child(key).child("address").setValue(address)
    }

    fun update(_id: String, name: String, address: String) {
        database.child("users").child(_id).child("name").setValue(name)
        database.child("users").child(_id).child("address").setValue(address)
    }

    fun delete(_id: String) {
        database.child("users").child(_id).removeValue()
    }
}
