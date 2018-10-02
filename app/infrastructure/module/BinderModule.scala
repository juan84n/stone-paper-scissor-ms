package infrastructure.module

/**
  * Created by juannana on 2018/10/01.
  */
import com.google.inject.AbstractModule
import play.api.Logger
import domain.repository.StatisticRepository
import infrastructure.persistence.StatisticRepoImpl

class BinderModule extends AbstractModule {

  override def configure(): Unit = {

    Logger.info("BinderModule... (ok)")

    //bind( classOf[Greeting] ).to( classOf[SpanishGreeting] ).asEagerSingleton()
    //bind( classOf[AccountRepository] ).to( classOf[AccountRepoInMemory] ).asEagerSingleton()
    bind(classOf[StatisticRepository]).to(classOf[StatisticRepoImpl]).asEagerSingleton()

  }
}
