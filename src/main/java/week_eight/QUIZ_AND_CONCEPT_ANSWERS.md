# Week 8: Object-Oriented Design & System Modeling Answers

---

## Part 1: Quiz Questions and Answers

### Question 1
**Question:** A ticketing system manages various event types like concerts, plays, and sports matches. While all events have a title and a scheduled date, concerts also have a performer, and sports matches have competing teams. The system processes tickets for any event type through a common `TicketProcessor`. Which OOP concept allows the `TicketProcessor` to interact with different event types uniformly?  
**Answer: C) Polymorphism**  
*Explanation:* Polymorphism allows a generic method or processor to interact with diverse subclasses through a shared interface or superclass reference without needing to know concrete subclass types.

---

### Question 2
**Question:** In a social media application, a `UserProfile` object has a `dateOfBirth` attribute. The application prevents direct modification of `dateOfBirth` from outside the `UserProfile` class, requiring any changes to be made through an `updateProfileInfo` method that includes validation logic to ensure the date is valid and reasonable. Which OOP principle is being primarily enforced here?  
**Answer: C) Encapsulation**  
*Explanation:* Encapsulation bundles data with the methods that operate on that data and hides internal state by restricting direct access from outside code.

---

### Question 3
**Question:** A publishing house manages authors and their books. An author can write one or more books, but a book must always have exactly one author. If the author is removed from the system, their books still exist but might be reassigned to another author or marked as unauthored. Which UML multiplicity best describes the relationship from Author to Book?  
**Answer: C) Author 1 --- 1..* Book**  
*Explanation:* Exactly one author writes one or more (`1..*`) books.

---

### Question 4
**Question:** A Shopping Cart object directly manages a collection of Cart Item objects. If the Shopping Cart is deleted, all its associated Cart Item objects are also destroyed, as they have no meaning or independent existence outside of that specific cart. Which UML relationship accurately models this strong ownership and lifecycle dependency?  
**Answer: D) Composition**  
*Explanation:* Composition represents a strong "part-of" relationship with coincident lifecycles: when the whole (Shopping Cart) is destroyed, its parts (Cart Items) cease to exist.

---

### Question 5
**Question:** An online course platform defines a general Course concept. Specific types of courses, such as `ProgrammingCourse` and `DesignCourse`, extend this general Course by adding specialized attributes and behaviors while inheriting common ones. The specialized courses are essentially 'is-a' types of the general course. Which UML relationship represents the connection between the general Course and `ProgrammingCourse`?  
**Answer: D) Generalization**  
*Explanation:* In UML, inheritance ("is-a" relationship) between a superclass and subclass is formally termed Generalization.

---

### Question 6
**Question:** A Printer class needs to interact with various `Printable` document types (e.g., `PdfDocument`, `WordDocument`). The Printer is designed to work with any object that adheres to a `Printable` contract, which defines a `print()` method, without needing to know the specific implementation details of each document type. Which UML relationship describes the connection between `PdfDocument` and the `Printable` contract?  
**Answer: C) Realization**  
*Explanation:* The relationship between an interface (contract) and a concrete class that implements it is called Realization in UML.

---

### Question 7
**Question:** A Customer places an Order. The Customer can exist without placing any orders, and an Order can be created and completed even if the Customer record is later archived (though it would still reference the customer ID). There is no strong ownership or lifecycle dependency between them; they are just related by a transaction. Which UML relationship is most suitable for describing this scenario?  
**Answer: B) Association**  
*Explanation:* A standard structural relationship where both entities can exist independently without strict whole-part ownership is an Association.

---

### Question 8
**Question:** A task in a project management system has various statuses: `Pending`, `InProgress`, `Completed`, `Blocked`. A task can move from `Pending` to `InProgress`, from `InProgress` to `Completed` or `Blocked`, but a `Completed` task cannot return to `Pending` or `InProgress`. This behavior defines a specific flow of states and transitions. Which UML diagram type is best suited to model the lifecycle and valid transitions of a Task object?  
**Answer: C) State diagram**  
*Explanation:* A UML State Machine Diagram (State Diagram) is specifically designed to model states, transitions, events, and guard conditions over an object's lifecycle.

---

### Question 9
**Question:** A reporting module generates various types of reports (e.g., Sales Summary, Inventory List, Employee Roster). The module interacts with a generic `ReportGenerator` interface, which defines a `generate()` method. This allows the module to request a report without needing to know the specific details of how each report type is created. Which design concept is primarily demonstrated by using a generic `ReportGenerator` interface?  
**Answer: B) Abstraction**  
*Explanation:* Abstraction focuses on what an object does rather than how it does it, hiding complex underlying implementation details behind a clean, simple contract.

---

### Question 10
**Question:** When a user logs into an application, the following sequence of actions occurs: the user enters credentials, the `AuthenticationService` receives these credentials, it then calls the `UserRepository` to verify them, the `UserRepository` returns the result, and finally, the `AuthenticationService` grants or denies access. This interaction involves multiple objects exchanging messages over time. Which UML behavioral model is most appropriate for visualizing this specific order and flow of messages between objects during the login process?  
**Answer: C) Sequence diagram**  
*Explanation:* Sequence diagrams emphasize time-ordered interaction and message exchange between collaborating objects across lifelines.

---

## Part 2: Concept Questions & Comprehensive Explanations

### Concept Question 1: Encapsulation in User Object Design
- **Application of Encapsulation:** The `email` field is marked `private` so that outside callers cannot alter it directly. Access is exclusively granted through a controlled method `changeEmail(String newEmail)`.
- **State Controlled by the User Object:** The object controls its email address format, domain validity, and uniqueness state.
- **Risks of Unrestricted Access:** Unrestricted direct field modification allows invalid email formats (e.g. empty strings, missing '@', or malformed domains) and bypassing uniqueness checks against duplicate registrations, leaving the system in an inconsistent or corrupt state.
- **How Controlled Access Preserves Integrity:** `changeEmail` enforces preconditions before writing to the field (e.g., regex pattern validation and repository uniqueness verification). If validation fails, an exception is thrown or change rejected, ensuring the object only ever resides in a valid, self-consistent state.

---

### Concept Question 2: Inheritance vs. Composition in ReportingService
- **When Inheritance is Considered:** Inheritance is appropriate only when there is a genuine "is-a" relationship and a clear, stable shared behavioral base where subclasses naturally substitute the superclass (Liskov Substitution Principle).
- **Why Composition is More Suitable:** Optional capabilities like `DataExport` (CSV, PDF, Excel) and `DataVisualization` (Charts, Dashboards) represent behaviors or "has-a" capabilities rather than intrinsic report identities. Using composition, a report can hold pluggable strategy components (`Exporter`, `Visualizer`).
- **Design Rigidities of Single Inheritance:** Forcing these cross-cutting features into single inheritance leads to a combinatorial explosion of rigid subclasses (e.g., `SalesSummaryCsvWithChart`, `SalesSummaryPdfWithoutChart`, `InventoryListCsvWithChart`). Adding a new format or visualization type requires modifying and duplicating branches across the entire class hierarchy.

---

### Concept Question 3: Abstraction, Method Overriding, and Polymorphism in NotificationService
- **Collaboration of the Three Concepts:**
  1. **Abstraction:** Defines a uniform contract (`NotificationChannel` with `send(message)`).
  2. **Method Overriding:** Each concrete subclass (`EmailChannel`, `SmsChannel`, `PushNotificationChannel`) supplies its own protocol-specific transmission logic.
  3. **Runtime Polymorphism:** `NotificationService` holds references to the abstraction and invokes `.send()` dynamically; the JVM dispatches execution to the appropriate concrete class at runtime without `if/else` checks.
- **Introducing New Notification Channels (e.g. `InAppNotification`):**
  A developer simply implements the `NotificationChannel` interface. The existing `NotificationService` code requires zero modifications, adhering to the Open/Closed Principle (Open for extension, Closed for modification).

---

### Concept Question 4: Composition vs. Aggregation (Organization, Department, Employee)
- **Organization and Department (Composition):**
  - *Definition:* Composition implies strong ownership and coincident lifecycles.
  - *Justification:* A Department is an intrinsic division of an Organization. When an Organization ceases to exist, its Departments are liquidated and cannot exist independently. Hence, `Organization` $\blacklozenge\text{---}$ `Department` is **Composition**.
- **Department and Employee (Aggregation):**
  - *Definition:* Aggregation is a weaker "has-a" relationship where child entities have an independent lifecycle.
  - *Justification:* Employees exist outside the Department; if a Department is disbanded, Employees can be reassigned to other departments or remain independent professionals. Hence, `Department` $\lozenge\text{---}$ `Employee` is **Aggregation**.

---

### Concept Question 5: UML Multiplicity vs. Business Logic (Student and Course)
- **Multiplicity Description:** The relationship between `Student` and `Course` is many-to-many ($* \text{ to } *$, or $0..* \text{ to } 0..*$), meaning a Student can enroll in zero or more Courses, and a Course can be taken by zero or more Students.
- **Why Multiplicity Alone Cannot Enforce No-Duplicates:**
  - UML multiplicity defines cardinality constraints on instances (how many instances can be linked at a conceptual level). A standard $*$ relationship between collections merely states that multiple links are valid.
  - Multiplicity does not specify uniqueness semantics of individual pairs or the temporal rules of enrollment.
- **Enforcement Mechanisms:** The uniqueness rule (preventing duplicate enrollment of the same student in the same course) must be enforced by business logic, such as using a `Set` collection in the domain model, a unique compound key `(student_id, course_id)` in the database schema, or validation guards in an `EnrollmentService`.