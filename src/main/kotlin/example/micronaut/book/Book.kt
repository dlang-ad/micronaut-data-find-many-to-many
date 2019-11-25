package example.micronaut.book

import example.micronaut.genre.Genre
import io.micronaut.data.annotation.AutoPopulated
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.Relation
import java.time.LocalDateTime
import java.util.Collections.emptySet
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Book(
    @Id
    @AutoPopulated
    var id: UUID? = null,
    @DateCreated
    var createdOn: LocalDateTime = LocalDateTime.now(),
    @DateUpdated
    var lastModified: LocalDateTime = LocalDateTime.now(),
    var name: String) {
    @Relation(Relation.Kind.MANY_TO_MANY)
    var genre: Set<Genre> = emptySet()
}
