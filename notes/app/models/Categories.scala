package models


import java.sql.Date

case class Category(id: Int, name: String, created_at: Date, updated_at: Date)
