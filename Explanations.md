Library usage explanations:
 - Retrofit: To allow the app to perform networking tasks.
 - Fresco: 	Fresco is an image loading library that works perfectly with retrofit since they both use OkHttp as its core.

5) Be able to consume information from a Webservice:
    By using the retrofit library the app was automatically network aware.

6) Be able to support a very extensive catalog (both from the visual side and from the architectural part):
    - Visual side:
        - The support library contains a view called RecyclerView which uses the ViewHolder pattern. This allows it to have an almost infinite amount items since it recycles and reutilizes the views when they leave the screen.
    - Architectural part:
    	- To hold a large amount of items I implemented a paging strategy to load the items progressively instead of all at once.

7) Be able to modify the data structure of the catalog from more than one single thread:
	By using RxJava the application is already multi-threaded. Since the data is stored in the adapter (which is in the main thread to allow comunication with the view) and its a code small to access a single instance of an object from multiple threads, each network opperation is run in a new thread and informs its result to the main thread to update the data.