package fplibrary

trait Monad[C[_]]{
  def map[A, B](ca: C[A])(ab: A => B): C[B]
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  @inline def >>==[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
}

object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}
