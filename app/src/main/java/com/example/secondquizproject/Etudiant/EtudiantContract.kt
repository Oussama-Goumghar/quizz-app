package com.example.secondquizproject.Etudiant

import android.provider.BaseColumns

object EtudiantContract {
    object EtudiantTable : BaseColumns {
        const val COLUMN_ID_ETUDIANT = BaseColumns._ID
        const val TABLE_NAM_ETUDIANT = "etudiantss"
        const val COLUMN_NOM_ETUDIANT = "nometudaint"
        const val COLUMN_PERNOM_ETUDIANT = "pernometudaint"
        const val COLUMN_USERNAME_ETUDIANT = "usernameetu"
        const val COLUMN_PASSWORD_ETUDIANT = "passwordetu"
        const val COLUMN_SCOURE_ETUDIANT = "scoure"
        const val COLUMN_IMAGE_ETUDIANT = "image"



        const val SQL_CREATE_TABLE_ETUDIANT = "CREATE TABLE $TABLE_NAM_ETUDIANT(" +
                "$COLUMN_ID_ETUDIANT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOM_ETUDIANT TEXT," +
                "$COLUMN_PERNOM_ETUDIANT TEXT," +
                "$COLUMN_USERNAME_ETUDIANT TEXT," +
                "$COLUMN_IMAGE_ETUDIANT BLOB ," +
                "$COLUMN_SCOURE_ETUDIANT INTEGER ," +
                "$COLUMN_PASSWORD_ETUDIANT TEXT" +
                ")"

        const val SQL_DROP_TABLE_ETUDIANT= "DROP TABLE IF EXISTS $TABLE_NAM_ETUDIANT"
    }
}