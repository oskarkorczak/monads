package fplibrary

trait Monad[C[_]] extends Functor[C] {
  def pure[A](a: =>A): C[A]
  @inline def point[A](a: =>A): C[A] = pure(a)
  @inline def unit[A](a: =>A): C[A] = pure(a)

  // A => B         C[A] => C[B]
  // A => C[B]      C[A] => C[C[B]]
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  @inline def >>==[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)

  def flatten[A](cca: C[C[A]]): C[A]
  @inline def join[A](cca: C[C[A]]): C[A] = flatten(cca)
}

object Monad extends Summoner[Monad]
