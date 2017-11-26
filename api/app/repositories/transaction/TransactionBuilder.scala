package repositories.transaction

trait TransactionBuilder {

  def exec[A](value: A): Transaction[A]
}
