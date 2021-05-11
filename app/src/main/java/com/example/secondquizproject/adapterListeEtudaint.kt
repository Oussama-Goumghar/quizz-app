import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.secondquizproject.Etudiant.Etudiant
import com.example.secondquizproject.R


class adapterListeEtudaint(private val exampleList: List<Etudiant>): RecyclerView.Adapter<adapterListeEtudaint.viewHolder>(){
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemlyout , parent , false)
        context=itemView.context
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.image.animation= AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation)
        holder.container.animation= AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation)

        val currn =  exampleList[position]
        holder.nom.text = currn.nom+"  "+currn.pernom
        holder.image.setImageBitmap(getImage(currn.image!!))
        holder.Scour.text = "high scoure: "+currn.Scoure.toString()
        holder.itemView.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("nom",currn.nom)
            bundle.putString("pernom",currn.pernom)
            bundle.putInt("scoure", currn.Scoure!!)
            bundle.putByteArray("image",currn.image)

        }




    }

    override fun getItemCount() = exampleList.size ;

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nom : TextView = itemView.findViewById(R.id.nomtxt)
        var image : ImageView = itemView.findViewById(R.id.imagprof)
        var Scour : TextView =itemView.findViewById(R.id.textScour)
        var container: RelativeLayout =itemView.findViewById(R.id.container2)
    }

    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }




}