package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import daos.CategoryDao
import javax.inject.{ Inject, Singleton }
import play.api.data.Form
import play.api.data.Forms.{ mapping, text }
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.mvc.Action
import play.api.mvc.{ Action, Controller }

case class CategoryForm(action: String, name: String)

@Singleton
class CategoryController @Inject() (val categoryDao: CategoryDao, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val categoryForm = Form(
    mapping(
      "action" -> text,
      "name" -> text
    )(CategoryForm.apply)(CategoryForm.unapply))

  /**
   * 初期表示(GET)
   */
  def get = Action.async {
    categoryDao.all().map(categories => Ok(views.html.register(categoryForm, categories)))
  }

  /**
   * アクション(POST)
   */
  def post = Action.async { implicit request =>
    categoryForm.bindFromRequest.fold(
      formWithErrors => {
        categoryDao.all().map(categories => Ok(views.html.register(formWithErrors, categories)))
      },
      categoryData => {
        val action = categoryData.action.split(":")
        action(0) match {
          case "insert" => insert(categoryData)
          case "update" => update(action(1), categoryData)
          case "delete" => delete(action(1))
          case _ => println("No Action!!")
        }
        categoryDao.all().map(categories => Ok(views.html.register(categoryForm.fill(categoryData), categorys)))
      })
  }

  /** 登録 */
  def insert(categoryForm: CategoryForm) = categoryDao.insert(Category(0, categoryForm.name))

  /** 更新 */
  def update(id: String, categoryForm: CategoryForm) = categoryDao.update(Category(id.toLong, categoryForm.name))

  /** 削除 */
  def delete(id: String) = categoryDao.delete(Category(id.toLong, ""))

}
