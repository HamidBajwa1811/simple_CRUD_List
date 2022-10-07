package com.example.crudtask

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var arrContacts = ArrayList<ContactModel1>()
    var adopter: RecyclerContactAdopter? = null
//    var btnOpenDialog: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceStage: Bundle?) {
        super.onCreate(savedInstanceStage)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclercontact)

        //new code
//        btnOpenDialog = findViewById(R.id.btnOpenDialog)
        btnOpenDialog.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_update_lay)
            val edtName = dialog.findViewById<EditText>(R.id.edtName)
            val edtNumber = dialog.findViewById<EditText>(R.id.edtNumber)
            val btnAction = dialog.findViewById<Button>(R.id.btnAction)
            btnAction.setOnClickListener {
                var name = ""
                var number = ""
                if (edtName.text.toString() != "") {
                    name = edtName.text.toString()
                } else {
                    Toast.makeText(this, "please enter contact name", Toast.LENGTH_SHORT).show()
                }
                if (edtNumber.text.toString() != "") {
                    number = edtNumber.text.toString()
                } else {
                    Toast.makeText(this, "please enter contact number", Toast.LENGTH_SHORT).show()
                }
                arrContacts.add(ContactModel1(R.drawable.d,name, number))
                adopter!!.notifyItemInserted(arrContacts.size - 1)
                recyclercontact.scrollToPosition(arrContacts.size - 1)
                dialog.dismiss()
            }
            dialog.show()
        })
        arrContacts.add(ContactModel1(R.drawable.d, "A", "034555525"))
        arrContacts.add(ContactModel1(R.drawable.d, "B", "0345251425"))
        arrContacts.add(ContactModel1(R.drawable.d, "C", "03469587457"))
        arrContacts.add(ContactModel1(R.drawable.d, "D", "033625142603"))
        arrContacts.add(ContactModel1(R.drawable.d, "E", "034625148930"))
        arrContacts.add(ContactModel1(R.drawable.d, "F", "031234567894"))
        arrContacts.add(ContactModel1(R.drawable.d, "G", "03145678912"))
        arrContacts.add(ContactModel1(R.drawable.d, "H", "031254758635"))
        arrContacts.add(ContactModel1(R.drawable.d, "i", "034715975321"))
        arrContacts.add(ContactModel1(R.drawable.d, "j", "03121597536"))
        recyclercontact.setLayoutManager(LinearLayoutManager(this))
        adopter = RecyclerContactAdopter(this, arrContacts)
        recyclercontact.setAdapter(adopter)
    }
}