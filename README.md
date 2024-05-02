## News App using Jetpack Compose

## Screenshoot
![Screenshot_1714622314](https://github.com/rakai77/MyGallery/assets/58464856/bcfc9db6-d607-46a1-86b5-7f56a112d1f0)


## Tech Stack : 
 1. MVVM pattern
 2. Room Database (for login and register)
 3. UI: Jetpack Compose
 4. Retrofit
 5. Hilt Dependency
 6. Repository and Usecase pattern
 7. Kotlin Coroutines
 8. Datastore

## Note :
https://jsonplaceholder.typicode.com/photos this API can't provide pagination rules.
pagination needs at least page and limit for query params (Difference behavior pagination between Web and Mobile App probably).
there is no "page", "limit" in the response, so the query params can't work properly in Android Apps

database infomation:
this database only provide user in local storage from your device.
