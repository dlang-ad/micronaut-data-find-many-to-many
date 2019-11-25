package example.micronaut.book

import io.micronaut.data.annotation.EmbeddedId
import io.micronaut.data.annotation.MappedEntity

@MappedEntity("book_genre")
data class BookGenre(
        @EmbeddedId
        val id: BookGenreId,
        var applicable: Boolean)
