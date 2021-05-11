package com.example.secondquizproject

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondquizproject.Question.Question

class ShowAdapter(private val questionList: List<Question>): RecyclerView.Adapter<ShowAdapter.viewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAdapter.viewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemshow , parent , false)
        context=itemView.context
        return ShowAdapter.viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowAdapter.viewHolder, position: Int) {
        val currentQuestion =  questionList[position]
        holder.textViewQuestion.text=currentQuestion.question
        holder.radioBtn1.text=currentQuestion.option1
        holder.radioBtn2.text=currentQuestion.option2
        holder.radioBtn3.text=currentQuestion.option3
        if(currentQuestion.answer ==1){
            holder.radioBtn1.setBackgroundResource(R.drawable.selectedradio)
        }
         if(currentQuestion.answer ==2){
            holder.radioBtn2.setBackgroundResource(R.drawable.selectedradio)
        }
         if(currentQuestion.answer ==3) {
            holder.radioBtn3.setBackgroundResource(R.drawable.selectedradio)

        }

        if (currentQuestion.answer!==currentQuestion.answerSelected){

            if(currentQuestion.answerSelected ==1){
                holder.radioBtn1.setBackgroundResource(R.drawable.fauxreponse)
            }
             if(currentQuestion.answerSelected ==2){
                holder.radioBtn2.setBackgroundResource(R.drawable.fauxreponse)

            }
            if(currentQuestion.answerSelected ==3) {
                holder.radioBtn3.setBackgroundResource(R.drawable.fauxreponse) }

        }

 
    }

    override fun getItemCount()= questionList.size ;

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewQuestion : TextView = itemView.findViewById(R.id.textQuestion)
        var radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)
        var radioBtn1: RadioButton =itemView.findViewById(R.id.radioButton1)
        var radioBtn2: RadioButton =itemView.findViewById(R.id.radioButton2)
        var radioBtn3: RadioButton =itemView.findViewById(R.id.radioButton3)

    }


}