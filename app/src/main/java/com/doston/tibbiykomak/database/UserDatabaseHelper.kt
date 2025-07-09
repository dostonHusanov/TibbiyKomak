package com.doston.tibbiykomak.database


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.doston.tibbiykomak.data.Content.DB_NAME
import com.doston.tibbiykomak.data.Content.DB_VERSION
import com.doston.tibbiykomak.data.Content.REMINDER_TABLE
import com.doston.tibbiykomak.data.Content.R_DAY
import com.doston.tibbiykomak.data.Content.R_DESC
import com.doston.tibbiykomak.data.Content.R_ID
import com.doston.tibbiykomak.data.Content.R_NAME
import com.doston.tibbiykomak.data.Content.R_TIMES
import com.doston.tibbiykomak.data.Content.USER_AGE
import com.doston.tibbiykomak.data.Content.USER_ID
import com.doston.tibbiykomak.data.Content.USER_NAME
import com.doston.tibbiykomak.data.Content.USER_PHONE_NUMBER
import com.doston.tibbiykomak.data.Content.USER_SURNAME
import com.doston.tibbiykomak.data.Content.USER_TABLE
import com.doston.tibbiykomak.data.ReminderData
import com.doston.tibbiykomak.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $USER_TABLE ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_NAME TEXT, $USER_SURNAME TEXT, $USER_AGE INTEGER, $USER_PHONE_NUMBER TEXT)"
        )
        db.execSQL("CREATE TABLE $REMINDER_TABLE($R_ID INTEGER PRIMARY KEY AUTOINCREMENT, $R_NAME TEXT, $R_DESC TEXT, $R_DAY TEXT, $R_TIMES TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        onCreate(db)
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(USER_NAME, user.name)
            put(USER_SURNAME, user.surname)
            put(USER_AGE, user.age)
            put(USER_PHONE_NUMBER, user.phoneNumber)
        }
        db.insert(USER_TABLE, null, values)
    }

    fun getUser(): User? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $USER_TABLE ORDER BY $USER_ID DESC LIMIT 1", null)
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

    fun insertPill(data: ReminderData) {
        val db = writableDatabase
        val gson = Gson()
        val timesJson = gson.toJson(data.times)

        val values = ContentValues().apply {
            put(R_NAME, data.name)
            put(R_DESC, data.desc)
            put(R_DAY, data.day)
            put(R_TIMES, timesJson)
        }
        db.insert(REMINDER_TABLE, null, values)

    }

    fun getAllPills(): List<ReminderData> {
        val db = readableDatabase
        val gson = Gson()
        val pillList = mutableListOf<ReminderData>()

        val cursor = db.rawQuery("SELECT * FROM $REMINDER_TABLE", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(R_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(R_NAME))
                val desc = cursor.getString(cursor.getColumnIndexOrThrow(R_DESC))
                val day = cursor.getString(cursor.getColumnIndexOrThrow(R_DAY))
                val timesJson = cursor.getString(cursor.getColumnIndexOrThrow(R_TIMES))

                val times: List<String> =
                    gson.fromJson(timesJson, object : TypeToken<List<String>>() {}.type)

                val pill = ReminderData(
                    id = id,
                    name = name,
                    desc = desc,
                    day = day,
                    times = times
                )

                pillList.add(pill)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return pillList
    }
    fun editPill(pill: ReminderData) {
        val db = writableDatabase
        val gson = Gson()
        val timesJson = gson.toJson(pill.times)

        val values = ContentValues().apply {
            put(R_NAME, pill.name)
            put(R_DESC, pill.desc)
            put(R_DAY, pill.day)
            put(R_TIMES, timesJson)
        }

        db.update(
            REMINDER_TABLE,
            values,
            "$R_ID = ?",
            arrayOf(pill.id.toString())
        )
    }

    fun deletePill(pillId: Int) {
        val db = writableDatabase
        db.delete(
            REMINDER_TABLE,
            "$R_ID = ?",
            arrayOf(pillId.toString())
        )
    }

}
