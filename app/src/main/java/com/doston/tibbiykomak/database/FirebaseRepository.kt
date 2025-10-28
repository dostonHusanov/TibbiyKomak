package com.doston.tibbiykomak.database

import android.util.Log
import com.doston.tibbiykomak.data.MainData
import com.doston.tibbiykomak.data.Pill
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val database: FirebaseDatabase
    private val illnessRef: com.google.firebase.database.DatabaseReference

    companion object {
        private const val TAG = "FirebaseRepository"
    }

    init {
        Log.d(TAG, "FirebaseRepository initialized")

        // If your database is not in the default region, specify the URL:
        // database = FirebaseDatabase.getInstance("https://your-database-name.firebaseio.com/")
        database = FirebaseDatabase.getInstance("https://tibbiyko-mak-default-rtdb.asia-southeast1.firebasedatabase.app")

        try {
            database.setPersistenceEnabled(true)
        } catch (e: Exception) {
            Log.w(TAG, "Persistence already enabled or failed", e)
        }

        illnessRef = database.getReference("illnesses")
        Log.d(TAG, "Database reference created for path: illnesses")

        // Test connection
        testDatabaseConnection()
    }

    private fun testDatabaseConnection() {
        illnessRef.get().addOnSuccessListener { snapshot ->
            Log.d(TAG, "✅ Firebase connected successfully!")
            Log.d(TAG, "Database has data: ${snapshot.exists()}")
            Log.d(TAG, "Children count: ${snapshot.childrenCount}")
            snapshot.children.forEach { child ->
                Log.d(TAG, "Category key: ${child.key}")
            }
        }.addOnFailureListener { exception ->
            Log.e(TAG, "❌ Firebase connection FAILED", exception)
        }
    }

    // Get all illnesses by category as Flow for real-time updates
    fun getIllnessesByCategory(categoryId: Int): Flow<List<MainData>> = callbackFlow {
        Log.d(TAG, "Starting to fetch illnesses for category: $categoryId")

        val categoryRef = illnessRef.child(categoryId.toString())

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange called. Snapshot exists: ${snapshot.exists()}")
                Log.d(TAG, "Snapshot children count: ${snapshot.childrenCount}")

                val illnesses = mutableListOf<MainData>()

                snapshot.children.forEach { illnessSnapshot ->
                    try {
                        Log.d(TAG, "Processing illness: ${illnessSnapshot.key}")

                        val category = illnessSnapshot.child("category").getValue(String::class.java) ?: ""
                        val problem = illnessSnapshot.child("problem").getValue(String::class.java) ?: ""
                        val description = illnessSnapshot.child("description").getValue(String::class.java) ?: ""
                        val imageResName = illnessSnapshot.child("image").getValue(String::class.java) ?: "brain"

                        Log.d(TAG, "Illness data - Category: $category, Problem: $problem")

                        val pills = mutableListOf<Pill>()
                        illnessSnapshot.child("recommendedPills").children.forEach { pillSnapshot ->
                            val pill = Pill(
                                name = pillSnapshot.child("name").getValue(String::class.java) ?: "",
                                description = pillSnapshot.child("description").getValue(String::class.java) ?: "",
                                usage = pillSnapshot.child("usage").getValue(String::class.java) ?: ""
                            )
                            pills.add(pill)
                        }

                        val homeAdvice = mutableListOf<String>()
                        illnessSnapshot.child("homeAdvice").children.forEach { adviceSnapshot ->
                            adviceSnapshot.getValue(String::class.java)?.let { homeAdvice.add(it) }
                        }

                        // Convert image resource name to resource ID
                        val imageResId = getImageResourceId(imageResName)

                        illnesses.add(
                            MainData(
                                category = category,
                                problem = problem,
                                description = description,
                                image = imageResId,
                                recommendedPills = pills,
                                homeAdvice = homeAdvice
                            )
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parsing illness data", e)
                        e.printStackTrace()
                    }
                }

                Log.d(TAG, "Total illnesses parsed: ${illnesses.size}")
                trySend(illnesses).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Firebase query cancelled: ${error.message}")
                close(error.toException())
            }
        }

        categoryRef.addValueEventListener(listener)

        awaitClose {
            Log.d(TAG, "Removing listener for category: $categoryId")
            categoryRef.removeEventListener(listener)
        }
    }

    // Get ALL illnesses from ALL categories as Flow for real-time updates
    fun getAllCategoriesFlow(): Flow<List<MainData>> = callbackFlow {
        Log.d(TAG, "Starting to fetch ALL illnesses from ALL categories")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onDataChange called for all categories. Snapshot exists: ${snapshot.exists()}")

                val allIllnesses = mutableListOf<MainData>()

                snapshot.children.forEach { categorySnapshot ->
                    val categoryId = categorySnapshot.key
                    Log.d(TAG, "Processing category: $categoryId with ${categorySnapshot.childrenCount} illnesses")

                    categorySnapshot.children.forEach { illnessSnapshot ->
                        try {
                            val category = illnessSnapshot.child("category").getValue(String::class.java) ?: ""
                            val problem = illnessSnapshot.child("problem").getValue(String::class.java) ?: ""
                            val description = illnessSnapshot.child("description").getValue(String::class.java) ?: ""
                            val imageResName = illnessSnapshot.child("image").getValue(String::class.java) ?: "brain"

                            val pills = mutableListOf<Pill>()
                            illnessSnapshot.child("recommendedPills").children.forEach { pillSnapshot ->
                                val pill = Pill(
                                    name = pillSnapshot.child("name").getValue(String::class.java) ?: "",
                                    description = pillSnapshot.child("description").getValue(String::class.java) ?: "",
                                    usage = pillSnapshot.child("usage").getValue(String::class.java) ?: ""
                                )
                                pills.add(pill)
                            }

                            val homeAdvice = mutableListOf<String>()
                            illnessSnapshot.child("homeAdvice").children.forEach { adviceSnapshot ->
                                adviceSnapshot.getValue(String::class.java)?.let { homeAdvice.add(it) }
                            }

                            val imageResId = getImageResourceId(imageResName)

                            allIllnesses.add(
                                MainData(
                                    category = category,
                                    problem = problem,
                                    description = description,
                                    image = imageResId,
                                    recommendedPills = pills,
                                    homeAdvice = homeAdvice
                                )
                            )
                        } catch (e: Exception) {
                            Log.e(TAG, "Error parsing illness data", e)
                        }
                    }
                }

                Log.d(TAG, "Total illnesses parsed from all categories: ${allIllnesses.size}")
                trySend(allIllnesses).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Firebase query cancelled: ${error.message}")
                close(error.toException())
            }
        }

        illnessRef.addValueEventListener(listener)

        awaitClose {
            Log.d(TAG, "Removing listener for all categories")
            illnessRef.removeEventListener(listener)
        }
    }

    // Get all illnesses (one-time fetch)
    suspend fun getAllIllnesses(): Map<Int, List<MainData>> {
        return try {
            val snapshot = illnessRef.get().await()
            Log.d(TAG, "getAllIllnesses - Snapshot exists: ${snapshot.exists()}")

            val result = mutableMapOf<Int, List<MainData>>()

            snapshot.children.forEach { categorySnapshot ->
                val categoryId = categorySnapshot.key?.toIntOrNull() ?: return@forEach
                val illnesses = mutableListOf<MainData>()

                categorySnapshot.children.forEach { illnessSnapshot ->
                    try {
                        val category = illnessSnapshot.child("category").getValue(String::class.java) ?: ""
                        val problem = illnessSnapshot.child("problem").getValue(String::class.java) ?: ""
                        val description = illnessSnapshot.child("description").getValue(String::class.java) ?: ""
                        val imageResName = illnessSnapshot.child("image").getValue(String::class.java) ?: "brain"

                        val pills = mutableListOf<Pill>()
                        illnessSnapshot.child("recommendedPills").children.forEach { pillSnapshot ->
                            val pill = Pill(
                                name = pillSnapshot.child("name").getValue(String::class.java) ?: "",
                                description = pillSnapshot.child("description").getValue(String::class.java) ?: "",
                                usage = pillSnapshot.child("usage").getValue(String::class.java) ?: ""
                            )
                            pills.add(pill)
                        }

                        val homeAdvice = mutableListOf<String>()
                        illnessSnapshot.child("homeAdvice").children.forEach { adviceSnapshot ->
                            adviceSnapshot.getValue(String::class.java)?.let { homeAdvice.add(it) }
                        }

                        val imageResId = getImageResourceId(imageResName)

                        illnesses.add(
                            MainData(
                                category = category,
                                problem = problem,
                                description = description,
                                image = imageResId,
                                recommendedPills = pills,
                                homeAdvice = homeAdvice
                            )
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parsing illness in getAllIllnesses", e)
                        e.printStackTrace()
                    }
                }

                result[categoryId] = illnesses
            }

            result
        } catch (e: Exception) {
            Log.e(TAG, "Error in getAllIllnesses", e)
            e.printStackTrace()
            emptyMap()
        }
    }

    // Helper function to map image names to resource IDs
    private fun getImageResourceId(imageName: String): Int {
        return when (imageName) {
            "brain" -> com.doston.tibbiykomak.R.drawable.brain
            "fever" -> com.doston.tibbiykomak.R.drawable.fever
            "stomach" -> com.doston.tibbiykomak.R.drawable.stomach
            "apatite" -> com.doston.tibbiykomak.R.drawable.apatite
            "allergy" -> com.doston.tibbiykomak.R.drawable.allergy
            "sore" -> com.doston.tibbiykomak.R.drawable.sore
            "sleepless" -> com.doston.tibbiykomak.R.drawable.sleepless
            "nose" -> com.doston.tibbiykomak.R.drawable.nose
            "blood" -> com.doston.tibbiykomak.R.drawable.blood
            "metobolib" -> com.doston.tibbiykomak.R.drawable.metobolib
            "bone" -> com.doston.tibbiykomak.R.drawable.bone
            else -> com.doston.tibbiykomak.R.drawable.brain
        }
    }
}