package com.example.secondquizproject.Admin

import android.provider.BaseColumns

object AdminContract {
    object AdminTable : BaseColumns {
        const val TABLE_NAM_ADMIN = "admin"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"



        const val SQL_CREATE_TABLE_ADMIN = "CREATE TABLE $TABLE_NAM_ADMIN(" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT" +
                ")"

        const val SQL_DROP_TABLE_ADMIN = "DROP TABLE IF EXISTS $TABLE_NAM_ADMIN"
    }

}