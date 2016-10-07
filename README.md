# gitty
Gitty is android application that uses many various tools.
All the app does is call github's api and places the data onto a recycler view.

- Dagger 2 for dependency injection
  - allows me to abstract the unwanted details, have more readable code, and increases app launch time.
  
- Butterknife
  - helps me have more readable code, by getting rid of many findViewById calls.
  
- Retrofit
  - helps me call apis like the github api in a quick and readable manner.
  
- RxJava
  - helps with readability and works well with retrofit. 
  - easy to run code in a background thread and simplfies asynchronous chain operations.
 
- Data Binding
  - rather than having logic in the adapter's onBindView, the data is binded to a xml layout.
  
There is a lot more that you can do with these tools!
