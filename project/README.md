# Desert Scenery Generator

Advanced Data Structures Quarter 1 project, using Java Swing and a custom
doubly linked list. Requires JDK 8 or newer.

## Run

From this folder:

```sh
javac *.java
java Runner
```

## Features

- A drawn desert background with sky, sun, mountains, and sand.
- Five multi-shape items: cactus, rock, tumbleweed, pyramid, and scorpion.
- Each Add button places one item at a random position on the sand.
- Each adjacent Remove button randomly removes one item of its own type.
- Randomize moves every item; Clear removes all items.
- Selected challenge: click an item to delete it. When items overlap,
  the topmost item's rectangular click area is checked first.
- The canvas is 800 by 600 pixels; the controls fit within the assignment's
  1920 by 1080 window limit.

`Node<E>` stores data and next/previous links. `DLList<E>` manages the head,
tail, size, addition, indexed access, removal, and clearing. All scenery
items are stored only in `DLList<Scenery>`; no Java collection is used to
store them. Drawing and randomizing walk forward through the nodes, while
click deletion walks backward.

The desert theme still needs Mr. Nguyen's approval.

## Code style

The program uses explicit `if`/`else` branches, loops, constructors, private
fields, getters, and inherited drawing methods. Methods and loop bodies use
braces and consistent four-space indentation. Button events use a named
`actionPerformed` method; there are no lambda expressions, streams, or
reflection in the application.

Swing event listeners, generic nodes, and doubly linked lists are extensions
needed for this project beyond the usual AP Computer Science A core. The
implementation keeps those parts explicit, with comments explaining node
traversal and random removal.
