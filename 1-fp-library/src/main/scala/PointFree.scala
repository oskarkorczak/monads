package fplibrary

object PointFree {

  // goal is to have: val ac = compose(ab, bc)
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    val b = ab(a)
    val c = bc(b)

    c
  }

  def composeKleisli[A, B, C, D[_] : Monad](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
    val db: D[B] = adb(a)
    val dc = Monad[D].flatMap(db)(bdc)

    dc
  }
}
