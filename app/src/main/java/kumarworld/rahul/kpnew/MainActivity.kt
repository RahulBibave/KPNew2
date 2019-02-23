package kumarworld.rahul.kpnew

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kumarworld.rahul.kpnew.data.ProjectData
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : BaseActivity() {
    var recyclerView:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pune:Button=bucity_pune
        var mumbai:Button=bucity_mumbai
        var bangalore:Button=bucity_bangalore


        fab.setOnClickListener {
            Toast.makeText(this,"SSSSSSSSSSSs",Toast.LENGTH_LONG).show()
        }


        var recyclerViewCity:RecyclerView=recyclerCity
        recyclerViewCity.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)

        var adpterCity=AdapterCity(this)
        recyclerViewCity.adapter=adpterCity
        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        recyclerView=recyler_main
        recyclerView!!.startAnimation(animation)
        recyclerView!!.layoutManager = GridLayoutManager(this,1, LinearLayout.VERTICAL, false)
        getProject()



        pune.setOnClickListener{
            bucity_pune.setBackgroundResource(R.drawable.city_oval_selected)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
           // var adpter=AdapterMain(this,10)
           // recyclerView.adapter=adpter
        }
        mumbai.setOnClickListener {
            bucity_pune.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval_selected)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
          //  var adpter=AdapterMain(this,1)
           // recyclerView.adapter=adpter
        }
        bangalore.setOnClickListener {
            bucity_pune.setBackgroundResource(R.drawable.city_oval)
            bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            bucity_bangalore.setBackgroundResource(R.drawable.city_oval_selected)
          //  var adpter=AdapterMain(this,3)
           // recyclerView.adapter=adpter
        }


        mapRadio.setOnClickListener {
            if (mapRadio.isChecked){
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, SearchMap(),  SearchMap().javaClass.simpleName)
                .addToBackStack(null)
                .commit()
             }
            else{
                val fm = getSupportFragmentManager()
                for (i in 0 until fm.getBackStackEntryCount()) {
                    fm.popBackStack()
                }
            }
        }


        imgOffer.setOnClickListener {  loadOffer() }

        navigation()

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


   fun navigation(){
       // Initialize the action bar drawer toggle instance
       val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
           this,
           rootLayouts,
           toolbar,
           R.string.openDrawer,
           R.string.closeDrawer
       ){
           override fun onDrawerClosed(view:View){
               super.onDrawerClosed(view)
               //toast("Drawer closed")
           }

           override fun onDrawerOpened(drawerView: View){
               super.onDrawerOpened(drawerView)
               //toast("Drawer opened")
           }

           override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
               super.onDrawerSlide(drawerView, slideOffset)
               linear_main.setTranslationX(slideOffset * drawerView.getWidth())
               rootLayouts.bringChildToFront(drawerView)
               rootLayouts.requestLayout()
           }
       }


       // Configure the drawer layout to add listener and show icon on toolbar
       drawerToggle.isDrawerIndicatorEnabled = true
       rootLayouts.addDrawerListener(drawerToggle)
       drawerToggle.syncState()


       // Set navigation view navigation item selected listener
       nav_view.setNavigationItemSelectedListener{
           when (it.itemId){


           }
           // Close the drawer
           rootLayouts.closeDrawer(GravityCompat.START)
           true
       }

   }

  /* fun getProject(){

       val queue=Volley.newRequestQueue(this)
       val url="http://app.megapolis.co.in/webservice/search"
       val stringRequest=StringRequest(Request.Method.GET,url, Response.Listener {
               response ->
           var strResp = response.toString()
           val jsonObj: JSONObject = JSONObject(strResp)
           val jsonArray: JSONArray = jsonObj.getJSONArray("data")
           for (i in 0 until jsonArray.length()){
               var jsonInner: JSONObject = jsonArray.getJSONObject(i)
               var name=jsonInner.get("name")
               var image=jsonInner.get("title_image")
               Log.e("wwwww",""+image)

           }



           var adpter=AdapterMain(this,list)
           recyclerView!!.adapter=adpter

       },




           Response.ErrorListener {  }


          )
       queue.add(stringRequest)

   }*/



    private fun getProject() {
        val queue=Volley.newRequestQueue(this)
        val list: ArrayList<ProjectData> = ArrayList()
        val req = object : StringRequest(Request.Method.POST,
            "http://app.kumarworld.com/api/all_projects",
            Response.Listener { response ->
                Log.e("wqw",""+response)

               //
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("data")
                Log.e("wqw",""+jsonArray)
                for (i in 0 until jsonArray.length()){
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    var name=jsonInner.get("project_name")
                    var id=jsonInner.get("id")
                    var city_name=jsonInner.get("city_name")
                    var lattitude=jsonInner.get("lattitude")
                    var longitude=jsonInner.get("longitude")
                    var location=jsonInner.get("location")
                    var image=jsonInner.get("image")

                    val data=ProjectData(name as String,image as String, id as String,lattitude as String,longitude as String,location as String,city_name as String)
                    list.add(data)

                    Log.e("wwsssw",""+name)

                }
                var adpter=AdapterMain(this,list)
                recyclerView!!.adapter=adpter




            }, Response.ErrorListener { e ->

                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }) {
            public override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", "1")
                return params
            }

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded";
            }
        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        queue!!.add(req)

    }
}
