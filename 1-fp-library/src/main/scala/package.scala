package object fplibrary {
  implicit final class InfixNotationForPointFree[A, B](private val ab: A => B) extends AnyVal {
    @inline def `-->`[C](bc: B => C): A => C = PointFree.compose(ab, bc)
  }

  implicit final class InfixNotationForPointFreeKleisli[A, B, D[_]](private val adb: A => D[B]) extends AnyVal {
    @inline def `>=>`[C](bdc: B => D[C])(implicit M: Monad[D]): A => D[C] = PointFree.composeKleisli(adb, bdc)
  }
}

