package com.example.tutoapp.db;

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class FaceBookDatabase(mContext: Context) : SQLiteOpenHelper(
    mContext,
    DB_NAME,
    null,
    DB_VERSION
)
    {
        override fun onCreate(db: SQLiteDatabase?) {
            // Création des tables
            val createTableUser = """
                CREATE TABLE users(
                    $USER_ID integer PRIMARY KEY,
                    $NAME varchar(50),
                    $EMAIL varchar(100),
                    $PASSWORD varchar(20)
                )
            """.trimIndent()
            db?.execSQL(createTableUser)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            // Supression des anciennes tables et la recrée de nouveau
            db?.execSQL("DROP TABLE IF EXISTS users")
            onCreate(db)
        }

        companion object{
            private val DB_NAME = "facebook_db"
            private val DB_VERSION = 1
            private val DB_TABLE_NAME = "users"
            private val USER_ID = "id"
            private val NAME = "name"
            private val EMAIL = "email"
            private val PASSWORD = "password"
        }
    }
