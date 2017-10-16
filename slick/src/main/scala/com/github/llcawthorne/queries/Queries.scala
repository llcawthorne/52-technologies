package com.github.llcawthorne.queries

import java.time.{LocalDate, LocalDateTime}

import com.github.llcawthorne.datamodel.ColumnDataMappers._
import com.github.llcawthorne.datamodel.DataModel.{Task, TaskTable, Tasks}
import com.github.llcawthorne.datamodel.Priority
import com.github.llcawthorne.datamodel.Priority.Priority
import slick.jdbc.H2Profile.api._

object Queries {

  val selectAllTasksQuery: Query[TaskTable, Task, Seq] = Tasks

  val selectAllTaskTitleQuery: Query[Rep[String], String, Seq] = Tasks.map(_.title)

  val selectMultipleColumnsQuery: Query[(Rep[String], Rep[Priority], Rep[LocalDateTime]), (String, Priority, LocalDateTime), Seq] =
    Tasks.map(t => (t.title, t.priority, t.createdAt))

  val selectHighPriorityTaskTitleQuery: Query[Rep[String], String, Seq] =
    Tasks.filter(_.priority === Priority.HIGH).map(_.title)

  val selectTasksSortedByDueDateDescQuery: Query[TaskTable, Task, Seq] =
    Tasks.sortBy(_.dueBy.desc)

  def findAllTasksPageQuery(skip: Int, limit: Int) = Tasks.drop(skip).take(limit)

  val selectAllTasksDue = Tasks.filter(_.dueBy >= LocalDate.now().atStartOfDay())

  val selectAllTaskTitleDueToday: Query[Rep[String], String, Seq] = Tasks
    .filter(t => t.dueBy > LocalDate.now().atStartOfDay() && t.dueBy < LocalDate.now().atStartOfDay().plusDays(1))
    .map(_.title)

  val selectTasksBetweenTodayAndSameDateNextMonthQuery: Query[TaskTable, Task, Seq] =
    Tasks.filter(t => t.dueBy.between(LocalDateTime.now(), LocalDateTime.now.plusMonths(1)))

  val selectAllTasksDueToday: Query[TaskTable, Task, Seq] = Tasks
    .filter(_.dueBy > LocalDate.now().atStartOfDay())
    .filter(_.dueBy < LocalDate.now().atStartOfDay().plusDays(1))

  val checkIfAnyHighPriorityTaskExistsToday: Rep[Boolean] =
    selectAllTasksDueToday.filter(_.priority === Priority.HIGH).exists
}
