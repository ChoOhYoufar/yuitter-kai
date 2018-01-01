package repositories.transaction

trait TransactionBuilder {

  def exec[A](value: A): Transaction[A]

  def sequence[A](list: List[Transaction[A]]): Transaction[List[A]]
}
