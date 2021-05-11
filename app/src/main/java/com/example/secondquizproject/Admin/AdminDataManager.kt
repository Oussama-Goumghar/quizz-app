package com.example.secondquizproject.Admin

import android.content.ContentValues
import android.provider.BaseColumns
import com.example.secondquizproject.Admin.AdminContract.AdminTable.COLUMN_PASSWORD
import com.example.secondquizproject.Admin.AdminContract.AdminTable.COLUMN_USERNAME
import com.example.secondquizproject.Admin.AdminContract.AdminTable.TABLE_NAM_ADMIN
import com.example.secondquizproject.DbHelper

object AdminDataManager {
    fun checkAdmin(myDBHelper: DbHelper, username: String, password: String): Boolean {
        val columns = arrayOf(BaseColumns._ID)
        val db = myDBHelper.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(username,password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_NAM_ADMIN, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false

    }
    fun InsertIAdmin(myDBHelper: DbHelper, username: String, password: String): Long? {
        val db = myDBHelper.writableDatabase
        val valeu = ContentValues()
        with(valeu){

            put("$COLUMN_USERNAME", username)
            put("$COLUMN_PASSWORD", password)



        }
        val newRowId = db?.insert( TABLE_NAM_ADMIN,null,valeu)
        return newRowId


    }
}