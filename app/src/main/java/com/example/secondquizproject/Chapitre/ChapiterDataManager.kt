package com.example.secondquizproject.Chapitre

import android.content.ContentValues
import com.example.secondquizproject.Chapitre.ChapitreContract.ChapitreTable.COLUMN_ID_CHAPITER
import com.example.secondquizproject.Chapitre.ChapitreContract.ChapitreTable.COLUMN_NOM_CHAPITER
import com.example.secondquizproject.Chapitre.ChapitreContract.ChapitreTable.TABLE_NAM_CHAPITER
import com.example.secondquizproject.DbHelper
object ChapiterDataManager {
    fun InsertInchapiter (myDBHelper: DbHelper, nom: String): Long? {
        val db = myDBHelper.writableDatabase
        val valeu = ContentValues()
        with(valeu){

            put("$COLUMN_NOM_CHAPITER", nom)




        }
        val newRowId = db?.insert( TABLE_NAM_CHAPITER,null,valeu)
        return newRowId


    }
    fun getAllChapitre(dbHelper: DbHelper) :ArrayList<chapitre>?{
        val db = dbHelper.readableDatabase
        val chapitres= ArrayList<chapitre>()

        val cursor = db.query(
          TABLE_NAM_CHAPITER,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor){
            while (moveToNext()) {

                val id=getInt(getColumnIndexOrThrow(COLUMN_ID_CHAPITER))
                val nom=getString(getColumnIndexOrThrow(COLUMN_NOM_CHAPITER))


                chapitres.add(chapitre(id,nom))
            }
        }
        return chapitres
    }

}