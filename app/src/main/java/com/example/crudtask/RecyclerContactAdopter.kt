package com.example.crudtask

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class RecyclerContactAdopter internal constructor(
    var context: Context,
    var arrContacts: ArrayList<ContactModel1>
) :
    RecyclerView.Adapter<RecyclerContactAdopter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = arrContacts[position]
        holder.imgContact.setImageResource(model.img)
        holder.txtName.text = model.name
        holder.txtNumber.text = model.number
        holder.llRow.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.add_update_lay)
            val edtName = dialog.findViewById<EditText>(R.id.edtName)
            val edtNumber = dialog.findViewById<EditText>(R.id.edtNumber)
            val btnAction = dialog.findViewById<Button>(R.id.btnAction)
            val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
            txtTitle.text = "Update Contact"
            btnAction.text = "update"
            edtName.setText(arrContacts[position].name)
            edtNumber.setText(arrContacts[position].number)
            btnAction.setOnClickListener {
                var name = ""
                var number = ""
                if (edtName.text.toString() != "") {
                    name = edtName.text.toString()
                } else {
                    Toast.makeText(context, "please enter contact name", Toast.LENGTH_SHORT)
                        .show()
                }
                if (edtNumber.text.toString() != "") {
                    number = edtNumber.text.toString()
                } else {
                    Toast.makeText(context, "please enter contact number", Toast.LENGTH_SHORT)
                        .show()
                }
                arrContacts[position] = ContactModel1(name, number)
                notifyItemChanged(position)
                dialog.dismiss()
            }
            dialog.show()
        }
        holder.llRow.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)
                .setTitle("Delete Contact")
                .setMessage("AreYou sure to delete?")
                .setPositiveButton("yes") { dialog, which ->
                    arrContacts.removeAt(position)
                    notifyItemChanged(position)
                }
                .setNegativeButton("no") { dialog, which -> }
            builder.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return arrContacts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView
        var txtNumber: TextView
        var imgContact: ImageView
        var llRow: LinearLayout

        init {
            txtName = itemView.findViewById(R.id.txtName)
            txtNumber = itemView.findViewById(R.id.txtnumber)
            imgContact = itemView.findViewById(R.id.imgcontact)
            llRow = itemView.findViewById(R.id.llRow)
        }
    }
}