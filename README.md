# ToyStoreP3

**Design Decisions:** We organized the files in the following categories: activities, adapters, and basic classes.
Google released Material Design to create consistency across their applications and we decided to implement the RecyclerView
because it plays on those materials and it allows our app to fall into that new landscape.
We used an asynchronized task to pull the data from the URL and populate our store.
We kept the interface pretty simple, with three screens: a home screen to go to the search, 
a store screen (that includes dragging to reorder, adding and deleting items from the cart, a checkout button, and a reset button), 
and a cart screen to view your cart and go to Google Maps.

**Problems:** We haven't noticed any problems with our code and everything has worked perfectly for us.

**Additional Features:** We decided to create these add-ons:
* Cart allows user to go to Google Maps app and see a Toys'R'Us in New Jersey.
* RecyclerView implements Material Design and provides a sense of familiarity for the user.
* Reset button allows user to clear the cart and refresh the page instead of having to navigate back to the home screen and search again.

We hope you enjoy it,

Parker Wilf & Timothy Shaker
