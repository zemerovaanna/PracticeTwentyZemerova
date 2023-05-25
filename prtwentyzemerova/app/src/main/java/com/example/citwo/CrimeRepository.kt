package com.example.citwo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.citwo.database.CrimeDatabase
import java.util.UUID

private const val DBName = "dataBase"

class CrimeRepository private
constructor(context: Context) {

    companion object {
        private var INSTANCE: CrimeRepository? =
            null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw
            IllegalStateException("CrimeRepository must be initialized")
        }

    }
    private val database : CrimeDatabase = Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DBName).build()
    private val crimeDao = database.crimeDao()
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)
}