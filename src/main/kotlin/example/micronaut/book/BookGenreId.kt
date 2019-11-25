package example.micronaut.book

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class BookGenreId(
        @Column(name = "book_id")
        val bookId: UUID,
        @Column(name = "genre_name")
        val genreName: String)
