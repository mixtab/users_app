This is sample app with base implementation of:

- Hilt
- Retrofit
- Navigation Component
- Repository and UseCase
- MVVM patterns
- Clean Architecture

App functionality

App loads users with pagination from https://api.github.com/ and shows it via RecyclerView.
Every user item  contain such information: avatar preview and user login.

By click on user is open user detail screen and show next information : user avatar
preview, name, url, public repositories, public gists and followers. 

By pressing url leads to detailed user information on the web.

