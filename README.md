# MusicWiki

## Description

MusicWiki is an unofficial Last.fm app that contains information about different music genres, the albums, artists and tracks listed under the genre.

## Application Screenshots

## Code Structure

The sample application is created mainly by keeping MVVM Architecture into our point of vision.

Different layers of the project: 

- **Data layer:** The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. Defining the Room entity, setting up the DAO Interface and building the Database Builder is done in this layer.

- **Network layer:** Network layer provides network implementation of the application. It fetches genres, albums, artists, tracks and genreinfo from remote database using retrofit api. Data will be stored in local database via room persistence library. Retrofit api also implements network interceptor to check connection status.

- **UI layer:** The UI layer provides the UI implementation of the application. This layer internally implements MVVM (Model-View-ViewModel) architecture. It contains various fragments/activities, viewmodel and controller for the data.

## Package Structure

     com.example.androidMVVMApp        # Root Package
      .
      ├── data                         # For data handling.
      │   ├── db                       # Local Persistence Database. Room (SQLite) database.
      │   ├── model                    # Model Classes.
      │   └── repository               # Defining the GenreRepository and GenreItemsRepository classes which provide connection to LocalDataSource and RemoteDataSource classes.
      |
      ├── network                      # For handling remote database.
      │    
      ├── ui                           # Implements UI functionalities.
      │   ├── genre                    # Contains GenreActivity, GenreAdapter, GenreViewModel and GenreViewModelFactory classes to show list of genres.
      |   └── genreInfo                # Contains fragments and activity which shows genre as title, genre's information and list of albums, top tracks and top artists.
      │       ├── album                # Contains AlbumFragment, AlbumsAdapter, AlbumViewModel and AlbumViewModelFactory classes to show list of albums.
      │       ├── artist               # Contains ArtistFragment, ArtistsAdapter, ArtistViewModel and ArtistViewModelFactory classes to show list of artists.
      │       └── track                # Contains TracksFragment, TracksAdapter, TrackViewModel and TrackViewModelFactory classes to show list of tracks.
      |
      └── utils                        # Utility Classes / Kotlin extensions
      
      
## Technologies and Libraries

- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For working with asynchronous threading related task.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps. Thus they help us to separate business logic apart from the UI logic and helps us in designing proper architecture.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on configuration changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Retrofit](https://square.github.io/retrofit/) - Retrofit is a type-safe HTTP client for Android and Java – developed by Square (Dagger, Okhttp).
- [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android focused on smooth scrolling. Glide offers an easy to use API, a performant and extensible resource decoding pipeline and automatic resource pooling.
- [Circle Image View](https://github.com/hdodenhof/CircleImageView) - A fast circular ImageView perfect for profile images. This is based on RoundedImageView from Vince Mi which itself is based on techniques recommended by Romain Guy.

## Built With

* Android Studio

## Author
* <a href="https://github.com/aikansh2001yadav"> **Aikansh Yadav** </a>
                                                                         
