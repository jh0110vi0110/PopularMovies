# PopularMovies
Udacity Android Developer Nanodegree Project

## Setup
TMDb API Key is required.
1. Sign up for an account at [www.themoviedb.org](www.themoviedb.org) and get an API key.
2. In the utils.Network class, add your key to the line:
   ```
   private static final String API_KEY = "API KEY GOES HERE";
   ```

## UI
### Movie List
![Movie List](https://github.com/joshua-hilborn/PopularMovies/blob/master/img/MainActivity.png)
- Displayed as a grid of posters and titles
- Sort by Most Popular, Highest Rated, and Favorites (Saved locally)

### Detail view of the selected movie
![Detail View](https://github.com/joshua-hilborn/PopularMovies/blob/master/img/DetailActivity.png)
- Displays basic info for selected movie (name, summary, user rating, release date, image)
- Videos are linked and launch in a window if possible, or a browser if that fails
- Clicking the heart button will add the movie to favorites list.

## Libraries and Services
- Picasso 2.71828
- Android Architechture Components 1.1.1: (persistence and lifecycle)
