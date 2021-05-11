package com.example.secondquizproject.Chapitre

import android.provider.BaseColumns

object ChapitreContract {
    object ChapitreTable : BaseColumns {
        const val TABLE_NAM_CHAPITER = "chapitre"
        const val COLUMN_ID_CHAPITER = BaseColumns._ID
        const val COLUMN_NOM_CHAPITER  = "nomchapiter"




        const val SQL_CREATE_TABLE_CHAPITER = "CREATE TABLE $TABLE_NAM_CHAPITER(" +
                "$COLUMN_ID_CHAPITER INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOM_CHAPITER TEXT" +

                ")"

        const val SQL_DROP_TABLE_CHAPITER= "DROP TABLE IF EXISTS $TABLE_NAM_CHAPITER"
    }
}