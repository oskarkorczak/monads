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

  def composeKleisli2[A, B, C, D[_]](adb: A => Description[B], bdc: B => Description[C]): A => Description[C] = a => {
    val db: Description[B] = adb(a)

    val dc = helper(db, bdc)

    dc
  }

  def helper[A, B, C[_]](ca: C[A], acb: A => C[B]): C[B] = ???
}
