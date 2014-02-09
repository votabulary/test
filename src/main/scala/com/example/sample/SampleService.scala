package com.example.sample

import akka.actor.Actor
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._
import MediaTypes._
import akka.actor.ActorSystem
import akka.actor.Props
import akka.io.IO
import spray.can.Http
import spray.json.DefaultJsonProtocol

object JsonImplicits extends DefaultJsonProtocol {
  implicit val implicitPerson = jsonFormat4(Person)
}

trait SampleService extends HttpService with SprayJsonSupport {

  import JsonImplicits._

  val myRoute =
    // hello
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Sample Service running!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
      path("person") {
        // create person
        post {
          entity(as[Person]) { template =>
            complete {
              PersonManager.create(template)
            }
          }
        } ~
          // retrieve all persons
          get {
            complete {
              PersonManager.retrieveAll
            }
          }
      } ~
      path("person" / IntNumber) { id =>
        // retrieve person
        get {
          complete {
            PersonManager.retrieve(id)
          }
        } ~
          // update person
          put {
            entity(as[Person]) { template =>
              complete {
                val person = Person(template.first, template.last, template.age, Some(id))
                PersonManager.update(person)
              }
            }
          }
      }
}

/**
 * The actor that runs the route defined in the update receiver trait.
 */
class SampleServiceActor extends Actor with SampleService {

  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

object Main {

  def main(args: Array[String]): Unit = {

    println("starting...")

    implicit val system = ActorSystem("sample-actor-system")

    // create and start our service actor
    val service = system.actorOf(Props[SampleServiceActor], "sample-service")

    // start a new HTTP server on port 8081 with our service actor as the handler
    IO(Http) ! Http.Bind(service, interface = "localhost", port = 8081)
  }
}