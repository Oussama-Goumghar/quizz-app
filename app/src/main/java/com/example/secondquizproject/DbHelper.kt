package com.example.secondquizproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.secondquizproject.Admin.AdminContract.AdminTable.SQL_CREATE_TABLE_ADMIN
import com.example.secondquizproject.Admin.AdminContract.AdminTable.SQL_DROP_TABLE_ADMIN
import com.example.secondquizproject.Chapitre.ChapitreContract.ChapitreTable.SQL_CREATE_TABLE_CHAPITER
import com.example.secondquizproject.Chapitre.ChapitreContract.ChapitreTable.SQL_DROP_TABLE_CHAPITER
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.SQL_CREATE_TABLE_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.SQL_DROP_TABLE_ETUDIANT
import com.example.secondquizproject.Question.Question
import com.example.secondquizproject.Question.QuestionContract
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.SQL_CREATE_TABLE
import com.example.secondquizproject.Question.QuestionContract.QuestionsTable.SQL_DROP_TABLE
class DbHelper(context: Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_CHAPITER)
        db?.execSQL(SQL_CREATE_TABLE)
        db?.execSQL(SQL_CREATE_TABLE_ADMIN)
        db?.execSQL(SQL_CREATE_TABLE_ETUDIANT)

       // fillQuestionsTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_TABLE)
        db?.execSQL(SQL_DROP_TABLE_ADMIN)
        db?.execSQL(SQL_DROP_TABLE_ETUDIANT)
        db?.execSQL(SQL_DROP_TABLE_CHAPITER)
        onCreate(db)
       // fillQuestionsTable(db)
    }

    companion object{
        const val DATABASE_NAME = "quizProject1.db"
        const val DATABASE_VERSION=3
    }

//    private fun fillQuestionsTable(db: SQLiteDatabase?) {
//        val q1 = Question("A is correct", "A", "B", "C", 1)
//        addQuestion(q1,db)
//        val q2 = Question("B is correct", "A", "B", "C", 2)
//        addQuestion(q2,db)
//        val q3 = Question("C is correct", "A", "B", "C", 3)
//        addQuestion(q3,db)
//        val q4 = Question("A is correct again", "A", "B", "C", 1)
//        addQuestion(q4,db)
//        val q5 = Question("B is correct again", "A", "B", "C", 2)
//        addQuestion(q5,db)
//    }




    private fun addQuestion(question: Question, db: SQLiteDatabase?) {
        val cv = ContentValues()
        cv.put(QuestionContract.QuestionsTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionContract.QuestionsTable.COLUMN_OPTION1, question.option1)
        cv.put(QuestionContract.QuestionsTable.COLUMN_OPTION2, question.option2)
        cv.put(QuestionContract.QuestionsTable.COLUMN_OPTION3, question.option3)
        cv.put(QuestionContract.QuestionsTable.COLUMN_ANSWER, question.answer)
        db?.insert(QuestionContract.QuestionsTable.TABLE_NAME, null, cv)
    }


}