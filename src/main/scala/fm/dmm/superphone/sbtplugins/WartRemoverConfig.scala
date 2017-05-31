package fm.dmm.superphone.sbtplugins

import sbt._

object WartRemoverConfig {
  import wartremover.Wart._

  // dirty hack to ignore stuff generated by Play (since it doesnt adhere)
  // why dirty? Because we could depend on Play plugin instead, and use it to
  // generate these routes from it (which would guarantee they are correct)
  // instead we are hardcoding it, since we dont want everyone who depends on this
  // plugin to carry baggage whole Play plugin as dependency just because of these
  def ignoreRoutes(root: File) = Seq(
    root / "routes" / "main" / "router" / "Routes.scala",
    root / "routes" / "main" / "router" / "RoutesPrefix.scala",
    root / "routes" / "main" / "controllers" / "ReverseRoutes.scala",
    root / "routes" / "main" / "controllers" / "javascript" / "JavaScriptReverseRoutes.scala"
  )

  val flaggedWarts = Seq(
      //Any,
      //AsInstanceOf,
      //DefaultArguments,
      //EitherProjectionPartial,
      Enumeration,
      //Equals,
      ExplicitImplicitTypes,
      FinalCaseClass,
      //FinalVal,
      ImplicitConversion,
      ImplicitParameter,
      //IsInstanceOf,
      JavaConversions,
      LeakingSealed,
      //MutableDataStructures,
      //NonUnitStatements,
      //Nothing,
      //Null,
      //Option2Iterable,
      //OptionPartial,
      //Overloading,
      Product,
      PublicInference,
      Return,
      Serializable,
      StringPlusAny
      //Throw,
      //TraversableOps,
      //ToString,
      //TryPartial,
      //Var,
      //While
  )
}
