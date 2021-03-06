object Interpreter {

  def main(args: Array[String]): Unit = {
    print(Console.RED)
    val description: IO[Unit] = PointProgram.createDescription(args)
    def interpret[A](description: IO[A]): A = description.unsafeRun()

    print(Console.GREEN)
    interpret(description)
    print(Console.RESET)
  }
}
