package infrastructure.persistence

import java.util.concurrent.Executors
import javax.inject.Inject

import domain.model.Statistic
import play.api.db.slick.DatabaseConfigProvider
import play.db.NamedDatabase
import domain.repository.StatisticRepository
import play.api.Logger
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by juannana on 2018/10/01.
  */
class StatisticRepoImpl @Inject() ( @NamedDatabase( "default" ) val dbProvider:DatabaseConfigProvider ) extends StatisticRepository {

  private val dbConfig = dbProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._
  import infrastructure.persistence.tables.StatisticTable.statisticTable

  implicit val ec = ExecutionContext.fromExecutorService( Executors.newFixedThreadPool( 15 ) )

  override def save(sta: Statistic): Future[String] = {
    //val query = statisticTable.map(p=>(p.namePlayer1, p.namePlayer2, p.winner))+= (sta.namePlayer1, sta.namePlayer2, sta.winner)
    db.run( statisticTable += sta ).map( resp => s"Se ha agregado satisfactoriamente" )

  }

  override def findAll(): Future[List[Statistic]] = {
    db.run( statisticTable.result ).map( resul=> {
      Logger.info(s"El resultado en la implementación fue $resul")
      resul.toList
    } )
  }
}
