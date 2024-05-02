## Gallery App using Jetpack Compose

## Screenshoot
<img width="315" alt="Screenshot 2024-05-02 at 11 47 34" src="https://github.com/rakai77/MyGallery/assets/58464856/80bb395f-9821-42fd-b9f7-1b07d1365dd5">
<img width="315" alt="Screenshot 2024-05-02 at 11 47 34" src="https://github.com/rakai77/MyGallery/assets/58464856/5fba7f27-f869-4711-92d6-d7a7972dc835">
<img width="318" alt="Screenshot 2024-05-02 at 11 50 30" src="https://github.com/rakai77/MyGallery/assets/58464856/c761d8f9-9560-428d-8a77-b2f167dca53c">


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

database information:
this database only provides users on local storage from your device.
