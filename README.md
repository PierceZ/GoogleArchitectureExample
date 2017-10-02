# Google Architecture Example
An example implementation of Google's architecture, found here: https://developer.android.com/topic/libraries/architecture/index.html

![Architecture diagram](https://i.imgur.com/xURcjt7.png)

Similar to the above image, this example inclues a repository with a local database and remote APIs. But, the local database is using [ObjectBox](http://objectbox.io) instead of Room. Also, the implementation of the network layer uses mock requests. Replacing it with real network requests with a library like Retrofit should be fairly straightforward though.
