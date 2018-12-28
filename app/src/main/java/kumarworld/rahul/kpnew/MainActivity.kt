package kumarworld.rahul.kpnew

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_project_landing.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pune:Button=bucity_pune
        var mumbai:Button=bucity_mumbai
        var bangalore:Button=bucity_bangalore



        var recyclerViewCity:RecyclerView=recyclerCity
        recyclerViewCity.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        var adpterCity=AdapterCity(this)
        recyclerViewCity.adapter=adpterCity
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        var recyclerView:RecyclerView=recyler_main
        recyclerView.startAnimation(animation)
        recyclerView.layoutManager = GridLayoutManager(this,2, LinearLayout.VERTICAL, false)

        var adpter=AdapterMain(this)
        recyclerView.adapter=adpter




        pune.setOnClickListener{
            bucity_pune.setBackgroundResource(R.drawable.city_oval_selected)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
        }
        mumbai.setOnClickListener {
            bucity_pune.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval_selected)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
        }
        bangalore.setOnClickListener {
            bucity_pune.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval_selected)
        }

        //loadOffer()

    }

    fun loadOffer(){
        val mBuilder = AlertDialog.Builder(this)
        val mView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_layout, null)
        val photoView = mView.findViewById<PhotoView>(R.id.imageView)
        Picasso.get().load("https://i1.wp.com/www.kumarworld.com/wp-content/uploads/2018/10/Cristmas-offer-3.jpg").into(photoView)


      //  photoView.setImageResource(R.drawable.demooo)
        mBuilder.setView(mView)
        val mDialog = mBuilder.create()
        mDialog.show()
    }

   /* private fun buCityOnClick(view:View){
        val buSelected= view as Button


        when(buSelected.id){
            R.id.bucity_pune-> {
                bucity_pune.setBackgroundResource(R.drawable.city_oval_selected)
                bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
                bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            }
            R.id.bucity_bangalore-> {
                bucity_pune.setBackgroundResource(R.drawable.city_oval)
                bucity_bangalore.setBackgroundResource(R.drawable.city_oval_selected)
                bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            }
            R.id.bucity_mumbai-> {
                bucity_pune.setBackgroundResource(R.drawable.city_oval)
                bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
                bucity_mumbai.setBackgroundResource(R.drawable.city_oval_selected)

            }


        }

    }*/
}
