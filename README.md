# gitty
Gitty is android application that uses many various tools.

All the app does is call github's api and places the data onto a recycler view.

- Dagger 2 
  - dependency injection, by building my own dependency graph
  - allows me to abstract the unwanted details, have more readable code, and increases app launch time.
  
- Retrofit
  - helps me call apis like the github api in a quick and readable manner.
  
- RxJava
  - helps with readability and works well with retrofit. 
  - easy to run code in a background thread and simplfies asynchronous chain operations.
 
- Data Binding
  - rather than having logic in the adapter's onBindView, the data is binded to a xml layout.
  - by giving your views ids and wrapping your xml layouts in "`<layout> </layout>`", you can access your components from the compiled binding through DataBindingUtil.
  
There is a lot more that you can do with these tools!
