package u03.Consegna

class test {
    
    // --------------- TASK 2 ---------------
    
    val Viroli = Teacher("Viroli", "PPS")
    val Ricci = Teacher("Ricci", "PCD")
    val Moreno = Teacher("Moreno", "HPC")
    val Viroli2 = Teacher("Viroli", "OOP")
    val Stud1 = Student("Marco", 15)
    val Stud2 = Student("Angelo", 63)
    val Stud3 = Student("Matteo", 25)
    
    val everyone = Cons(Viroli, Cons(Stud1, Cons(Ricci, Cons(Stud2, Cons(Moreno, Cons(Stud3, Cons(Viroli2, Nil())))))))
    val teachers = Cons(Viroli, Cons(Ricci, Cons(Moreno, Cons(Viroli2, Nil()))))
    val students = Cons(Stud1, Cons(Stud2, Cons(Stud3, Nil())))
    val onlyViroliAllOverAgain = Cons(Viroli, Cons(Viroli, Cons(Viroli, Cons(Viroli, Nil()))))
    
    @Test def testCourseTeacher() =
        assertEquals(Cons("PPS", Cons("PCD", Cons("HPC", Cons("OOP", Nil())))), getCourseTeacher(everyone))
        assertEquals(Cons("PPS", Cons("PCD", Cons("HPC", Cons("OOP", Nil())))), getCourseTeacher(teachers))
        assertEquals(Nil(), getCourseTeacher(students))
        assertEquals(Nil(), Nil())
    
    @Test def testFoldLeft() =
        val list = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))
        assertEquals(16, foldLeft(list)(0)(_ + _))
        assertEquals(-16, foldLeft(list)(0)(_ - _))
    
    @Test def testTotalCoursesTaught() =
        assertEquals(4, totalCoursesTaught(everyone))
        assertEquals(4, totalCoursesTaught(onlyViroliAllOverAgain))
    
    // --------------- TASK 3 ---------------
    
    @Test def testFill(): Unit =
        assertEquals(Stream.toList(Stream.fill(3)("a")), Cons("a", Cons("a", Cons("a", Nil()))))
    
}
