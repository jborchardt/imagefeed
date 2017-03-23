# imagefeed
An Android image fetcher for Imgur. Made in less than 48 hours.

# Architecture
The architectural approach used in for this application is close to Uncle Bob's clean architecture, as described here: https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html

![alt tag](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture-8b00a9d7e2543fa9ca76b81b05066629.jpg)


In the heart of the app is the 'domain' module, which represents business logic seperated by use cases, AKA interactors. In the above image it is located in the orange Use Case layer.
This module is free from platform dependent code or dependencies to other of this application's modules.

On the outer layers are the 'presentation' and 'data' modules, which both utilize platform dependent code and know of the 'domain' layer. 
The 'presentation' module represents the View and Presenter part of the MVP pattern. The data module utilizes a repository pattern. The executed network calls are synchronous, as this module is only reponsible for retreiving data.

The 'app' module hosts activities and base functionality, such as navigation. 

## Variations
There are two points in which the chosen architecture is different from the above mentioned clean architecture. 

1. Interactors define their needs using interfaces, however are not defined by interfaces themselves. This represents the assumption that business logic in an app is unlikely to be exchanged. On the flipside, this makes the Presenters harder to test.
2. Data models are not mapped to models suitable for each layer. Instead, Interfaces are utilized (see com.jborchardt.imagefeed.data.model.ImgurImage). 

## Breaking the architecture
At two points, the above mentioned architecture is broken: Whenever the Glide library is used to load images. The image loading flow is triggered in the 'presentation' module without calling an Interactor or Repository. The desicion to include this library anyway was made out of time constraints and because it represents a proven concept and a well used and tested piece of code, which seemed sufficient for this usecase. 

# App structure
The app consists of an imagefeed, and an image details view. Both screens are able to handle portrait and landscape orientation without the need to recreate views.

## Image feed
The image feed shows all the image posts of Imgur's most viral feed. When the user scrolls close to the end of the feed, the next page of images is requested (paging). Loading is indicated by a horizontal progressbar in the actionbar.

## Image Details
The image details show the selected image in fullscreen and provides upvotes, downvotes, comments and views, as well as the image's titles as metadata.

# Open points
Given the short timeframe in which this app was created, room for improvement exists. The following comes to mind:
- Transitions: The translation from image feed to details invites for a shared item transition.
- Design in general: The author of this application consideres himself to be a programmer rather than a designer.
- Tests: Right now tests for interactors and repositories exist. UI tests don't.
- Dependency injection: Could be handled by a framework, like dagger.
