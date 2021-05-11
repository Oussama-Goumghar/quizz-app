package com.example.secondquizproject.Question

import android.provider.BaseColumns
import com.example.secondquizproject.Chapitre.ChapitreContract

object QuestionContract {
    object QuestionsTable : BaseColumns {
        const val TABLE_NAME = "question"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTION1 = "option1"
        const val COLUMN_OPTION2 = "option2"
        const val COLUMN_OPTION3 = "option3"
        const val COLUMN_ANSWER = "answer"
        const val COLUMN_ANSWER_SELECTED = "answerSelected"
        const val COLUMN_CHAPITRE_ID = "chapitre_id"

        const val SQL_CREATE_TABLE = "CREATE TABLE $TABLE_NAME(" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_QUESTION TEXT," +
                "$COLUMN_OPTION1 TEXT," +
                "$COLUMN_OPTION2 TEXT," +
                "$COLUMN_OPTION3 TEXT," +
                "$COLUMN_ANSWER INTEGER," +
                "$COLUMN_ANSWER_SELECTED INTEGER," +
                "$COLUMN_CHAPITRE_ID INTEGER, " +
                "FOREIGN KEY($COLUMN_CHAPITRE_ID) REFERENCES " + ChapitreContract.ChapitreTable.TABLE_NAM_CHAPITER+"("+ChapitreContract.ChapitreTable.COLUMN_ID_CHAPITER + ")" + "ON DELETE CASCADE" +
                ")"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }



}