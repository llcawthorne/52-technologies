package com.github.llcawthorne.datamodel

import com.github.llcawthorne.datamodel.ColumnDataMappers._
import java.sql.Timestamp
import java.time.LocalDateTime

import com.github.llcawthorne.datamodel.Priority.Priority
import slick.jdbc.H2Profile.api._

object DataModel {

  case class Task(
                 title: String,
                 description: String = "",
                 createdAt: LocalDateTime = LocalDateTime.now(),
                 dueBy: LocalDateTime,
                 tags: Set[String] = Set(),
                 priority: Priority = Priority.LOW,
                 id: Long = 0L
                 )

  class TaskTable(tag: Tag) extends Table[Task](tag, "TASKS") {
    def title = column[String]("TITLE")
    def description = column[String]("DESCRIPTION")
    def createdAt = column[LocalDateTime]("CREATED_AT")(localDateTimeColumnType)
    def dueBy = column[LocalDateTime]("DUE_BY")(localDateTimeColumnType)
    def tags = column[Set[String]]("TAGS")(setStringColumnType)
    def priority = column[Priority]("priority")(priorityMapper)
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

    def * = (title, description, createdAt, dueBy, tags, priority, id) <>(Task.tupled, Task.unapply)
  }

  lazy val Tasks = TableQuery[TaskTable]

  val createTaskTableAction = Tasks.schema.create

  def insertTaskAction(tasks: Task*) = Tasks ++= tasks.toSeq

  def listTasksAction = Tasks.result
}

object ColumnDataMappers {

  implicit val localDateTimeColumnType = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.valueOf(ldt),
    t => t.toLocalDateTime
  )

  implicit val setStringColumnType = MappedColumnType.base[Set[String], String](
    tags => tags.mkString(","),
    tagsString => tagsString.split(",").toSet
  )

  implicit val priorityMapper = MappedColumnType.base[Priority, Int](
    p => p.id,
    v => Priority(v)
  )

}
