# Quantum Bookstore

Design an online book store with the following criteria:
- The book store may have an inventory of many books of these types:
  - Paper book which have stock and may be shipped
  - EBook which have a filetype and may be sent via email
  - Showcase/Demo book which is not for sale
- you should be able to do the following:
  - Add a book to the inventory with some details like ISBN(just any string identifier), title, year when the book is published, price
  - Remove and return outdated books that passed specific number of years
  - Buy a single book providing its ISBN, quantity, email,address
    - Reduces the quantity of the book from the inventory, throw error if not available
    - Return the paid amount.
    - Send Paper book to the ShippingService with the address provided (no implementation required)
    - Send EBook to MailService with the email provided (no implementation required) add field author name to the book
- Provide a class to test adding, removing, buying books This class should be named QuantumBookstoreFullTest
- Design the system to be easily extensible, so that we don’t need to modify it if we’ve added a new type of products
## UML
<img width="5616" alt="Fawry Project UML (Copy)" src="https://github.com/user-attachments/assets/228986f1-25a1-49f7-a1a4-779641d7d613" />

## Example Code
![Screenshot_20250708_193532](https://github.com/user-attachments/assets/22cb37a5-30b4-4da8-8826-540a9bd87367)
## Example Code Running
![Screenshot_20250708_193545](https://github.com/user-attachments/assets/10041189-e4c8-4498-b977-23e939277120)
![Screenshot_20250708_193552](https://github.com/user-attachments/assets/e6750dd2-77f4-45ff-836c-07eacbe512dc)
## Tests running (18 total)
![Screenshot_20250708_193711](https://github.com/user-attachments/assets/8f58e53e-447c-4614-9541-3a8cc984a66c)

