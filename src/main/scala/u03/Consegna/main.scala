package u03.Consegna

class main {
    
    // --------------- TASK 1 ---------------
    
    object Sequences:
        
        enum Sequence[E]:
            case Cons(head: E, tail: Sequence[E])
            case Nil()
        
        object Sequence:
            
            def sum(l: Sequence[Int]): Int = l match
                case Cons(h, t) => h + sum(t)
                case _ => 0
            
            def map[A, B](l: Sequence[A])(mapper: A => B): Sequence[B] = l match
                case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
                case Nil() => Nil()
            
            def filter[A](l1: Sequence[A])(pred: A => Boolean): Sequence[A] = l1 match
                case Cons(h, t) if pred(h) => Cons(h, filter(t)(pred))
                case Cons(_, t) => filter(t)(pred)
                case Nil() => Nil()
            
            def skip[A](s: Sequence[A])(n: Int): Sequence[A] = s match
                case Cons(h, t) if (n > 0) => skip(t)(n - 1)
                case _ => s
            
            def zip[A, B](first: Sequence[A], second: Sequence[B]): Sequence[(A, B)] = (first, second) match
                case (Cons(h1, t1), Cons(h2, t2)) => Cons((h1, h2), zip(t1, t2))
                case _ => Nil()
            
            def concat[A](s1: Sequence[A], s2: Sequence[A]): Sequence[A] = s1 match
                case Cons(h, t) => Cons(h, concat(t, s2))
                case _ => s2
            
            def reverse[A](s: Sequence[A]): Sequence[A] =
                def _rev(s1: Sequence[A], acc: Sequence[A]): Sequence[A] = s1 match
                    case Cons(h, t) => _rev(t, Cons(h, acc))
                    case _ => acc
                
                _rev(s, Nil())
            
            def flatMap[A, B](s: Sequence[A])(mapper: A => Sequence[B]): Sequence[B] = s match
                case Cons(h, t) => concat(mapper(h), flatMap(t)(mapper))
                case _ => Nil()
            
            // prima variante
            def min(s: Sequence[Int]): Optional[Int] =
                def _min(s: Sequence[Int], n: Int): Int = s match
                    case Cons(h, t) if(h <= n) => _min(t, h)
                    case Cons(h, t) if(n < h) => _min(t, n)
                    case _ => n
                    
                s match
                    case Nil() => Optional.Empty()
                    case _ => Optional.Just(_min(s, Int.MaxValue))
            
            // seconda variante
            def min2(s: Sequence[Int]): Optional[Int] = s match
                case Cons(h, Nil()) => Just(h)
                case Cons(h, t) if h <= orElse(min(t), -1) => Just(h)
                case Cons(h, t) if h > orElse(min(t), -1) => min(t)
                case _ => Empty()
            
            // fatto da solo
            def evenIndices[A](s: Sequence[A]): Sequence[A] = s match
                case Cons(h, t) => Cons(h, t match
                    case Cons(ht, tt) => evenIndices(tt)
                    case _ => Nil())
                case _ => Nil()
            
            // fatto da solo
            def contains[A](s: Sequence[A])(elem: A): Boolean = s match
                case Cons(h, t) if (h == elem) => true
                case Cons(h, t) if (h != elem) => contains(t)(elem)
                case _ => false
        
        end Sequence
    end Sequences
    
    // --------------- TASK 2 ---------------
    
    def getCourseTeacher(sp: Sequence[Person]): Sequence[String] =
        val f = (p: Person) => p match
            case Teacher(_, _) => true
            case _ => false
        
        val m = (p: Person) => p match
            case Teacher(_, c) => c
            case _ => ""
        
        map(filter(sp)(f))(m)
    
    def foldLeft[A, B](s: Sequence[A])(n: B = 0)(op: (B, A) => B): B = s match
        case Cons(h, t) => foldLeft(t)(op(n, h))(op)
        case _ => n
    
    // prima variante
    def totalCoursesTaught(s: Sequence[Person]): Int =
        val courses = getCourseTeacher(s)
        foldLeft(courses)(0)((acc, _) => acc + 1)
        
    // seconda variante, che tiene in considerazione l'esistenza di TeacherPro
    // come mostrato qua sotto
    enum Person:
        case Student(name: String, year: Int)
        case Teacher(name: String, course: String)
        case TeacherPro(name: String, courses: Sequence[String])
        
    def getNumberOfCourses(s: Sequence[Person]): Int =
        val teacher: Person => Boolean = _ match
            case TeacherPro(_, _) => true
            case Teacher(_, _) => true
            case _ => false
        
        val courses: Person => Sequence[String] = _ match
            case TeacherPro(_, c) => c
            case Teacher(_, c) => Cons(c, Nil())
            case _ => Nil()
        
        foldLeft(map(map(filter(s)(teacher))(courses))(_ => 1))(0)(_ + _)
    
    // --------------- TASK 3 ---------------
    
    // le prime 2 funzioni erano gia implementate
    def fill[A](n: Int)(k: A): Stream[A] = n match
        case 0 => Empty()
        case _ => Cons(() => k, () => fill(n - 1)(k))
    
}
