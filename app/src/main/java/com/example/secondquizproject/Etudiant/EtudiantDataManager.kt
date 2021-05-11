package com.example.secondquizproject.Etudiant

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_IMAGE_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_NOM_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_PASSWORD_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_PERNOM_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_USERNAME_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.TABLE_NAM_ETUDIANT
import com.example.secondquizproject.DbHelper
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_ID_ETUDIANT
import com.example.secondquizproject.Etudiant.EtudiantContract.EtudiantTable.COLUMN_SCOURE_ETUDIANT

object EtudiantDataManager {
    fun Insertetudaint(
        myDBHelper: DbHelper,
        nom: String,
        pernom: String,
        username: String,
        password: String,
        image: ByteArray,
        scoure: Int
    ): Long? {
        val db = myDBHelper.writableDatabase
        val valeu = ContentValues()
        with(valeu) {
            put("$COLUMN_NOM_ETUDIANT", nom)
            put("$COLUMN_PERNOM_ETUDIANT", pernom)
            put("$COLUMN_USERNAME_ETUDIANT", username)
            put("$COLUMN_PASSWORD_ETUDIANT", password)
            put("$COLUMN_IMAGE_ETUDIANT", image)
            put("$COLUMN_SCOURE_ETUDIANT", scoure)


        }
        val newRowId = db?.insert(TABLE_NAM_ETUDIANT, null, valeu)
        return newRowId


    }

    fun checkEtudaint(myDBHelper: DbHelper, username: String, password: String): ArrayList<Etudiant> {
        val columns = arrayOf(
            COLUMN_ID_ETUDIANT,
            COLUMN_NOM_ETUDIANT,
            COLUMN_PERNOM_ETUDIANT,
            COLUMN_USERNAME_ETUDIANT,
            COLUMN_IMAGE_ETUDIANT,
            COLUMN_PASSWORD_ETUDIANT,
            COLUMN_SCOURE_ETUDIANT,

        )
        val db = myDBHelper.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USERNAME_ETUDIANT = ? AND $COLUMN_PASSWORD_ETUDIANT = ?"
        // selection arguments
        val selectionArgs = arrayOf(username, password)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val etudiants= ArrayList<Etudiant>()
        val cursor = db.query(
            TABLE_NAM_ETUDIANT, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(columns[0]))
            val nom = cursor.getString(cursor.getColumnIndex(columns[1]))
            val pernom = cursor.getString(cursor.getColumnIndex(columns[2]))
            val username = cursor.getString(cursor.getColumnIndex(columns[3]))
            val image = cursor.getBlob(cursor.getColumnIndex(columns[4]))
            val pass = cursor.getString(cursor.getColumnIndex(columns[5]))
            val scoure = cursor.getInt(cursor.getColumnIndex(columns[6]))
            etudiants.add(Etudiant(id, username, nom, pernom, pass, image,scoure))
        }

return  etudiants
    }

    fun getAllEtudaint(dbHelper: DbHelper) :ArrayList<Etudiant>?{
        val db = dbHelper.readableDatabase
        val etudiants= ArrayList<Etudiant>()

        val cursor = db.query(
            TABLE_NAM_ETUDIANT,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            while (moveToNext()) {

                val nom=getString(getColumnIndexOrThrow(COLUMN_NOM_ETUDIANT))
                val pernom=getString(getColumnIndexOrThrow(COLUMN_PERNOM_ETUDIANT))
                val username=getString(getColumnIndexOrThrow(COLUMN_USERNAME_ETUDIANT))
                val passw=getString(getColumnIndexOrThrow(COLUMN_PASSWORD_ETUDIANT))
                val scoure=getInt(getColumnIndexOrThrow(COLUMN_SCOURE_ETUDIANT))
                val image: ByteArray = getBlob(getColumnIndexOrThrow(COLUMN_IMAGE_ETUDIANT))

                etudiants.add(Etudiant(null,username,nom,pernom,passw,image,scoure))
            }
        }
        return etudiants
    }
    fun  updateinfo(myDBHelper: DbHelper, id : String,scoure:Int){
        val dbr = myDBHelper.readableDatabase
        val db = myDBHelper.writableDatabase
        val columns = arrayOf(COLUMN_SCOURE_ETUDIANT)

        // selection criteria
        val selections = "$COLUMN_ID_ETUDIANT  = ? "
        // selection arguments
        val selectionArgs = arrayOf(id)
        val cursor = dbr.query(
            TABLE_NAM_ETUDIANT, //Table to query
            columns, //columns to return
            selections, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order
        if (cursor.moveToFirst()) {
            val  scoures = cursor.getInt(cursor.getColumnIndex(columns[0]))
            if(scoure>scoures){
                val values = ContentValues().apply {

                    put("$COLUMN_SCOURE_ETUDIANT",scoure)

                }
                val selection = "$COLUMN_ID_ETUDIANT LIKE ?"
                val selectioArges = arrayOf(id)
                val count = db.update(
                    TABLE_NAM_ETUDIANT,
                    values,
                    selection,
                    selectioArges
                )
            }


    }
}
    fun GetEtudaintById(myDBHelper: DbHelper, id: String): ArrayList<Etudiant> {
        val columns = arrayOf(
            COLUMN_ID_ETUDIANT,
            COLUMN_NOM_ETUDIANT,
            COLUMN_PERNOM_ETUDIANT,
            COLUMN_USERNAME_ETUDIANT,
            COLUMN_IMAGE_ETUDIANT,
            COLUMN_PASSWORD_ETUDIANT,
            COLUMN_SCOURE_ETUDIANT,

            )
        val db = myDBHelper.readableDatabase
        // selection criteria
        val selection = "$COLUMN_ID_ETUDIANT = ? "
        // selection arguments
        val selectionArgs = arrayOf(id)
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val etudiants= ArrayList<Etudiant>()
        val cursor = db.query(
            TABLE_NAM_ETUDIANT, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(columns[0]))
            val nom = cursor.getString(cursor.getColumnIndex(columns[1]))
            val pernom = cursor.getString(cursor.getColumnIndex(columns[2]))
            val username = cursor.getString(cursor.getColumnIndex(columns[3]))
            val image = cursor.getBlob(cursor.getColumnIndex(columns[4]))
            val pass = cursor.getString(cursor.getColumnIndex(columns[5]))
            val scoure = cursor.getInt(cursor.getColumnIndex(columns[6]))
            etudiants.add(Etudiant(id, username, nom, pernom, pass, image,scoure))
        }

        return  etudiants
    }

}