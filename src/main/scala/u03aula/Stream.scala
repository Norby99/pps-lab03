package u03aula

import u03.Sequences

object Streams extends App:
    ???
/*
    import Sequences.*
    
    enum Stream[E]:
        private case Cons(head: E, tail: Stream[E])
        private case Empty()
    
    object Stream:
        def cons[E](head: E, tail: Stream[E]): Stream[E] =
            lazy val h = head
            lazy val t = tail
            Cons(h, t)
        def empty[E](): Stream[E] = Empty[E]()
        extension [E](str: Stream[E]) def take(n: Int): Stream[E] = str match
            case Cons(fh, ft) if n > 0 => cons(fh(), ft().take(n - 1))
            case _ => empty()
        def iterate[E](first: E)(next: E => E): Stream[E] =
            cons(first, iterate(next(first))(next))
            case _ => empty()
        def toList[E](str: Stream[E]): Sequence[E] = str match
            case Cons(fh, ft) => Sequqnce.Cons(fh(), ft().toList())
            case _ => Sequence.Nul()
        
        @main def tryStream =
            import Streams.*
            import Stream.*
            
            //val str: Stream[Int] = cons(10, cons(20, cons(39, empty())))
            val str: Stream[Int] = iterate(0)(i => i + 1)
            println(iterate(0)(i => i + 1).take(20).toList())   // [10, 20]
*/
