import fplibrary.Description

object PointFreeProgram {

  def createDescription(args: Array[String]): Description[Unit] =
    Description.create(
      display(
        hyphens(
          display(
            createMessage(
              round(
                ensureAmountIsPositive(
                  converStringToInt(
                    prompt(
                      display(
                        question(
                          display(
                            hyphens(
                              args
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )

  private def hyphens(input: Any): String = "-" * 50

  private def question(input: Any): String = "How much money would you like to deposit?"

  // side effect (writing to the console)
  private def display(input: Any): Unit = println(input)

  // side effect (reading from the console)
  private def prompt(input: Any): String = "5"

  // potential side effect (throwing of a NumberFormatException)
  private def converStringToInt(input: String): Int = input.toInt

  private def ensureAmountIsPositive(amount: Int): Int = {
    if (amount < 1)
      1
    else
      amount
  }

  @scala.annotation.tailrec
  private def round(amount: Int): Int = {
    if (isDivisibleByHundered(amount))
      amount
    else
      round(amount + 1)
  }

  private def isDivisibleByHundered(amount: Int): Boolean = {
    amount % 100 == 0
  }

  private def createMessage(balance: Int): String = {
    s"Congratulations, you now have USD $balance."
  }
}
