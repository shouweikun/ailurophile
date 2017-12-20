package myIO

import cats.{Alternative, Functor, Monad}

/**
  * Created by john_liu on 2017/12/20.
  */
trait IO[A] {
  self =>
  def run: A

  def map[B](f: A => B): IO[B] = new IO[B] {
    override def run: B = {
      f(self.run)
    }
  }

  def flatMap[B](f: A => IO[B]) = new IO[B] {
    override def run: B = f(self.run).run
  }
}

object IO extends Monad[IO] {

  def apply[A](x: A): IO[A] = pure(x)

  override def pure[A](x: A): IO[A] = new IO[A] {
    override def run: A = x
  }


    override def flatMap[A, B](fa: IO[A])(f: (A) => IO[B]): IO[B] = {
      fa flatMap f
    }

  override def tailRecM[A, B](a: A)(f: (A) => IO[Either[A, B]]): IO[B] = {
    f(a).run match {
      case Left(a) => tailRecM(a)(f)
      case Right(b) => IO(b)
    }

  }
  override def whileM[G[_], A](p: IO[Boolean])(body: => IO[A])(implicit G: Alternative[G]): IO[G[A]] = super.whileM(p)(body)
}
