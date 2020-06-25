package fplibrary

trait Monad[C[_]] extends Functor[C] {
  override def map[A, B](ca: C[A])(ab: A => B): C[B] =
    flatMap(ca)(a => pure(ab(a)))

  def pure[A](a: =>A): C[A]
  @inline def point[A](a: =>A): C[A] = pure(a)
  @inline def unit[A](a: =>A): C[A] = pure(a)

  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  @inline def >>==[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
}

object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}
