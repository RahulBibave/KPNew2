package kumarworld.rahul.kpnew

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_project_landing.*
import kotlinx.android.synthetic.main.demo_layout.*
import kumarworld.rahul.kpnew.data.PriceArea
import kumarworld.rahul.kpnew.data.ProjectData
import kumarworld.rahul.kpnew.data.ProjectDetails
import layout.FragmentEnquiry
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class ProjectLandingActivity : BaseActivity() {

    var menu:Menu?=null
    val mProjectData: ArrayList<ProjectDetails> = ArrayList()
    var x=""
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.demo_layout)
        val animation = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        projectLandingMain.startAnimation(animation)
        val project_id=intent.getStringExtra("project_id")
        getProjectDetails(project_id)



       //




/*

        liveview.setOnClickListener {
            //val mapi = SearchMap()
            supportFragmentManager.beginTransaction()
                .replace(R.id.projectLandingMain, SearchMap(),  SearchMap().javaClass.simpleName)
                .addToBackStack(null)
                .commit()
        }
*/




    }

     fun buDetails(view:View){

         val buSelected= view
         val intent = Intent(this,ActivityDemo::class.java) //not application context


        when(buSelected.id){



            R.id.about-> {

                intent.putExtra("input","about")
                intent.putExtra("aboutData",mProjectData.get(0).mProDesc)
                startActivity(intent)
                /*val aboutFragment=FragmentAboutProject()
                var bundle = Bundle()
                bundle.putString("about",mProjectData.get(0).mProDesc)
                aboutFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                .replace(R.id.projectLandingMain, aboutFragment,  aboutFragment.javaClass.simpleName)
                .addToBackStack(null)
                .commit()*/



            }


            R.id.amenities-> {
                intent.putExtra("input","amenities")
                intent.putExtra("amenities1",mProjectData.get(0).getmCommAmi())
                intent.putExtra("amenities2",mProjectData.get(0).getmSpeAmi())
                startActivity(intent)
              /*  amenities.isClickable=true
                supportFragmentManager.beginTransaction()
                    .add(R.id.projectLandingMain, FragmentAminites(),  FragmentAminites().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()*/
            }


            R.id.gallery-> {
                intent.putExtra("input","gallery")
                startActivity(intent)

               /* supportFragmentManager.beginTransaction()
                    .add(R.id.projectLandingMain, SearchMap(),  SearchMap().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()*/
            }

            R.id.floorplan-> {
                intent.putExtra("input","floorplan")
                startActivity(intent)
                /*supportFragmentManager.beginTransaction()
                    .replace(R.id.projectLandingMain, SearchMap(),  SearchMap().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()*/
            }

            R.id.locationmap-> {
               /* supportFragmentManager.beginTransaction()
                    .replace(R.id.projectLandingMain, SearchMap(),  SearchMap().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()*/
            }

            R.id.liveview-> {

                val liveFragment=FragmentLiveView()
                var bundle = Bundle()
                bundle.putString("liveview",mProjectData.get(0).mCamera_feed)
                liveFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.projectLandingMain, liveFragment,  liveFragment.javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()
            }

            R.id.enquiry-> {
               /* supportFragmentManager.beginTransaction()
                    .replace(R.id.projectLandingMain, FragmentEnquiry(),  FragmentEnquiry().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()*/
            }

            R.id.website-> {
                openNewTabWindow("https://www.kumarworld.com/")
            }

            R.id.contactus-> {
               /* supportFragmentManager.beginTransaction()
                .add(R.id.projectLandingMain, FragmentContactUs(),  FragmentContactUs().javaClass.simpleName)
                .addToBackStack(null)
                .commit()*/
                }


        }



    }



     fun getProjectDetails(proId:String){
       // http://app.kumarworld.com/api/project_details (parametrs -> project_id)


        val queue=Volley.newRequestQueue(this)

        val req = object : StringRequest(Request.Method.POST,
            "http://app.kumarworld.com/api/project_details",
            Response.Listener { response ->
                Log.e("wqw",""+response)

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("data")
                Log.e("ssssssssssssssss",""+jsonArray)
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                     var mProID      = jsonInner.get("project_id") as String
                     var mProName   = jsonInner.get("project_name") as String
                     var mAddress   = jsonInner.get("address") as String
                     var mPinCode   = jsonInner.get("pin_code") as String
                     var mCat       = jsonInner.get("category") as String
                     var mProType    = jsonInner.get("property_type") as String
                     var mProStatus  = jsonInner.get("project_status") as String
                     var mProImage   = jsonInner.get("landing_image") as String
                     var mProLat   = jsonInner.get("lattitude") as String
                     var mProLong  = jsonInner.get("longitude") as String
                     var mYoutube  = jsonInner.get("youtube") as String
                     var mWebsite  = jsonInner.get("website") as String
                     var mReraNum  = jsonInner.get("rera_number") as String
                     var mRubeId  = jsonInner.get("rqube_id") as String
                     var mSpeAmi  = jsonInner.get("specific_amenities") as String
                     var mCommAmi  = jsonInner.get("common_amenities") as String
                     var mProDesc  = jsonInner.get("project_description") as String
                     var mFb        = jsonInner.get("facebook") as String
                     var mOrgName  = jsonInner.get("org_name") as String
                     var mTwitter  = jsonInner.get("twitter") as String
                     var mInsta   = jsonInner.get("instagram") as String
                     var mSms      = jsonInner.get("sms_contact") as String
                     var mWhatsapp  = jsonInner.get("whatsapp_contact") as String
                     var mProLocation = jsonInner.get("location") as String
                     var mCityName   = jsonInner.get("city_name") as String
                     var mAiBot     = jsonInner.get("ai_bot") as String
                     var mCamera   =jsonInner.get("camera_feed")as String



                    cat_titdesc.text=mCat






                    val jsonArrayGallery: JSONArray = jsonInner.getJSONArray("gallary_image")
                    val mGallaryImg: ArrayList<String> = ArrayList()
                    for (i in 0 until jsonArrayGallery.length()){
                        var img=jsonArrayGallery.get(i) as String
                        mGallaryImg.add(img)
                    }


                    val jsonArrayRera: JSONArray = jsonInner.getJSONArray("rera_certificate")
                    val mReraCerti: ArrayList<String> = ArrayList()
                    for (i in 0 until jsonArrayRera.length()){
                        var img=jsonArrayRera.get(i) as String
                        mReraCerti.add(img)
                    }




                    val jsonArrayFlorePlane: JSONArray = jsonInner.getJSONArray("floor_plan")
                    val mFloorPlan: ArrayList<String> = ArrayList()
                    for (i in 0 until jsonArrayFlorePlane.length()){
                        var img=jsonArrayFlorePlane.get(i) as String
                        mFloorPlan.add(img)
                    }




                    val jsonArrayCost: JSONArray = jsonInner.getJSONArray("cost_sheet")
                    val mCostSheet: ArrayList<String> = ArrayList()
                    for (i in 0 until jsonArrayCost.length()){
                        var img=jsonArrayCost.get(i) as String
                        mCostSheet.add(img)
                    }



                    val jsonArrayArea: JSONArray = jsonInner.getJSONArray("get_price_area")
                    val mPriceArea: ArrayList<PriceArea> = ArrayList()
                    for (i in 0 until jsonArrayArea.length()){
                        var jsonAreaPrice: JSONObject = jsonArrayArea.getJSONObject(i)

                        var mPriceFrom=jsonAreaPrice.get("price_from")as String
                        var mAreaFrom=jsonAreaPrice.get("area_from")as String
                        var mAreaTo=jsonAreaPrice.get("area_to")as String
                        var mPriceSuffix=jsonAreaPrice.get("suffix_string")as String
                        var mFlatType=jsonAreaPrice.get("flat_type")as String



                        val priceArea=PriceArea(mPriceFrom,mAreaFrom,mAreaTo,mPriceSuffix,mFlatType)

                        mPriceArea.add(priceArea)
                    }



                    val projectDetails=ProjectDetails(
                            mProID,mProName,mAddress,mPinCode,mCat , mProType , mProStatus  ,mProImage , mProLat   ,mProLong , mYoutube
                          , mWebsite , mReraNum  , mRubeId  , mSpeAmi , mCommAmi , mProDesc, mFb   , mOrgName   , mTwitter  , mInsta   , mSms
                          , mWhatsapp, mProLocation, mCityName, mAiBot  ,mCamera,mGallaryImg,mReraCerti,mFloorPlan,mCostSheet,mPriceArea )
                    mProjectData.add(projectDetails)

                }

                Picasso.get().load(mProjectData.get(0).mProImage).into(expandedImage)
                val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
                mToolbar.title=mProjectData.get(0).mProName
                setSupportActionBar(mToolbar)

                var x:String=""
                for (i in 0 until mProjectData.get(0).mPriceArea.size){
                   x=x+mProjectData.get(0).mPriceArea.get(i).mFlatType+" "
                }
                txtFlatType.text=x



                val fab = findViewById<View>(R.id.fab) as FloatingActionButton
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                val mAppBarLayout = findViewById<View>(R.id.app_bar) as AppBarLayout
                mAppBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
                    internal var isShow = false
                    internal var scrollRange = -1

                    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.totalScrollRange
                        }
                        if (scrollRange + verticalOffset == 0) {
                            isShow = true
                            showOption(R.id.action_info)
                        } else if (isShow) {
                            isShow = false
                            hideOption(R.id.action_info)
                        }
                    }
                })

                getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
                getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
                mToolbar.setNavigationOnClickListener(){onBackPressed()}


                var recyclerView:RecyclerView=recyclergallary
                recyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
                var adpter=AdapterGallery(this,mProjectData.get(0).mGallaryImg)
                recyclerView.adapter=adpter





                //Social on Click

                instagram.setOnClickListener { val uri = Uri.parse(mProjectData.get(0).mInsta)
                    val likeIng = Intent(Intent.ACTION_VIEW, uri)

                    likeIng.setPackage("com.instagram.android")

                    try {
                        startActivity(likeIng)
                    } catch (e: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(mProjectData.get(0).mInsta)
                            )
                        )
                    } }

                fb.setOnClickListener {
                    val uri = Uri.parse(mProjectData.get(0).mFb)
                    val likeIng = Intent(Intent.ACTION_VIEW, uri)

                    likeIng.setPackage("com.facebook.android")

                    try {
                        startActivity(likeIng)
                    } catch (e: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(mProjectData.get(0).mFb)
                            )
                        )
                    }
                }

                tw.setOnClickListener {  }

                youtube.setOnClickListener {  }

                sms.setOnClickListener {  }


                call.setOnClickListener {  }















                }, Response.ErrorListener { e ->

                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }) {
            public override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("project_id", proId)
                return params
            }

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded";
            }
        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        queue!!.add(req)

    }



    fun openNewTabWindow(urls: String) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        startActivity(intents)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        hideOption(R.id.action_info)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return false
        } else if (id == R.id.action_info) {
            return false
        }

        return super.onOptionsItemSelected(item)
    }


    private fun hideOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = false
    }

    private fun showOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = false
    }






}
