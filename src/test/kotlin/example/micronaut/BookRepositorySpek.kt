package example.micronaut

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import example.micronaut.book.Book
import example.micronaut.book.BookGenre
import example.micronaut.book.BookGenreId
import example.micronaut.book.BookGenreRepository
import example.micronaut.book.BookRepository
import example.micronaut.genre.Genre
import example.micronaut.genre.GenreRepository
import io.micronaut.context.ApplicationContext
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature


class BookRepositorySpek: Spek({
    val embeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    val bookRepository = embeddedServer.applicationContext.getBean(BookRepository::class.java)
    val genreRepository = embeddedServer.applicationContext.getBean(GenreRepository::class.java)
    val bookGenreRepository = embeddedServer.applicationContext.getBean(BookGenreRepository::class.java)

    Feature("BookRepository") {
        Scenario("Create Book") {
            lateinit var book: Book

            When("We create a book") {
                book = bookRepository.save(Book(name = "TestBook"))
            }

            Then("It is created") {
                assertThat(book).isNotNull()
                assertThat(book.id).isNotNull()
                assertThat(book.name).isEqualTo("TestBook")
            }

            afterGroup {
                bookRepository.deleteAll()
            }
        }

        Scenario("Find all books") {
            lateinit var books: Page<Book>

            Given("A book exists with two associated genres") {
                val fantasyGenre = genreRepository.save(Genre(name = "Fantasy"))
                val scifiGenre = genreRepository.save(Genre(name = "Scifi"))
                val book = bookRepository.save(Book(name = "TestBook"))

                bookGenreRepository.save(BookGenre(BookGenreId(bookId = book.id!!, genreName = fantasyGenre.name), applicable = true))
                bookGenreRepository.save(BookGenre(BookGenreId(bookId = book.id!!, genreName = scifiGenre.name), applicable = true))
            }

            When("We findAll books") {
                books = bookRepository.findAll(Pageable.from(0, 25, Sort.UNSORTED))

            }
            Then("One book with two genres should be returned") {
                assertThat(books).isNotNull()
                assertThat(books.totalSize).isEqualTo(1L)
                assertThat(books.content.size).isEqualTo(1)
                assertThat(books.content[0].name).isEqualTo("TestBook")
            }
        }

    }
    afterGroup {
        embeddedServer.close()
    }
})
