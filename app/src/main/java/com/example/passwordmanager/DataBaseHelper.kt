package com.example.passwordmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASENAME = "DATABASE"
const val TABLENAME = "Data"
const val COL_ID = "id"
const val COL_TEXT = "text"
const val COL_USERNAME = "username"
const val COL_PASSWORD = "password"

class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLENAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_TEXT VARCHAR(250),$COL_USERNAME VARCHAR(250),$COL_PASSWORD VARCHAR(250))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // no Upgrade in APP =D
    }

    fun insertData(datainsert: Datainsert) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TEXT, datainsert.text)
        contentValues.put(COL_USERNAME, datainsert.username)
        contentValues.put(COL_PASSWORD, datainsert.password)

        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<Datainsert> {
        val list: MutableList<Datainsert> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLENAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                val datainsert = Datainsert(null,null,null)
                datainsert.text = result.getString(result.getColumnIndex(COL_TEXT))
                datainsert.username = result.getString(result.getColumnIndex(COL_USERNAME))
                datainsert.password = result.getString(result.getColumnIndex(COL_PASSWORD))
                list.add(datainsert)
            }while (result.moveToNext())
        }
        result.close()
        return list
    }

}