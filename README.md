## einvite
EInvite Application

#Non-functional requirements: 

As A User, the application should adapt to Desktop, Mobile equally well. Response webdesign. Simple example is redbus.in 

The application should have the following functionalities 
+ Notifications - Websockets 
+ Location based services 
+ User should be able to enter a location if location is not turned on 
+ Integrate well with social networks like Facebook, Whatsapp, Linkedin, Phone contacts 

The application will have most pages without having to login. Pages which require login would be 
-

+ Understand how webhook works - See if we can integrate it 
+ Explore if we can integrate slack 
+ Deploy on AWS 
+ Check Amazon SNS - Amazon Simple notification services
+ Payments Integration

##Functional requirements: 

+ Host should be able to create an event - Event name, Date, Time, - Check if any external API available - Google event 
+ There can be multiple hosts for the event, host can assign other friends as hosts 
+ Host should be able to add a location for the event - Later integrate with Google maps 
+ Host should be able to create an invitation - Something fancy, look for api that can integrate well - canva.com 
+ Host should be able to invite friends to an event 
+ Host should be able to assign work to friends 
+ Host should be able to accept the invitation, RSVP, specify his preference in terms of food, etc 
+ Host should be able to add work related to the event - Like Order a Cake, Book a location, Pick a photographer, decoration, - Integrate with localsearch apis like yellow pages, truelocal, gumtree, etc - Long term goal is to build something of our own 
+ Host should be able to send messages/notification to all guests related to that event 
+ Host should be able to add expenses for the event 
+ Host should be able to split the expenses for the event with other guests/friends 
+ Friends can split the tab for the event, sponsor for part of the event 
+ Friends can send gifts to host for the event 
+ Host / Friends should be able to share photos/videos related to the event 
+ Host/Friends should be able to view public events at their location - Integrate with eventbrite, meetups, 
+ Host/Friends should be able to add reviews for location, cake, photographers, etc 
+ Host should be able to make payment to Merchant from the app 
+ Host should be able to conduct a poll - Check webhooks

##Merchant login 

+ Merchants should be able to see their orders 
+ Should be able to send a quote / messages for the event to host(s) 
+ Accept / Decline the order 
+ Add review for the user / host 
+ Should be added to his calendar / Block his calendar 
+ Should be able to add more users who can login as merchant 
+ Should be able to verify that he owns the business
