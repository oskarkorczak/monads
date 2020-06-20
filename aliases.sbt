import Util._

addCommandAlias("ll", "projects")
addCommandAlias("cd", "project")
addCommandAlias("root", "cd monads")

addCommandAlias("lib", "project fp-library")
addCommandAlias("app", "project application-library")
addCommandAlias("main", "project end-of-the-world")

addCommandAlias("c", "compile")
addCommandAlias("ca", "test:compile")
addCommandAlias("t", "test")
addCommandAlias("r", "run")
addCommandAlias(
  "up2date",
  "reload plugins; dependencyUpdates; reload return; dependencyUpdates"
)

onLoadMessage +=
  s"""|
      |───────────────────────────
      |  List of defined ${styled("aliases")}
      |────────┬──────────────────
      |${styled("ll")}      │ projects
      |${styled("cd")}      │ project
      |${styled("root")}    │ cd monads
      |${styled("lib")}     │ cd lib
      |${styled("app")}     │ cd app
      |${styled("main")}    │ cd main
      |${styled("c")}       │ compile
      |${styled("ca")}      │ compile all
      |${styled("t")}       │ test
      |${styled("r")}       │ run
      |${styled("up2date")} │ dependencyUpdates
      |────────┴──────────────────""".stripMargin
