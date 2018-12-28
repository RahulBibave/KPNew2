package kumarworld.rahul.kpnew

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_project_landing.*
import kotlinx.android.synthetic.main.demo_layout.*

class ProjectLandingActivity : BaseActivity() {

  var menu:Menu?=null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_layout)

        val animation = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        projectLandingMain.startAnimation(animation)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        mToolbar.title="Megapolis"
        setSupportActionBar(mToolbar)
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

        var recyclerView:RecyclerView=recyclergallary
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        var adpter=AdapterGallery(this)
        recyclerView.adapter=adpter

        liveview.setOnClickListener {
            var  intent=Intent(this,LiveViewActivity::class.java)
            startActivity(intent)
        }




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
            return true
        } else if (id == R.id.action_info) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun hideOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = false
    }

    private fun showOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = true
    }
}
