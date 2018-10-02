package infrastructure.shared

/**
  * Created by juannana on 2018/10/01.
  */
import domain.model.Statistic
import play.api.libs.json.{Json, OFormat}

trait JsonMapper {

  implicit val statisticFormat: OFormat[Statistic] = Json.format[Statistic]
}

