package fplibrary

trait Functor[C[_]] {
//  def lift[A, B]: (A => B) => (C[A] => C[B])
//  def lift[A, B](ab: A => B)(ca: C[A]): C[B]
  def map[A, B](ca: C[A])(ab: A => B): C[B]
  @inline def fmap[A, B](ca: C[A])(ab: A => B): C[B] = map(ca)(ab)
}

object Functor extends Summoner[Functor]