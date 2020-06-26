import fplibrary._

object PointProgram {

  def createDescription(args: Array[String]): IO[Unit] = for {
      _ <- display(hyphens)
      _ <- display(question)
      message <- prompt.map(fromInputToMessage)
      _ <- display(message)
      _ <- display(hyphens)
    } yield ()

  private def fromInputToMessage(input: String): String = {
    val integerAmount = converStringToInt(input)
    val positiveAmount = ensureAmountIsPositive(integerAmount)
    val balance = round(positiveAmount)
    val message = createMessage(balance)

    message
  }

  private val hyphens: String = "\u2500" * 50

  private val question: String = "How much money would you like to deposit?"

  // side effect (writing to the console)
  private def display(input: Any): IO[Unit] =
    IO.create {
      println(input)
    }

  // side effect (reading from the console)
  private def prompt: IO[String] = IO.create("5") // scala.io.StdIn.readLine

  // potential side effect (throwing of a NumberFormatException)
  private def converStringToInt(input: String): Int = input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if (amount < 1)
      1
    else
      amount

  @scala.annotation.tailrec
  private def round(amount: Int): Int =
    if (isDivisibleByHundered(amount))
      amount
    else
      round(amount + 1)

  private def isDivisibleByHundered(amount: Int): Boolean =
    amount % 100 == 0

  private def createMessage(balance: Int): String =
    s"Congratulatins, you now have USD $balance."
}
