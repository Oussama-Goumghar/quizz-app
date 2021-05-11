package com.example.secondquizproject
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.secondquizproject.Question.Question


class myadapter(private val questionList: List<Question>):RecyclerView.Adapter<myadapter.viewHolder>(){
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout , parent , false)
        context=itemView.context
       return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentQuestion =  questionList[position]
        holder.textViewQuestion.text=currentQuestion.question
        holder.radioBtn1.text=currentQuestion.option1
        holder.radioBtn2.text=currentQuestion.option2
        holder.radioBtn3.text=currentQuestion.option3

        holder.radioBtn1.setOnClickListener {
            questionList[position].answerSelected=holder.radioGroup.indexOfChild(holder.radioBtn1)+1
           // Toast.makeText(context,"hadaaaa howaa li ban =="+questionList[position].answerSelected+" dyal question id=="+currentQuestion.question,Toast.LENGTH_SHORT).show()
        }
        holder.radioBtn2.setOnClickListener {
            questionList[position].answerSelected=holder.radioGroup.indexOfChild(holder.radioBtn2)+1
           // Toast.makeText(context,"hadaaaa howaa li ban =="+questionList[position].answerSelected+" dyal question id=="+currentQuestion.question,Toast.LENGTH_SHORT).show()
        }
        holder.radioBtn3.setOnClickListener {
            questionList[position].answerSelected=holder.radioGroup.indexOfChild(holder.radioBtn3)+1
          //  Toast.makeText(context,"hadaaaa howaa li ban =="+questionList[position].answerSelected+" dyal question id=="+currentQuestion.question,Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount() = questionList.size ;

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewQuestion : TextView = itemView.findViewById(R.id.textQuestion)
        var radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)
        var radioBtn1:RadioButton=itemView.findViewById(R.id.radioButton1)
        var radioBtn2:RadioButton=itemView.findViewById(R.id.radioButton2)
        var radioBtn3:RadioButton=itemView.findViewById(R.id.radioButton3)
        }




}