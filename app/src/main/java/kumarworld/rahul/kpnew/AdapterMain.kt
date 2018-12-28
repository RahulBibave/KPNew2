package kumarworld.rahul.kpnew

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.list_layout.view.*
import kumarworld.rahul.kpnew.R.id.*

class AdapterMain(val context: Context) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_layout, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItems()
        p0.ProjectImage!!.setOnClickListener{

            var intent=Intent(context,ProjectLandingActivity::class.java)
            context.startActivity(intent)
        }


    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ProjectImage:ImageView? = null
        var real:RelativeLayout?=null
        fun bindItems() {
            ProjectImage= itemView.projectImage




        }
    }
}