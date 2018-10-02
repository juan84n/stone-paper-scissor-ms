package infrastructure.persistence.tables

/**
  * Created by juannana on 2018/10/01.
  */
import domain.model.Statistic
import slick.jdbc.MySQLProfile.api._

object StatisticTable {
  val statisticTable = TableQuery[StatisticTable]

  class StatisticTable( tag: Tag ) extends Table[Statistic]( tag, "tbl_statistic" ) {
    def id = column[Double]( "id" , O.PrimaryKey, O.AutoInc)
    def namePlayer1 = column[String]( "namePlayer1" )
    def namePlayer2 = column[String]( "namePlayer2" )
    def winner = column[String]( "winner" )

    //val pk = primaryKey( "tbl_statistic_pk", id )

    override def * = ( id, namePlayer1, namePlayer2, winner ) <> ( Statistic.tupled, Statistic.unapply )
  }

}
