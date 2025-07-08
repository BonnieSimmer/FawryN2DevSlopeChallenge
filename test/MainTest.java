import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.time.Year;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import allBooks.*;
import java.util.List;
class MainTest {
    private BookStore bookStore;
    private ArrayList<Book> testBooks;

    @BeforeEach
    void setUp() {
        bookStore = new BookStore();
        testBooks = new ArrayList<>();
        // Initialize with test data
        testBooks.add(new Ebook("E101", "Test Ebook", Year.of(2020), 9.99, "pdf"));
        testBooks.add(new PaperBook("P101", "Test PaperBook", Year.of(2016), 19.99, 5));
        testBooks.add(new ShowcaseDemoBook("S101", "Test ShowcaseBook", Year.of(2025)));
    }

    @Nested
    class BookStoreTests {
        @Test
        void testAddBooks() {
            bookStore.addBooks(testBooks);
            assertEquals(3, bookStore.getInventorySize());
        }

        @Test
        void testRemoveBook() {
            bookStore.addBooks(testBooks);
            Book bookToRemove = testBooks.get(0);
            bookStore.removeBook(bookToRemove);
            assertEquals(2, bookStore.getInventorySize());
        }

        @Test
        void testBuySingleBook() {
            bookStore.addBooks(testBooks);
            // Test buying an available book
            assertDoesNotThrow(() -> {
                bookStore.buySingleBook("P101", 1, "test@test.com", "123 Test St");
            });
            
            // Test buying with invalid quantity
            assertThrows(IllegalArgumentException.class, () -> {
                bookStore.buySingleBook("P101", 6, "test@test.com", "123 Test St");
            });
        }

        @Test
        void testCheckForOutdatedBooks() {
            // Add an outdated book (2014 or earlier)
            testBooks.add(new Ebook("E102", "Old Book", Year.of(2003), 5, "pdf"));
            bookStore.addBooks(testBooks);
            
            int initialSize = bookStore.getInventorySize();
            bookStore.checkForOutdatedBooks();
            assertEquals(initialSize - 1, bookStore.getInventorySize());
        }

        @Test
        void testBuyShowcaseBook() {
            bookStore.addBooks(testBooks);
            // Showcase books cannot be purchased
            assertThrows(IllegalArgumentException.class, () -> {
                bookStore.buySingleBook("S101", 1, "test@test.com", "123 Test St");
            });
        }
    }

    @Nested
    class EbookTests {
        @Test
        void testEbookCreation() {
            Ebook ebook = new Ebook("E101", "Test Ebook", Year.of(2021), 16.87, "pdf");
            assertEquals("E101", ebook.getIsbn());
            assertEquals("Test Ebook", ebook.getTitle());
            assertEquals(Year.of(2021), ebook.getPublishYear());
            assertEquals("pdf", ebook.getFileType());
        }
    }

    @Nested
    class PaperBookTests {
        @Test
        void testPaperBookCreation() {
            PaperBook book = new PaperBook("P101", "Test Paper Book", Year.of(2024), 19.99, 5);
            assertEquals("P101", book.getIsbn());
            assertEquals("Test Paper Book", book.getTitle());
            assertEquals(Year.of(2024), book.getPublishYear());
            assertEquals(5, book.getStock());
        }

        @Test
        void testInvalidQuantity() {
            assertThrows(IllegalArgumentException.class, () -> {
                new PaperBook("P101", "Test Paper Book", Year.of(2024), 19.99, -1);
            });
        }

        @Test
        void testUpdateQuantity() {
            PaperBook book = new PaperBook("P101", "Test Paper Book", Year.of(2024), 19.99, 5);
            book.setStock(2);
            assertEquals(2, book.getStock());
        }
    }

    @Nested
    class ShowcaseDemoBookTests {
        @Test
        void testShowcaseBookCreation() {
            ShowcaseDemoBook book = new ShowcaseDemoBook("S101", "Test Showcase Book", Year.of(2024));
            assertEquals("S101", book.getIsbn());
            assertEquals("Test Showcase Book", book.getTitle());
            assertEquals(Year.of(2024), book.getPublishYear());
        }

        @Test
        void testShowcaseBookPurchaseAttempt() {
            ShowcaseDemoBook book = new ShowcaseDemoBook("S101", "Test Showcase Book", Year.of(2024));
            bookStore.addBooks(new ArrayList<>(List.of(book)));
            
            // Attempting to purchase a showcase book should throw an exception
            assertThrows(IllegalArgumentException.class, () -> {
                bookStore.buySingleBook("S101", 1, "test@test.com", "123 Test St");
            });
        }

        @Test
        void testShowcaseBookISBNFormat() {
            // Showcase books should start with 'S'
            ShowcaseDemoBook book = new ShowcaseDemoBook("S101", "Test Showcase Book", Year.of(2024));
            assertTrue(book.getIsbn().startsWith("S"), 
                "Showcase book ISBN should start with 'S'");
        }

        @Test
        void testMultipleShowcaseBooksInStore() {
            ArrayList<Book> showcaseBooks = new ArrayList<>();
            showcaseBooks.add(new ShowcaseDemoBook("S101", "Demo Book 1", Year.of(2024)));
            showcaseBooks.add(new ShowcaseDemoBook("S102", "Demo Book 2", Year.of(2024)));
            
            bookStore.addBooks(showcaseBooks);
            
            // Verify both books are added successfully
            assertEquals(2, bookStore.getInventorySize());
            
            // Attempt to purchase either should fail
            assertThrows(IllegalArgumentException.class, () -> {
                bookStore.buySingleBook("S101", 1, "test@test.com", "123 Test St");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                bookStore.buySingleBook("S102", 1, "test@test.com", "123 Test St");
            });
        }

        @Test
        void testShowcaseBookRemoval() {
            ShowcaseDemoBook book = new ShowcaseDemoBook("S101", "Test Showcase Book", Year.of(1965));
            bookStore.addBooks(new ArrayList<>(List.of(book)));
            
            // Verify book can be removed from inventory like any other book
            bookStore.removeBook(book);
            assertEquals(0, bookStore.getInventorySize());
        }

        @Test
        void testShowcaseBookOutdatedCheck() {
            // Add an old showcase book
            ShowcaseDemoBook oldBook = new ShowcaseDemoBook("S101", "Old Showcase Book", Year.of(1984));
            bookStore.addBooks(new ArrayList<>(List.of(oldBook)));
            
            // Check if outdated books removal works for showcase books
            bookStore.checkForOutdatedBooks();
            assertEquals(0, bookStore.getInventorySize(), 
                "Outdated showcase books should be removed");
        }
    }

    @Nested
    class EdgeCaseTests {
        @Test
        void testNullValues() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook(null, "Test Book", Year.of(2024), 9.99, "pdf");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook("E101", null, Year.of(2024), 9.99, "pdf");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook("E101", "Test Book", null, 9.99, "pdf");
            });
        }

        @Test
        void testEmptyStrings() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook("", "Test Book", Year.of(2024), 9.99, "pdf");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook("E101", "", Year.of(2024), 9.99, "pdf");
            });
        }

        @Test
        void testInvalidPrices() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Ebook("E101", "Test Book", Year.of(2024), -999, "pdf");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new PaperBook("P101", "Test Book", Year.of(2024), 0, 5);
            });
        }
    }
}