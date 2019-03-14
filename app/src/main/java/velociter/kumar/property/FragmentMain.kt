package velociter.kumar.property

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_main.view.*
import velociter.kumar.property.data.ProjectData
import org.json.JSONArray
import org.json.JSONObject

class FragmentMain :Fragment() {


    var recyclerView: RecyclerView?=null
    val listLatLong: ArrayList<LatLong> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView= inflater?.inflate(R.layout.fragment_main, container, false)


        var pune: Button =rootView.bucity_pune
        var mumbai:Button=rootView.bucity_mumbai
        var bangalore:Button=rootView.bucity_bangalore


        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        recyclerView=rootView.recyler_main
        recyclerView!!.startAnimation(animation)
        recyclerView!!.layoutManager = GridLayoutManager(context,1, LinearLayout.VERTICAL, false)
        getProject("1")


        pune.setOnClickListener{
            rootView.bucity_pune.setBackgroundResource(R.drawable.city_oval_selected)
            rootView.bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
            rootView.bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            getProject("1")
            // var adpter=AdapterMain(this,10)
            // recyclerView.adapter=adpter
        }
        mumbai.setOnClickListener {
            rootView.bucity_pune.setBackgroundResource(R.drawable.city_oval)
            rootView.bucity_mumbai.setBackgroundResource(R.drawable.city_oval_selected)
            rootView. bucity_bangalore.setBackgroundResource(R.drawable.city_oval)
            getProject("2")
            //  var adpter=AdapterMain(this,1)
            // recyclerView.adapter=adpter
        }
        bangalore.setOnClickListener {
            rootView.bucity_pune.setBackgroundResource(R.drawable.city_oval)
            rootView.bucity_mumbai.setBackgroundResource(R.drawable.city_oval)
            rootView.bucity_bangalore.setBackgroundResource(R.drawable.city_oval_selected)
            getProject("3")
            //  var adpter=AdapterMain(this,3)
            // recyclerView.adapter=adpter
        }





        return rootView

    }


    private fun getProject(cityID:String) {
        val queue= Volley.newRequestQueue(context)
        val list: ArrayList<ProjectData> = ArrayList()
        val req = object : StringRequest(Request.Method.POST,
            "http://app.kumarworld.com/api/all_projects",
            Response.Listener { response ->
                Log.e("wqw",""+response)

                //
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                var status=jsonObj.getInt("status")
                if (status==200) {

                    val jsonArray: JSONArray = jsonObj.getJSONArray("data")
                    Log.e("wqw", "" + jsonArray)
                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        var name = jsonInner.get("project_name")
                        var id = jsonInner.get("id")
                        var city_name = jsonInner.get("city_name")
                        var lattitude = jsonInner.get("lattitude")
                        var longitude = jsonInner.get("longitude")
                        var location = jsonInner.get("location")
                        var image = jsonInner.get("image")

                        val data = ProjectData(
                            name as String,
                            image as String,
                            id as String,
                            lattitude as String,
                            longitude as String,
                            location as String,
                            city_name as String
                        )
                        val latlong=LatLong(lattitude ,longitude, name,id)
                        list.add(data)

                        Log.e("wwsssw", "" + name)

                    }
                    var adpter = AdapterMain(context!!, list)
                    recyclerView!!.adapter = adpter


                }

            }, Response.ErrorListener { e ->

                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }) {
            public override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", cityID)
                return params
            }

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded";
            }
        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        queue!!.add(req)

    }




    fun someDialog(message:String){
        AlertDialog.Builder(context).apply {
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


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}