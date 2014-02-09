package com.example.sample

import scala.collection.mutable

case class Person(first: String, last: String, age: Int, id: Option[Long]) {

}

object PersonManager {

  val instances = mutable.HashMap.empty[Long, Person]

  var nextId: Long = 1L

  def create(template: Person): Person = {
    val person = Person(template.first, template.last, template.age, Some(nextId))
    instances += (nextId -> person)
    nextId = nextId + 1L
    person
  }

  def retrieve(id: Long): Option[Person] = instances.get(id)

  def update(person: Person): Option[Person] =
    if (person.id.isEmpty)
      None
    else {
      val old = retrieve(person.id.get)
      if (old.isEmpty)
        None
      else {
    	 instances += (person.id.get -> person)
    	 Some(person)
      }
    }
  
  def retrieveAll: List[Person] = instances.values.toList

}