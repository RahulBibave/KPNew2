package kumarworld.rahul.kpnew

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View

class ActivityDemo : BaseActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        val received: String = intent.getStringExtra("input")
        if (received.equals("about")){
            val aboutData:String=intent.getStringExtra("aboutData")
            val aboutFragment=FragmentAboutProject()
            var bundle = Bundle()
            bundle.putString("about",aboutData)
            aboutFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentView, aboutFragment,  aboutFragment.javaClass.simpleName)
                .addToBackStack(null)
                .commit()
        }
        else if (received.equals("amenities")){
            val amenitiesData1:String=intent.getStringExtra("amenities1")
            val amenitiesData2:String=intent.getStringExtra("amenities2")
            val aminitiesFragmnet=FragmentAminites()
            var bundle=Bundle()
            bundle.putString("amenities1",amenitiesData1)
            bundle.putString("amenities2",amenitiesData2)
            aminitiesFragmnet.arguments=bundle
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentView, aminitiesFragmnet,  aminitiesFragmnet.javaClass.simpleName)
                .addToBackStack(null)
                .commit()

        }
        else if (received.equals("gallery")){

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentView, SearchMap(),  SearchMap().javaClass.simpleName)
                .addToBackStack(null)
                .commit()

        }
        else if (received.equals("floorplan")){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentView, SearchMap(),  SearchMap().javaClass.simpleName)
                .addToBackStack(null)
                .commit()


        }

        else if (received.equals("about")){




        }

    }


}