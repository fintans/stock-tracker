Build/Test/Run  
In project directory run:  
mvn clean install  
mvn test  
mvn spring-boot:run  

How to use:  

Use https://www.postman.com/  

POST https://rhubarb-surprise-47059.herokuapp.com/api/users/register using any non previously existing user name or password:  
{
    "username": "user",
    "password": "pass"
}

Search for stocks:  
GET https://rhubarb-surprise-47059.herokuapp.com/api/stock/nio with Basic auth enabled using user name and password you registered with  

Add to favourites  
PUT https://rhubarb-surprise-47059.herokuapp.com/api/users/favourites/tsla with Basic auth enabled using user name and password you registered with  

Remove from favourites  
DELETE https://rhubarb-surprise-47059.herokuapp.com/api/users/favourites/tsla with Basic auth enabled using user name and password you registered with  


Thought process  
I focused on creating the back end peice of functionality for the overall solution of the project. I added very basic user registration to simulate the ability for users
to log in so they can view thier favorite stocks.
The app allows user to regiter. The user can then query other end points with basic auth enabled using thier previously registered user name and password.
The app then checks the db against the user retrieved from the basic auth in the request. If there is a match, the user can successfully carry out thier request as they are
an authenticted user. This is also how stocks are saved for the user in the db.

Things to fix:  
Adds null to user favorites if they search for a non existant stock  
Allows search for any available stock from AlphaVantage, not just NYSE stocks.  

Things to improve:  
Currently the app uses a blocking request to save stocks to the db (user favourites), a non blocking call would be better.  
The app needs to send a new request to get updated stock info rather than using a stream, where user would have real time stock updates.  
Registration is very basic - no password/email validation requirments.  
In the list of the users favorite stock, the price is the same as it was when caputred during the first request. This isn't useful for getting live updates, but could be used
to capture the price of the stock when the user first added it. Currently the front end would have to make multiple calls for each stock in the user favorites to get updated stock info (e.g. for the stock in the user favorites, query /stocks/eg to get updated price info).  
More test to improve code coverage  
There could be many more features added to this app from basic stock info processing to simulating of buying and selling shares with a profit/loss for each user.
