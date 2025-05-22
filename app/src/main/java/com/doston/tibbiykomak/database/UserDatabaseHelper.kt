package com.doston.tibbiykomak.database


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.doston.tibbiykomak.data.User

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "user_database", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, age INTEGER, phoneNumber TEXT)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("surname", user.surname)
            put("age", user.age)
            put("phoneNumber", user.phoneNumber)
        }
        db.insert("user", null, values)
    }

    fun getUser(): User? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM user ORDER BY id DESC LIMIT 1", null)
        return if (cursor.moveToFirst()) {
            User(
                id = cursor.getInt(0),
                name = cursor.getString(1),
                surname = cursor.getString(2),
                age = cursor.getInt(3),
                phoneNumber = cursor.getString(4)
            )
        } else null.also {
            cursor.close()
        }
    }
}
