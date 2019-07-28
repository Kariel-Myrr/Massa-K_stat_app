package com.example.lesson_1.massa_k_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DBName, null, DBVersion) {

    companion object{

        const val DBName = "UsersDB" // Название БД
        const val DBVersion = 1 // Версия БД

        var WeightDataName = "WeightData" //имя таблицы

        var id = "id"
        var Date = "date"
        var ShipperName = "shipper"
        var Reference = "reference"
        var ContainerID = "contid"
        var Tare = "tare"
        var SealID = "sealid"
        var Location = "location"
        var OperatorName = "operator"
        var Notes = "notes"

    }

    private var sqlObj: SQLiteDatabase = this.writableDatabase //Сущность SQLiteDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql1 = "CREATE TABLE IF NOT EXISTS $WeightDataName( $id  INTEGER PRIMARY KEY , $Date TEXT , $ShipperName TEXT , $Reference TEXT , $ContainerID TEXT , $Tare TEXT , $SealID TEXT , $Location TEXT , $OperatorName Text, $Notes TEXT);"
        p0!!.execSQL(sql1)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("Drop table IF EXISTS $WeightDataName")
        onCreate(p0)
    }

    fun addMeasurement(values : ContentValues) = sqlObj.insert(WeightDataName, "", values)



    fun getAllData(): Cursor {

        return sqlObj.rawQuery( "SELECT * FROM $WeightDataName", null)

    }

    fun getData(id : String): Cursor {
        return sqlObj.rawQuery("SELECT * FROM $WeightDataName WHERE id=?", arrayOf(id), null)
    }

    fun updateData(id : String,date : String, shipper : String, reference : String, container_id : String, tare : String, seal_id : String, location : String, operator : String, notes : String) : Boolean? {
        val cv = ContentValues()
        cv.put(id, id)
        cv.put(Date, date)
        cv.put(ShipperName, shipper)
        cv.put(Reference, reference)
        cv.put(ContainerID, container_id)
        cv.put(Tare, tare)
        cv.put(SealID, seal_id)
        cv.put(Location, location)
        cv.put(OperatorName, operator)
        cv.put(Notes, notes)
        sqlObj.update(WeightDataName, cv, "id=?", arrayOf(id))
        return true
    }

    fun deleteData(id : String): Int?{
        return sqlObj.delete(WeightDataName, "id=?", arrayOf(id))
    }

}