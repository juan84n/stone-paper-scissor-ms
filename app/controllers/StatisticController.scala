package controllers

import java.util.concurrent.Executors
import javax.inject._

import domain.model.Statistic
import domain.repository.StatisticRepository
import domain.service.StatisticService
import infrastructure.shared.JsonMapper
import play.api._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.Logger

import scala.concurrent.{ExecutionContext, Future}

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class StatisticController @Inject()(cc: ControllerComponents, statisticRepo: StatisticRepository) extends AbstractController(cc) with JsonMapper {

  implicit val ec = ExecutionContext.fromExecutorService( Executors.newFixedThreadPool( 15 ) )
  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def status(): Action[AnyContent] = Action {
    Logger.info( "BaseController ... /status  (ok)" )
    Ok( "We're up and running" ) as TEXT
  }
  def saveWithReader(): Action[AnyContent] = Action.async { request =>

    val jsonBody: Option[JsValue] = request.body.asJson

    Logger.info(jsonBody.toString)

    jsonBody match {
      case None => Future.successful( BadRequest( "No recibimos un json válido" ) as TEXT )
      case Some( body ) => {
        val receivedStatistic: Statistic = body.as[Statistic]
        val myConfiguredReader = StatisticService.save( receivedStatistic )
        myConfiguredReader.run( statisticRepo )
          .map { resultado => Ok( Json.toJson(resultado) ) as JSON }
      }
    }
  }

  def findWithReader() = Action.async {

    // Paso 1. Configurar el Reader
    val myConfiguredReader = StatisticService.findAll()

    // Paso 2. Ejecutarlo
    myConfiguredReader.run( statisticRepo )
      .map {
        resultado => {
          Logger.info(s"el resultado fue $resultado")
          Ok( Json.toJson( resultado ) ) as JSON
        }
      }

  }

  def testOption(): Action[AnyContent] = Action {
    Logger.info( "BaseController ... /status  (ok)" )
    Ok( "We're up and running" ) as TEXT
  }
}

