package com.elnemr.roomdbmultipletables.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.elnemr.roomdbmultipletables.db.entities.Director
import com.elnemr.roomdbmultipletables.db.entities.School
import com.elnemr.roomdbmultipletables.db.entities.Student
import com.elnemr.roomdbmultipletables.db.entities.Subject
import com.elnemr.roomdbmultipletables.db.entities.relations.*


@Dao
interface SchoolDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    // join query - Problem: database could change during the join query
    @Transaction // for solving threading issues regarding the join query
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolWithDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndStudentsWithSchoolName(schoolName: String): List<SchoolWithStudent>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudent>

    @Transaction
    @Query("SELECT * FROM student WHERE schoolName = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubject>
}