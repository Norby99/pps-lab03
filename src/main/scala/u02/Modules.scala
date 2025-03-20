package u02

object Modules extends App:
    
    import u03.Sequences.*
    import u03.Sequences.Sequence.*
    
    // An ADT: type + module
    enum Person:
        case Student(name: String, year: Int)
        case Teacher(name: String, course: String)
    
    object Person:
        def name(p: Person): String = p match
            case Student(n, _) => n
            case Teacher(n, _) => n
            
        def getCourseTeacher(sp: Sequence[Person]): Sequence[String] =
            val f = (p: Person) => p match
                case Teacher(_, _) => true
                case _ => false
            
            val m = (p: Person) => p match
                case Teacher(_, c) => c
                case _ => ""
            
            map(filter(sp)(f))(m)
        
        def foldLeft[A, B](s: Sequence[A])(n: B = 0)(op: (B, A) => B): B= s match
            case Cons(h, t) => foldLeft(t)(op(n, h))(op)
            case _ => n
        
        def totalCoursesTaught(s: Sequence[Person]): Int =
            val courses = getCourseTeacher(s)
            foldLeft(courses)(0)((acc, _) => acc + 1)
    
    println(Person.name(Person.Student("mario", 2015)))
    
    import Person.*
    
    println(name(Student("mario", 2015)))
    
    // a method outside the Person module
    def isStudent(p: Person): Boolean = p match
        case Student(_, _) => true
        case _ => false
    
    println(isStudent(Student("mario", 2015)))
end Modules
