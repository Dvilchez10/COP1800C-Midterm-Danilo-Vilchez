# Reasoning - COP1800C Midterm Workshop

## Exercise 1: Study Budget
* **E1-R1:** An `AND` (`&&`) operator is required because the business rule establishes a strict double condition: the student must have an active membership **and** have accumulated at least 4 complete study blocks to qualify for the discount.
    * *Incorrect input case:* If an `OR` (`||`) operator were used, a student with an inactive membership (`false`) who accumulates 4 study blocks would incorrectly receive the discount, since the logical condition would be met with just one of the true premises, violating the study center's policy.

* **E1-R2:** In Java, integer division (`int`) truncates any decimal portion. Therefore, the operation `20 / 100` evaluates to `0` instead of `0.20`. Multiplying any base fee by `0` results in a discount amount of zero, altering the final amount to be paid. To prevent this, a decimal data type (`double`) must be used by writing `0.20` or by forcing floating-point division (`20.0 / 100`).

---

## Exercise 2: Attendance Analyzer
* **E2-R1:** The average per cell is obtained by dividing the grand total accumulated visits by the total number of cells (the sum of the lengths of all rows, or the product of rows and columns if rectangular). To prevent truncation to zero or loss of decimals due to integer division, an explicit cast to `double` is required on at least one operand before performing the division.

* **E2-R2:** Its purpose is to safely catch conversion errors when a user inputs text, letters, or empty characters instead of the expected integer using `Integer.parseInt()`. This prevents the application from crashing, allowing the program to display a descriptive error message and cleanly resume the menu flow.
---
* **E2-R3:** The tie-breaking rule was implemented by iterating through the room totals array from left to right and using a strict greater-than comparison operator (`>`) instead of greater-than-or-equal (`>=`). Thus, if two rooms accumulate the exact same maximum visits, the lower index corresponding to the first found room is preserved. Establishing a deterministic policy avoids random or ambiguous behavior in the system's statistical reports.

## Exercise 3: Study Room
* **E3-R1:** Explain how constructor overloading and the `this()` delegation pattern protect object invariants and prevent code duplication.
    * **Answer:** Constructor overloading allows a class to provide multiple ways to initialize an object depending on the provided arguments (such as initializing a room with a custom capacity versus a default capacity). Using `this()` delegation enables one constructor to call another within the same class, centralizing initialization logic and ensuring that capacity rules, constraints, and valid initial states are enforced uniformly without repeating code.

* **E3-R2:** Explain why encapsulation (private fields with public getters) is critical for enforcing reservation rules safely.
    * **Answer:** Encapsulation hides the internal state (`reserved` and `capacity`) from external classes, preventing direct or invalid modifications (such as setting negative seats or exceeding maximum capacity). By routing changes exclusively through controlled public methods like `reserve()` and `cancel()`, the object can validate business rules and maintain data integrity at all times.

* **E3-R3:** Explain what an object alias is and how demonstrating it differs from object copying.
    * **Answer:** An alias occurs when two or more reference variables point to the exact same object instance in memory. Modifying the object's state through one reference (e.g., `aliasRoom.reserve(5)`) directly affects the state observed by the other reference (`originalRoom`). In contrast, object copying creates a distinct, independent instance with its own separate block of memory, meaning changes to one copy do not affect the other.
---

## Exercise 4: Campus Resource Reservation App

* **E4-R1:** Explain why a `CampusResource` reference can produce two different fee values. Distinguish overriding here from the overloading in Exercise 3.
    * **Answer:** A `CampusResource` reference can produce different fee values due to **runtime polymorphism (dynamic method dispatch)**. When `feePerSeat()` is called on a subclass instance (such as `StudySpace` or `ComputerLab`), Java dynamically invokes the specific overridden implementation defined in that concrete subclass rather than the abstract superclass method. This is distinct from the **overloading** seen in Exercise 3, where multiple methods share the same name within the *same* class but possess different parameter signatures, resolved statically at compile time.

* **E4-R2:** Explain the different responsibilities of abstract `CampusResource` and interface `Priced`.
    * **Answer:** The interface `Priced` defines a contract ensuring that any implementing class must provide a fee calculation method (`feePerSeat()`), focusing strictly on *behavioral capability* without holding any state. In contrast, the abstract class `CampusResource` serves as a foundational base class that inherits core state management and reservation logic from `StudyRoom`, implements common calculations like revenue and occupancy, and leaves specific fee determination abstract for its concrete subclasses.

* **E4-R3:** Explain how cancellation affects revenue and why summing every historically accepted reservation would be incorrect.
    * **Answer:** Cancellation decreases the current number of reserved seats (`reserved`), which directly lowers the total revenue when calculated dynamically via `getReserved() * feePerSeat()`. Summing every historically accepted reservation over time would be incorrect because it would count seats that were subsequently cancelled and freed up, failing to reflect the actual, current active financial state of the resource.

* **E4-R4:** Explain why rebuilding the catalog inside the menu loop would lose reservations.
    * **Answer:** Rebuilding or reinstantiating the `CampusResource[]` catalog inside the repeating menu loop would overwrite the array on every iteration, discarding all references to the active object instances currently existing in memory. Consequently, any seat reservations or state changes made during previous loop iterations would be lost, resetting all resources back to their initial default (unreserved) state.