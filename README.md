# Test_IDnow Application

## Description
This project involves developing an application that utilizes a RESTful API called DummyJson to fetch relevant JSON data. It includes features such as capturing a picture, displaying a preview along with a description fetched from the API. The application consists of a login screen, a product listing screen with search functionality, and a detailed screen presenting information about the selected product. Data retrieval from the API is facilitated through Kotlin and Retrofit library. 

## Used Technologies

- Kotlin programming language
- Android Studio IDE 
- Retrofit library for making HTTP calls and parsing JSON responses
- Gson Converter for serializing and deserializing JSON objects


## Architecture

The architecture that I presented to you is a variation of the "Clean Architecture" proposed by Robert C. Martin (Uncle Bob).
In Clean Architecture, the application is divided into concentric layers, where each layer has its own responsibilities and dependencies. The outermost layers depend on the innermost layers, but the innermost layers do not depend on the outermost layers.
Here is how the packages I presented to you could be mapped to the Clean Architecture layers:

     1. di: This layer corresponds to the “Frameworks and Drivers” layer of Clean Architecture. It contains platform-specific dependencies and adapters for input/output interfaces.
     
     2. network: This layer corresponds to the “Interface Adapters” layer of Clean Architecture. It contains adapters for input/output interfaces, such as API services.
     
     3. product: This layer corresponds to the “Domain Layer” of Clean Architecture. It contains data models, use cases, and domain entities.
     
     4. adapter: This layer also corresponds to the “Interface Presentation” layer of Clean Architecture. It contains adapters for navigation and presentation.
     
the goal was  to separate the responsibilities of each layer to make your application easier to maintain and develop.



### Login

- You can utilize one of the registered users on dummyjson.com/users to log in to the website. ex: username	= "atuny0"  &  password = "9uQFF1Lh"
- <img src="https://github.com/adelboudrahem/Test_IDnow/assets/28858732/803fa3d4-d018-4142-80c6-17788d1666aa" width=50% height=50%>



### Search

- You can utilize the search bar located at the top of the main page to search for products. The search results will be displayed on the same page for your convenience.
- <img src="https://github.com/adelboudrahem/Test_IDnow/assets/28858732/c1b51dc7-e749-4ba4-bb9b-955dcde9df7d" width=50% height=50%>



### Product Detail

- You can view the details of products registered on dummyjson.com/products directly within the application.
- <img src="https://github.com/adelboudrahem/Test_IDnow/assets/28858732/1a662f5b-9730-4fec-879a-400ab3fb3d11" width=50% height=50%>








