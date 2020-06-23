package fplibrary

object PointFree {

  // goal is to have: val ac = compose(ab, bc)
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    val b = ab(a)
    val c = bc(b)

    c
  }

  def composeKleisli[A, B, C](adb: A => Description[B], bdc: B => Description[C]): A => Description[C] = a => {
    val db: Description[B] = adb(a)
    val b: B = db.apply()
    val dc: Description[C] = bdc(b)

    dc
  }

  def composeKleisli2[A, B, C, D[_]: Monad](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
    val db: D[B] = adb(a)

    val dc = Monad[D].flatMap(db)(bdc)

    dc
  }

  trait Monad[C[_]]{
    def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
    @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
    @inline def >>==[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  }

  object Monad {
    def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
  }
}
