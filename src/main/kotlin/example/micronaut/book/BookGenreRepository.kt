package example.micronaut.book

import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@JdbcRepository(dialect = Dialect.H2)
interface BookGenreRepository : CrudRepository<BookGenre, BookGenreId> {
    @Query("SELECT * FROM book_genre WHERE book_id = :bookId")
    fun findByBookId(bookId: UUID): List<BookGenre>
}
