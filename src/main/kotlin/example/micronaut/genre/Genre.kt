package example.micronaut.genre

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Genre(
        @Id
        var name: String = "",
        //var id: UUID? = null,
        @DateCreated
        var createdOn: LocalDateTime = LocalDateTime.now(),
        @DateUpdated
        var lastModified: LocalDateTime = LocalDateTime.now())
