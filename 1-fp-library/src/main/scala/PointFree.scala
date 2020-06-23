package fplibrary

object PointFree {

  // goal is to have: val ac = compose(ab, bc)
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    val b = ab(a)
    val c = bc(b)

    c
  }
}
