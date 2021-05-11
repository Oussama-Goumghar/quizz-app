package com.example.secondquizproject.Question

import android.content.ContentValues
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_ANSWER
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_OPTION1
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_OPTION2
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_OPTION3
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_QUESTION
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.TABLE_NAME
import com.example.secondquizproject.DbHelper
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.COLUMN_CHAPITRE_ID

object QuestionDataManager {

    fun getAllQuestions(dbHelper: DbHelper) :ArrayList<Question>?{
        val db = dbHelper.readableDatabase
        val questions= ArrayList<Question>()

        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            while (moveToNext()) {

              val question=getString(getColumnIndexOrThrow(COLUMN_QUESTION))
              val option1=getString(getColumnIndexOrThrow(COLUMN_OPTION1))
              val option2=getString(getColumnIndexOrThrow(COLUMN_OPTION2))
              val option3=getString(getColumnIndexOrThrow(COLUMN_OPTION3))
              val answer=getInt(getColumnIndexOrThrow(COLUMN_ANSWER))
              questions.add(Question(question,option1,option2,option3,answer,null))
            }
        }
        return questions
    }
    fun InsertQuestion (myDBHelper: DbHelper, question: String, option1:String, option2: String, option3: String, answer:Int,chapitreId:Int): Long? {
        val db = myDBHelper.writableDatabase
        val valeu = ContentValues()
        with(valeu){
            put("$COLUMN_QUESTION ", question)
            put("$COLUMN_OPTION1", option1)
            put("$COLUMN_OPTION2", option2)
            put("$COLUMN_OPTION3", option3)
            put("$COLUMN_ANSWER", answer)
            put("$COLUMN_CHAPITRE_ID ", chapitreId)






        }
        val newRowId = db?.insert(TABLE_NAME ,null,valeu)
        return newRowId


    }
    fun getBychapitre(dbHelper: DbHelper, id: String) :ArrayList<Question>?{
        val db = dbHelper.readableDatabase
        val questions= ArrayList<Question>()
        val selection = "$COLUMN_CHAPITRE_ID = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
            TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor){
            while (moveToNext()) {

                val question=getString(getColumnIndexOrThrow(COLUMN_QUESTION))
                val option1=getString(getColumnIndexOrThrow(COLUMN_OPTION1))
                val option2=getString(getColumnIndexOrThrow(COLUMN_OPTION2))
                val option3=getString(getColumnIndexOrThrow(COLUMN_OPTION3))
                val answer=getInt(getColumnIndexOrThrow(COLUMN_ANSWER))
                questions.add(Question(question,option1,option2,option3,answer,null))
            }
        }
        return questions
    }






}