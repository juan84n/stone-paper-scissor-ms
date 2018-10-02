package domain.repository

/**
  * Created by juannana on 2018/10/01.
  */
import domain.model.Statistic

import scala.concurrent.Future

trait StatisticRepository {

  def save( acc: Statistic ): Future[String]

  def findAll(): Future[List[Statistic]]

}
