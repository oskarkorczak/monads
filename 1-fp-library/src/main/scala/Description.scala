import fplibrary.Monad

final case class IO[+A](unsafeRun: () => A) extends AnyVal

object IO {

  def create[A](a: =>A): IO[A] =
    IO(() => a)

  implicit val M: Monad[IO] = new Monad[IO] {
    final override def flatMap[A, B](ca: IO[A])(acb: A => IO[B]): IO[B] = IO.create {
      val a: A = ca.unsafeRun()
      val db: IO[B] = acb(a)
      val b: B = db.unsafeRun()

      b
    }
  }
}
