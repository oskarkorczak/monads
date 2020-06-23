import fplibrary._

object PointFreeProgram {

  // format: OFF
  lazy val createIO: Array[String] => IO[Unit] =
    ignoreArgs             --> hyphens                --> displayKleisli                                                               >=>
    question               --> displayKleisli                                                                                     >=>
    promptKleisli                                                                                                                      >=>
    convertStringToInt     --> ensureAmountIsPositive --> round                  --> createMessage          --> displayKleisli         >=>
    hyphens                --> displayKleisli
  // format: ON

  private lazy val ignoreArgs: Array[String] => Unit = _ =>
    ()

  private lazy val hyphens: Any => String = _ =>
    "-" * 50

  private lazy val question: Any => String = _ =>
    "How much money would you like to deposit?"

  // side effect (writing to the console)
  private lazy val displayKleisli: Any => IO[Unit] = input => IO.create {
    println(input)
  }

  // side effect (reading from the console)
  private lazy val promptKleisli: Any => IO[String] = _ => IO.create("5")

  // potential side effect (throwing of a NumberFormatException)
  private lazy val convertStringToInt: String => Int = input => input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1)
      1
    else
      amount

  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundered(amount))
      amount
    else
      round(amount + 1)

  private lazy val isDivisibleByHundered: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance =>
    s"Congratulations, you now have USD $balance."
}
