# Module-2 Project

## Learning Goals

- Implement a class with instance variables, a constructor, and methods that
  access and mutate the instance variables.
- Implement an application with multiple classes.
- Implement various types of class associations.

## Instructions

Fork and clone this project.
The starter code contains several empty classes that
you will develop incrementally as you learn the material
in this module.

![uml module2 project](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/uml_module2_project.png)

## Task #1 - The `Concert` Class

The `Concert` class encapsulates data about:
- the artist performing at a music concert
- the number of tickets available for purchase
- the number of people on the wait list

(1) Add instance variables in the following order to the `Concert` class:

- A String named `performer`.
- An int named `available`.
- An int named `waitlist`.

(2) Add a constructor that takes two parameters to initialize the `performer` and
`available` instance variables.  The `waitlist` instance variable
should be initialized to a default value of 0.

(3) Use IntelliJ to generate getter methods for each of the instance variables.

(4) Use IntelliJ to generate a `toString()` method, selecting all fields.

(5) Edit the `ConcertTest` Junit test class to test the constructor, getters,
and `toString` methods:

```java
class ConcertTest {

  private Concert c1, c2;

  @BeforeEach
  void setup() {
    c1 = new Concert("The Weeknd", 10);
    c2 = new Concert("Harry Styles", 2);
  }

  @Test
  void constructor() {
    assertEquals("The Weeknd", c1.getPerformer());
    assertEquals(10, c1.getAvailable());
    assertEquals(0, c1.getWaitlist());

    assertEquals("Harry Styles", c2.getPerformer());
    assertEquals(2, c2.getAvailable());
    assertEquals(0, c2.getWaitlist());
  }

  @Test
  void testToString() {
    assertEquals("Concert{performer='The Weeknd', available=10, waitlist=0}", c1.toString());
    assertEquals("Concert{performer='Harry Styles', available=2, waitlist=0}", c2.toString());
  }

}
```

Run the Junit tests to ensure the code passes the tests.

Once you have that working, edit the `Concert` class to add the following methods:

- A method named `purchaseTicket()` that takes no parameters and returns a `boolean`.
  - If the number of available tickets is positive, decrement the number
    of available tickets by 1 and return `true`.  Return `false` if there are
    no tickets available.
- A method named `addtoWaitList()` that takes no parameters and does not return a value.
  - Increment the `waitlist` value by 1.

Edit the `ConcertTest` Junit test class to add the following test methods.

```java
@Test
void purchaseTicket() {
    assertEquals(10, c1.getAvailable());
    assertTrue(c1.purchaseTicket());
    assertEquals(9, c1.getAvailable());
    assertTrue(c1.purchaseTicket());
    assertEquals(8, c1.getAvailable());

    assertEquals(2, c2.getAvailable());
    assertTrue(c2.purchaseTicket());
    assertEquals(1, c2.getAvailable());
    assertTrue(c2.purchaseTicket());
    assertEquals(0, c2.getAvailable());
    //no tickets left, remain at 0
    assertFalse(c2.purchaseTicket());
    assertEquals(0, c2.getAvailable());
    }

@Test
void addToWaitlist() {
    assertEquals(0, c1.getWaitlist());
    c1.addToWaitlist();
    assertEquals(1, c1.getWaitlist());
    c1.addToWaitlist();
    assertEquals(2, c1.getWaitlist());

    assertEquals(0, c2.getWaitlist());
    c2.addToWaitlist();
    assertEquals(1, c2.getWaitlist());
    c2.addToWaitlist();
    assertEquals(2, c2.getWaitlist());
    }
```


## Task #2 - The `ConcertRepository` class

The `ConcertRepository` class will use an array to
encapsulate a collection of `Concert` class instances.
The `ConcertRepository` class models a subset of
functionality found in the `ArrayList`
class that is part of the Java Collections Framework.


(1) Edit the `ConcertRepository` class to add two instance variables:

- An array named `concerts` that stores `Concert` class instances.
- An int named `currentSize` that stores the number of `Concert` objects
  that have been added to the array.  This value may differ from the array size,
  which represents the maximum number of concerts that may be added to the array.


(2) Create a constructor for the `ConcertRepository` that takes one parameter named
`maxSize`.  The constructor should initialize the array size using the `maxSize` variable.
Note that initially all elements in the array will be `null` since the array
stores instances of the reference type  `Concert`.

For example, the call `new ConcertRepository(3)` would create an object that looks
like the following, with an array of size 3.

![new ConcertRepository of size 3](https://curriculum-content.s3.amazonaws.com/6676/project/concertrepository_size3.png)

(3) Generate a getter method for the `currentSize` instance variable named `getCurrentSize`.

(4) Create a method named `add` that takes one parameter and returns a `boolean`.
The parameter type should be `Concert`.

- The `currentSize` instance variable represents the number of concerts that have
  been added to the array. Initially this value is 0.
- If the array is not full (i.e. `currentSize` is less than the array length),
  add the parameter object into the array using `currentSize` as the index.  Increment
  `currentSize` and return `true` to indicate the concert was added to the repository.
- If the array is already full, simply return `false` to indicate the concert
  could not be added to the repository.

For example, let's step through several calls to the `add` method.
The first three calls return `true` since there is room to add a `Concert`
in the array.  Note the value of `currentSize` is incremented
after inserting the object into the array.

`repository.add(new Concert("Artist0", 1000))`

![add one concert into the repository](https://curriculum-content.s3.amazonaws.com/6676/project/concertrepository_add1.png)

`repository.add(new Concert("Artist1", 2000))`

![add second concert into the repository](https://curriculum-content.s3.amazonaws.com/6676/project/concertrepository_add2.png)

`repository.add(new Concert("Artist2", 3000))`

![add third concert into the repository](https://curriculum-content.s3.amazonaws.com/6676/project/concertrepository_add3.png)


Calling `add()` again when the array is full results in the method returning  `false`,
and the `ConcertRepository` object state does not change:

`repository.add(new Concert("Artist3", 4000))`

![fail to add fourth concert into the repository](https://curriculum-content.s3.amazonaws.com/6676/project/concertrepository_add3.png)


(5) Create a method named `get` that takes an int parameter and returns a `Concert` object.
The method should return a `Concert` object using the parameter as the index
into the array, or return `null` if the index is out of bounds.  The method should not
throw an exception.

(6) Edit the `ConcertRepositoryTest` Junit class to test the constructor and methods.
You may want to add one method at a time and use the debugger if your code does not work.

```java

class ConcertRepositoryTest {

    @Test
    void constructor() {
        // repository can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);

        // current size is 0 since no concerts have been added
        assertEquals(0, repository.getCurrentSize());
    }

    @Test
    void addGet1() {
        // repository can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);
        assertEquals(0, repository.getCurrentSize());

        // add a concert
        assertTrue(repository.add(new Concert("Artist0" , 1000)));
        assertEquals(1, repository.getCurrentSize());

        // retrieve the concert using index 0
        Concert c = repository.get(0);
        assertEquals("Artist0", c.getPerformer());
        assertEquals(1000, c.getAvailable());
        assertEquals(0, c.getWaitlist());

    }

    @Test
    void addGet5() {
        // repository can hold up to 5 concerts
        ConcertRepository repository = new ConcertRepository(5);
        assertEquals(0, repository.getCurrentSize());

        // add 5 concerts
        for (int i=0; i<5; i++) {
            // add the concert
            assertTrue(repository.add(new Concert("Artist" + i, i*1000)));

            // retrieve the concert using index i
            assertNotNull(repository.get(i));

            // confirm the current size
            assertEquals(i + 1, repository.getCurrentSize());
        }

        // array is full, can't add another concert
        assertFalse(repository.add(new Concert("Another artist", 100)));

        // confirm the size did not increase
        assertEquals(5, repository.getCurrentSize());
    }

    @Test
    void getConcertState() {
        // array can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);

        // add 3 concerts
        assertTrue(repository.add(new Concert("The Weeknd", 1000)));
        assertTrue(repository.add(new Concert("Taylor Swift", 500)));
        assertTrue(repository.add(new Concert("Harry Styles", 20000)));
        assertEquals(3, repository.getCurrentSize());

        // confirm each concert was inserted in the correct array position
        assertEquals("The Weeknd", repository.get(0).getPerformer());
        assertEquals("Taylor Swift", repository.get(1).getPerformer());
        assertEquals("Harry Styles", repository.get(2).getPerformer());
    }

    @Test
    public void getOutOfBounds() {
        // array can hold up to 3 concerts
        ConcertRepository repository = new ConcertRepository(3);
        assertTrue(repository.add(new Concert("artist1", 1000)));
        assertTrue(repository.add(new Concert("artist2", 1000)));
        assertTrue(repository.add(new Concert("artist3", 1000)));

        // test that out of bounds index returns null
        assertNull(repository.get(-1));
        assertNull(repository.get(3));
    }
}
```

Run the Junit tests to ensure the code passes the tests.

Once you have that working, edit the `ConcertRepository` class
to add a method named `findByPerformer` that takes one parameter, a string
representing the name of the performer, and returns a `Concert`
object.  The method should iterate through the array looking for a concert
having a matching performer name.

*Be careful, the size of the array
does not represent the number of `Concert` objects in the array, so some
array elements may be null.*  The method should return `null` if the
repository does not contain a concert for that performer.

```java

@Test
void findByPerformer() {
    ConcertRepository repository = new ConcertRepository(2);
  
    repository.add(new Concert("Taylor Swift", 1000));
    repository.add(new Concert("The Weeknd", 500));
  
    Concert c1 = repository.findByPerformer("Taylor Swift");
    assertEquals("Taylor Swift", c1.getPerformer());
    assertEquals(1000, c1.getAvailable());
    assertEquals(0, c1.getWaitlist());
  
    Concert c2 = repository.findByPerformer("The Weeknd");
    assertEquals("The Weeknd", c2.getPerformer());
    assertEquals(500, c2.getAvailable());
    assertEquals(0, c2.getWaitlist());
  
    //unknown performer
    Concert c3 = repository.findByPerformer("Unknown Singer");
    assertNull(c3);

}
```

## Stretch Goal (Optional) - Case Insensitive Search


Edit the  `findByPerformer` method in the `ConcertRepository` class
to perform a case-insensitive search using the
performer name.  The method should ignore the case of the string
based as a parameter as well as the string representing the
performer name stored in the array.

Add the following  method to the `ConcertRepositoryTest` class
to test the new functionality:

```java
@Test
void caseInsensitiveFind() {
    ConcertRepository repository = new ConcertRepository(2);
  
    repository.add(new Concert("Taylor Swift", 1000));
    repository.add(new Concert("The Weeknd", 500));
  
    Concert c1 = repository.findByPerformer("TAYLOR swift");
    assertEquals("Taylor Swift", c1.getPerformer());
    assertEquals(1000, c1.getAvailable());
    assertEquals(0, c1.getWaitlist());

}
```


## Task #3 - The `ConcertService` and `Driver` classes


The `ConcertRepository` class encapsulates a collection of `Concert` objects
and provides methods to add a new concert, get a concert by index, and find
a concert by performer name.  However, the `ConcertRepository`
class does not provide business logic or input/output interaction
with the user.    This will be handled in two additional classes:

- The `Driver` class implements a command-line interface (CLI) to handle user input, repeatedly
  prompting the user with: "Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit:"
- The `Driver` class will delegate the request to a `ConcertService` object.
- The `ConcertService` class implements the business logic for the application.
  - Adding a concert:
    - Print a success message if the concert is added to the repository.
    - Print an error message if the repository already contains a concert for that performer.
    - Print an error message if the repository can't add a concert (array is full).
  - Display all concerts
    - Iterate and print each concert in the repository.
  - Purchase ticket:
    - Print a success message if the purchase was successful.
    - Print an error message if the repository does not contain a concert for the specified performer.
    - Print an error message if the repository contains the concert but the purchase is unsuccessful (no tickets available).
  - Add to waitlist:
    - Print a success message if the waitlist update was successful.
    - Print an error message if the repository does not contain a concert for the specified performer.



The `Driver` class will use the `ConcertService` class to perform the
actions requested by the user.  The `ConcertService` class in turn
uses the `ConcertRepository` class to manage the actual storage of `Concert`
objects in the array.

![uml module2 project](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/uml_module2_project.png)


We'll implement the `ConcertService` class first, then implement the CLI in the `Driver`.

## `ConcertService` class

(1) Edit the `ConcertService` class to add an instance variable to reference an instance of `ConcertRepository`
that stores a maximum of 3 concerts.

```java
private  ConcertRepository repository = new ConcertRepository(3);
```

(2) Edit the `ConcertService` class to add a method named `addConcert` that takes two
parameters: the performer name and the number of available tickets.  The method does not return a value.
- Call the `findByPerformer` method to test if a concert for that performer
  exists in the repository, and print "Unable to add concert" if one already does.
- If there is no existing concert for the performer, create a new `Concert` object
  and call the `add` method to add the new object to the repository.
  - Print "Added concert" if the `add` method returns `true`.
  - Print "Unable to add concert" if the `add` method returns `false`.

(3) Edit `ConcertServiceTest` to add two methods to test the new functionality:

```java
@Test
void addFull() {
    concertService.addConcert("Taylor Swift" , 100);
    concertService.addConcert("The Weeknd", 5000);
    concertService.addConcert("Harry Styles", 599);
    // Array size 3, can't add any more
    concertService.addConcert("Another Singer", 100);
    assertEquals("Added concert\n" +
                 "Added concert\n" +
                 "Added concert\n" +
                 "Unable to add concert",
                 outputStreamCaptor.toString().trim());
}

@Test
void duplicateAdd() {
    concertService.addConcert("Taylor Swift" , 100);
    concertService.addConcert("Taylor Swift" , 200);
    assertEquals("Added concert\n" +
                 "Unable to add concert",
                 outputStreamCaptor.toString().trim());
}
```

Run the Junit test and confirm your code.

(4) Edit the `ConcertService` class to
add a method named `displayConcerts` that takes no parameters and returns no value.
The method should call the repository's `get` method to retrieve and print each concert in the repository.

(5) Edit `ConcertServiceTest` to add two methods to test the new functionality:

```java
@Test
void displayEmpty() {
    concertService.displayConcerts();
    assertEquals("", outputStreamCaptor.toString().trim());
}

@Test
void displayNonEmpty() {
    concertService.addConcert("Taylor Swift" , 100);
    concertService.addConcert("The Weeknd", 5000);
    concertService.displayConcerts();
    assertEquals("Added concert\n" +
        "Added concert\n" +
        "Concert{performer='Taylor Swift', available=100, waitlist=0}\n" +
        "Concert{performer='The Weeknd', available=5000, waitlist=0}",
        outputStreamCaptor.toString().trim());
}
```

Run the Junit tests and confirm your code.

(6) Edit the `ConcertService` class to
add a method named `purchaseTicket`. The method takes one parameter, the performer name.
The method does not return a value.

- Call the `findByPerformer` method to test if a concert for that performer
  exists in the repository, and print "No concert for name", substituting the
  actual performer name, if there is no such concert.
- If there is a concert for the performer, call the `purchaseTicket` method on the given concert
  object.  Test the result returned from `purchaseTicket`.
  - Print "Ticket purchased" if the `purchaseTicket` method returns `true`.
  - Print "Ticket unavailable" if the `purchaseTicket` method returns `false`.

(7) Edit the `ConcertServiceTest` class to add Junit tests:

```java
@Test
void purchaseTicket() {
    concertService.addConcert("Taylor Swift" , 3);
    concertService.purchaseTicket("Taylor Swift");
    concertService.purchaseTicket("Taylor Swift");
    concertService.purchaseTicket("Taylor Swift");
    // sold out, ticket unavailable
    concertService.purchaseTicket("Taylor Swift");
    assertEquals("Added concert\n" +
                 "Ticket purchased\n" +
                 "Ticket purchased\n" +
                 "Ticket purchased\n" +
                 "Ticket unavailable",
            outputStreamCaptor.toString().trim());
}

@Test
void purchaseTicketUnknownArtist() {
    concertService.addConcert("Taylor Swift" , 1000);
    concertService.purchaseTicket("Unknown Singer");
    assertEquals("Added concert\n" +
                  "No concert for Unknown Singer",
            outputStreamCaptor.toString().trim());
}
```

Run the Junit tests and confirm your code.

(8) Edit the `ConcertService` class to add a method `addToWaitlist` that takes
one parameter, the performer name.  The method does not return a value.

- Call the `findByPerformer` method to test if a concert for that performer
  exists in the repository, and print "No concert for name", substituting the
  actual performer name, if there is no such concert.
- If there is a concert for the performer, call the `addToWaitlist` method on the given concert
  object and then print "Added to waitlist".

(9) Edit the `ConcertServiceTest` class to add Junit tests:

```java
@Test
void addToWaitlist() {
    concertService.addConcert("Taylor Swift" , 100);
    concertService.addConcert("The Weeknd", 5000);
    concertService.addToWaitlist("Taylor Swift");
    concertService.addToWaitlist("Taylor Swift");
    concertService.addToWaitlist("The Weeknd");
    // no concert
    concertService.addToWaitlist("Unknown Singer");

    assertEquals("Added concert\n" +
                 "Added concert\n" +
                 "Added to waitlist\n" +
                 "Added to waitlist\n" +
                 "Added to waitlist\n" +
                 "No concert for Unknown Singer",
                outputStreamCaptor.toString().trim());
}
```


## `Driver` class

Edit the `Driver` class to provide a CLI for managing concerts as shown:

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private static ConcertService service = new ConcertService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String prompt = "Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: ";
        String action;

        // Prompt the user for an action
        System.out.println(prompt);
        action = scanner.nextLine();

        // Loop until the user enters q to quit
        while (!action.equals("q")) {
            switch (action) {
                case "a":
                    System.out.println("Enter the performer's name:");
                    String performer = scanner.nextLine();
                    try {
                        System.out.println("Enter the number of available tickets:");
                        int available = scanner.nextInt();
                        scanner.nextLine();  //consume the rest of the input
                        service.addConcert(performer, available);
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Value was not an integer");
                    }
                case "d":
                    service.displayConcerts();
                    break;
                case "p":
                    System.out.println("Enter the performer's name:");
                    service.purchaseTicket( scanner.nextLine() );
                    break;
                case "w":
                    System.out.println("Enter the performer's name:");
                    service.addToWaitlist( scanner.nextLine() );
                    break;
                default:
                    System.out.println("Invalid choice: " + action);
            }

            // get the next action
            System.out.println(prompt);
            action = scanner.nextLine();
        }
    }

} 
```

Run the program and test the various actions.  For example:

```text
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
a
Enter the performer's name:
Taylor Swift
Enter the number of available tickets:
1000
Added concert
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
a
Enter the performer's name:
The Weeknd
Enter the number of available tickets:
2
Added concert
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Concert{performer='The Weeknd', available=2, waitlist=0}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
p
Enter the performer's name:
The Weeknd
Ticket purchased
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Concert{performer='The Weeknd', available=1, waitlist=0}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
p
Enter the performer's name:
The Weeknd
Ticket purchased
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Concert{performer='The Weeknd', available=0, waitlist=0}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
p
Enter the performer's name:
The Weeknd
Ticket unavailable
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Concert{performer='The Weeknd', available=0, waitlist=0}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
p
Enter the performer's name:
The band
No concert for The band
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
a
Enter the performer's name:
Taylor Swift
Enter the number of available tickets:
500
Unable to add concert
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
w
Enter the performer's name:
The Weeknd
Added to waitlist
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=0}
Concert{performer='The Weeknd', available=0, waitlist=1}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
w
Enter the performer's name:
Taylor Swift
Added to waitlist
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=1}
Concert{performer='The Weeknd', available=0, waitlist=1}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
w
Enter the performer's name:
The band
No concert for The band
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
d
Concert{performer='Taylor Swift', available=1000, waitlist=1}
Concert{performer='The Weeknd', available=0, waitlist=1}
Select an action: a=add concert, d=display all concerts, p=purchase ticket, w=add to waitlist, q=quit: 
q
```
