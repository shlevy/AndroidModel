AndroidModel
============
A library to make Android databse access simpler

TODO: Flesh out the README

What's with the tests?
----------------------
Everything I know about BDD comes from _The RSpec Book_. So I do things their way. Eventually, I would like to investigate BDD the Java way, if such exists, but for now, this works for me.

The tests are organized into two categories:

* *Features* are behaviour-level descriptions of the code. Each feature has one or more *scenarios*, which are written in a Given-When-Then format. Features try to describe the code on the level that the end-user will see it.
* *Specs* are unit-level descriptions of the code. They test the building blocks of the code in isolation from each other and any system resources.

The general development process goes: 

1. Figure out what I want the product to be able to do
2. Find or create a feature that covers that behaviour
3. Write a scenario that describes utilizing the behaviour I want
4. If necessary, change the code under test JUST ENOUGH to have the scenario compile
5. Run the scenario. It should fail the first time, unless the scenario is to document behaviour that was already implemented incidentally.
6. If it fails, figure out what aspect of the code is causing the failure and write a spec that specifies the behaviour that aspect of the code needs in order to not cause the failure
7. Run the spec. It should fail this time. 
8. Change the code under test JUST ENOUGH to have the spec pass
9. Run the scenario. If it fails, repeat 6-8 until it passes.
