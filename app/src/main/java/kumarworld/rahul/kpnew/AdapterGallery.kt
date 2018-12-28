package kumarworld.rahul.kpnew

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.gallary_view.view.*
import kotlinx.android.synthetic.main.list_layout.view.*

class AdapterGallery (var context:Context):RecyclerView.Adapter<AdapterGallery.ViewHolderGallery>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderGallery {
        val v = LayoutInflater.from(context).inflate(R.layout.gallary_view, p0, false)
        return AdapterGallery.ViewHolderGallery(v)
    }

    override fun getItemCount(): Int {
       return 10
    }

    override fun onBindViewHolder(p0: ViewHolderGallery, p1: Int) {
        p0.bindItems()
        p0.GalleryImage!!.setOnClickListener{
            val mBuilder = AlertDialog.Builder(context)
            val mView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_layout, null)
            val photoView = mView.findViewById<PhotoView>(R.id.imageView)
            photoView.setImageResource(R.drawable.demooo)
            mBuilder.setView(mView)
            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }


    class ViewHolderGallery (itemView: View): RecyclerView.ViewHolder(itemView) {
        var GalleryImage: ImageView? = null
        fun bindItems() {
            GalleryImage= itemView.galleryImage

        }
    }
}