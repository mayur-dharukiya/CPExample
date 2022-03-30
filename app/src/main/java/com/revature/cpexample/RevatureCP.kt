package com.revature.cpexample

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class RevatureCP: ContentProvider() {


    companion object
    {
        const val DATABASE_NAME="UserDB"
        const val DATABASE_VERSION=1
        const val TABLE_NAME="Users"

        const val name="name"
        const val id="id"

        //defining the authority(content URI)

        const val PROVIDER_NAME="com.revature.user.provider"

        //defining content URI

      //  const val URL="http://www.google.com/user"
        const val URL="content://$PROVIDER_NAME/users"

        //parsing the content URI

        val CONTENT_URI=Uri.parse(URL)


        var uriMatcher:UriMatcher?=null

        //create a table

        const val CREATE_DB_TABLE=(

                "CREATE TABLE"+ TABLE_NAME+
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL);")

            const val uriCode = 1

        init {
             //to match the content URI
            uriMatcher= UriMatcher(UriMatcher.NO_MATCH)

            //to access whole table

            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users",
                uriCode

            )

            //access particular row

            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users/*",
                uriCode

            )


        }

    }



    override fun onCreate(): Boolean {

        val context=context

        val dbHelper= context?.let { DatabaseHelper(it) }
        db=dbHelper?.writableDatabase


        return db!=null

//        return if(db!=null)
//        {
//            true
//        }
//        else false

    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val rowID=db!!.insert(TABLE_NAME,"",values)
        if(rowID>0)
        {
            val _uri= ContentUris.withAppendedId(uri,rowID)
            context!!.contentResolver.notifyChange(_uri,null)

            return _uri
        }

        throw SQLiteException("Failed to add record into $uri")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    private var db: SQLiteDatabase?=null

    private class DatabaseHelper
    internal constructor(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
    {
        override fun onCreate(db: SQLiteDatabase?) {

                db?.execSQL(CREATE_DB_TABLE)

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)

        }


    }



}