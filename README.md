## Kotlin Native Multi Test

Kotlin Multiplatform test prototype to validate the following functionalities:

- Android and IOS shared code using Kotlin Native
- MVP: platform specific View implementation, common Presenter
- Multithreading via Coroutines
- HTTP via Ktor client
- JSON serilization via kotlinx.serialization
- Dependency Injection via Kodein
- Logging via platform specific APIs

### Todo:

- Investigate architecture patterns (Viper, unidirectional)
- Expand threading
- Persistence (SqlDelight)
