import fplibrary.{Description, Monad}

object Description {
  def create[A](a: =>A): Description[A] =
    () => a

  implicit val M: Monad[Description] = new Monad[Description] {
    final override def flatMap[A, B](ca: Description[A])(acb: A => Description[B]): Description[B] = {
      val a = ca.apply()
      val db = acb(a)

      db
    }
  }
}
