package domain.service

/**
  * Created by juannana on 2018/10/01.
  */

import cats.data.Reader
import domain.model.Statistic
import domain.repository.StatisticRepository
import play.api.Logger

import scala.concurrent.Future

object StatisticService {

  def findAll (): Reader[StatisticRepository, Future[List[Statistic]]] = Reader {
    (repo)=>
    {
      repo.findAll()
    }
  }

  def save(statistic: Statistic): Reader[StatisticRepository, Future[String]] = Reader{
    (repo)=>
    {
      repo.save(statistic)
    }
  }
}
