package daos

import javax.inject.Inject
import java.sql.Date

import models.Category
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.driver.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class CategoryDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Categories = TableQuery[CategoriesTable]

  /** 全検索 */
  def all(): Future[Seq[Category]] = db.run(Categories.result)

  /** 登録 */
  def insert(category: Category): Future[Unit] = {
    val categories = Categories returning Categories.map(_.id) into ((category, id) => category.copy(id = id)) += category
    db.run(categories.transactionally).map(_ => ())
  }

  /** 更新 */
  def update(category: Category): Future[Unit] = {
    db.run(Categories.filter(_.id === category.id).map(_.name).update(category.name)).map(_ => ())
  }

  /** 削除 */
  def delete(category: Category): Future[Unit] = {
    db.run(Categories.filter(_.id === category.id).delete).map(_ => ())
  }

  /** マッピング */
  private class CategoriesTable(tag: Tag) extends Table[Category](tag, "CATEGORY") {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def created_at = column[Date]("CREATED_AT")
    def updated_at = column[Date]("UPDATED_AT")
    def * = (id, name, created_at, updated_at) <> (Category.tupled, Category.unapply _)
  }

}
