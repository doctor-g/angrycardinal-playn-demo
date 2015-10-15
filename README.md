# Angry Cardinal PlayN Demo
This is a demonstration of the [PlayN](http://playn.io) game programming library. 
It is designed to show some of the interesting features of PlayN, particularly
for the purpose of teaching game programming to undergraduate Computer Science
students. This is a technology demo, not a complete or playable game.

The demonstration was originally designed for the [Consortium of Computing
Sciences in Colleges Midwest](http://ccsc.org/midwest) 2015 Conference.
The corresponding article is:
* [Paul Gestwicki. 2015. Teaching game programming with PlayN. Journal of Computing Sciences in Colleges 31, 1 (October 2015), 90-97.](http://dl.acm.org/citation.cfm?id=2831373.2831388)

## Project Structure

A series of branches is used to walk through the demonstration. Here is a summary of what each branch contains.

* [master](https://github.com/doctor-g/angrycardinal-playn-demo) is a blank PlayN project, configured for development with [IntelliJ IDEA Community Edition](http://www.jetbrains.org/pages/viewpage.action?pageId=983211) using [git](https://git-scm.com/).
* [1-charlie-image](https://github.com/doctor-g/angrycardinal-playn-demo/tree/1-charlie-image) introduces a single custom image: [Charlie Cardinal](https://en.wikipedia.org/wiki/Charlie_Cardinal), the mascot of [Ball State University](http://www.bsu.edu).
* [2-simple-interaction](https://github.com/doctor-g/angrycardinal-playn-demo/tree/2-simple-interaction) adds a slot for listening to mouse signals, so that pressing the mouse button toggles Charlie's motion across the screen.
* [3-tripleplay-ui](https://github.com/doctor-g/angrycardinal-playn-demo/tree/3-tripleplay-ui) introduces the (TriplePlay)[https://github.com/threerings/tripleplay] library, with corresponding changes to the project configuration files. This branch uses [TriplePlay's ScreenStack](https://github.com/threerings/tripleplay/blob/master/core/src/main/java/tripleplay/game/ScreenStack.java) to show a title screen before the "game".
* [4-score](https://github.com/doctor-g/angrycardinal-playn-demo/tree/4-score) adds a score in order to demonstrate how the [React](https://github.com/threerings/react) library can be used for [functional reactive programming](https://en.wikipedia.org/wiki/Functional_reactive_programming). Charlie's motion across the screen earns points, and the HUD updates itself based on the changing score.
* [5-feedback](https://github.com/doctor-g/angrycardinal-playn-demo/tree/5-feedback) demonstrates [TriplePlay's animation system](https://github.com/threerings/tripleplay/tree/master/core/src/main/java/tripleplay/anim), which provides a [fluent interface](https://en.wikipedia.org/wiki/Fluent_interface) for constructing animations. In this case, the player sees a congratulatory message for each fifty points earned.
