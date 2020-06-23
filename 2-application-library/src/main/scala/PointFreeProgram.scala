import fplibrary._

object PointFreeProgram {

  // format: OFF
  lazy val createDescription: Array[String] => Description[Unit] =
    ignoreArgs             -->
    hyphens                -->
    displayKleisli         >=>
    question               -->
    displayKleisli         >=>
    prompt                 -->
    convertStringToInt     -->
    ensureAmountIsPositive -->
    round                  -->
    createMessage          -->
    displayKleisli         >=>
    hyphens                -->
    displayKleisli         >=>
    Description.brokenCreate
  // format: ON

  private lazy val ignoreArgs: Array[String] => Unit = _ =>
    ()

  private lazy val hyphens: Any => String = _ =>
    "-" * 50

  private lazy val question: Any => String = _ =>
    "How much money would you like to deposit?"

  // side effect (writing to the console)
  private lazy val display: Any => Unit = input => {
    println(input)
  }

  private lazy val displayKleisli: Any => Description[Unit] = input => Description.create {
    println(input)
  }

  // side effect (reading from the console)
  private lazy val prompt: Any => String = _ => "5"

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
