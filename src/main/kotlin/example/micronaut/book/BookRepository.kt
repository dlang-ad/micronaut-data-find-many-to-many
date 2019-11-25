package example.micronaut.book

import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import java.util.UUID

@JdbcRepository(dialect = Dialect.MYSQL)
interface BookRepository : PageableRepository<Book, UUID> {
    @Query(FIND_ALL_QUERY, countQuery = """SELECT count(*) from book""")
    @Join("genre", alias = "genre_", type = Join.Type.LEFT_FETCH)
    override fun findAll(pageable: Pageable): Page<Book>

    companion object {
        const val FIND_ALL_QUERY = """SELECT book.*,
            g.`name` AS genre_name,
            g.created_on AS genre_created_on,
            g.last_modified AS genre_last_modified
        FROM book
        LEFT JOIN book_genre ON book.id = book_genre.book_id AND book_genre.applicable IS true
        LEFT JOIN genre AS g ON book_genre.genre_name = g.name"""
    }
}
