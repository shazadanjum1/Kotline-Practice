package com.kentae.kotlinepractice.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kentae.kotlinepractice.models.ItemsModel

class SQLiteDB(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT," +
                DETAIL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(name : String, detail : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME, name)
        values.put(DETAIL, detail)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }


     fun getAllRecords(): ArrayList<ItemsModel>? {
        val items = ArrayList<ItemsModel>();
        val db = this.readableDatabase

        val cursor =db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {

            val id = cursor.getString(0) // Modify this based on your table schema
            val name = cursor.getString(1) // Modify this based on your table schema
            val detail = cursor.getString(2) // Modify this based on your table schema

            items.add(ItemsModel(id,name, detail));
        }
        cursor.close();
        return items;
    }

    fun updateRow(id: String, name: String, detail: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(NAME, name);
            put(DETAIL, detail);
        }
        val selection = "$ID = ?"
        val selectionArgs = arrayOf(id)
        db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

    fun deleteRow(id: String) {
        val db = this.writableDatabase
        val selection = "$ID = ?"
        val selectionArgs = arrayOf(id)
        db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "ITEM"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "ITEM_TABLE"

        // below is the variable for id column
        val ID = "ID"

        // below is the variable for name column
        val NAME = "NAME"

        // below is the variable for age column
        val DETAIL = "DETAIL"
    }
}
