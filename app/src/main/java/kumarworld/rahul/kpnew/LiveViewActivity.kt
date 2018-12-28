package kumarworld.rahul.kpnew

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play_video.*


class LiveViewActivity :BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)
        var webSetting=webView!!.settings
        webSetting.javaScriptEnabled=true
        webSetting.builtInZoomControls=true
        webView!!.loadUrl("https://rtsp.me/embed/fbQnyN9t/")



    }
}