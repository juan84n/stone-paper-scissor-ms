package infrastructure.module

/**
  * Created by juannana on 2018/10/01.
  */
import play.api.{ ApplicationLoader, Logger }
import play.api.inject.guice.{ GuiceApplicationBuilder, GuiceApplicationLoader }

class ApplicationLoader extends GuiceApplicationLoader() {

  override def builder( context: ApplicationLoader.Context ): GuiceApplicationBuilder = {

    Logger.info( "ApplicationLoader... (ok)" )

    initialBuilder
      .disableCircularProxies()
      .in( context.environment )
      .loadConfig( context.initialConfiguration )
      .overrides( overrides( context ): _* )

  }

}