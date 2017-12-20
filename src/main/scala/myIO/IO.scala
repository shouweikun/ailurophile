package myIO

/**
  * Created by john_liu on 2017/12/20.
  */
trait IO[A] {
  def run: A

  def map[B](f:A=>B):IO[B] = new IO[B] {
    override def run: B = f(this.run)
  }
  def
}
