package velociter.kumar.property

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.github.chrisbanes.photoview.PhotoView
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){
    var recyclerView:RecyclerView?=null
    val listLatLong: ArrayList<LatLong> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("demo")
            .addOnCompleteListener { task ->
                var msg = "msg_subscribed"
                if (!task.isSuccessful) {
                    msg = "msg_subscribe_failed"
                }
              //  Log.d(TAG, msg)
              //  Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }


        if (isNetworkAvailable()){
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, FragmentMain(),  FragmentMain().javaClass.simpleName)
                .commit()
        }
        else{
            someDialog("internet not working")
        }

        fab.setOnClickListener {
            if(isNetworkAvailable()){
                var intent=Intent(this,AI_Bot::class.java)
                intent.putExtra("aibot","<script src=\"https://www.kenyt.ai/botapp/ChatbotUI/dist/js/bot-loader.js\" type=\"text/javascript\" data-bot=\"Kumar_Properties\"></script>")
                startActivity(intent)
            } else{
                someDialog("internet not working")
            }

        }




        var recyclerViewCity:RecyclerView=recyclerCity
        recyclerViewCity.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)

        var adpterCity=AdapterCity(this)
        recyclerViewCity.adapter=adpterCity





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
                R.id.nav_serach_byCat->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFrame, FragmentOffer(),  "FragmentB")
                        .addToBackStack("A_B_TAG")
                        .commit()
                }

               R.id.nav_home ->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.mainFrame, FragmentMain(),  FragmentMain().javaClass.simpleName)
                       .commit()
               }

               R.id.nav_profile ->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.mainFrame, SearchMap(), "FragmentB")
                       .addToBackStack("A_B_TAG")
                       .commit()
               }
               R.id.nav_shareapp ->{
                   val sendIntent: Intent = Intent().apply {
                       action = Intent.ACTION_SEND
                       putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=velociter.kumar.property")
                       type = "text/plain"
                   }
                   startActivity(sendIntent)
               }

           }
           // Close the drawer
           rootLayouts.closeDrawer(GravityCompat.START)
           true
       }

   }




    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {







        getSupportFragmentManager().popBackStack()

        if (doubleBackToExitPressedOnce) {
            if (getSupportFragmentManager().findFragmentByTag("FragmentC") != null) {
                // I'm viewing Fragment C
                getSupportFragmentManager().popBackStack("A_B_TAG",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } else {
                super.onBackPressed()
            }
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

    }



    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun someDialog(message:String){
        android.app.AlertDialog.Builder(this).apply {
            setTitle("Kumar Properties")
            setMessage(message)
            setNegativeButton("Ok") { dialog, which ->
                dialog.cancel()
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    fun Back(){

    }
}
