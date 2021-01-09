# online-shopping
1.	Summary:
In the final project of Info 6250, I build a simple shopping platform called “Buy it!” via Spring MVC and Hibernate. Like Amazon, people can view and buy various items on the platform or set up their own stores and sell items through the platform. The platform is equipped with all required functions for sellers and customers, hoping to simulate an environment that can really benefit people’s daily life.

2.	Functions:
It supports main functions as following:

  a.	User Register/Login :
Both sellers and customers can register an account in the platform. Once registered, user needs to type the accurate username and password to log in to the platform.

  b.	(Sellers) Manage Stores and Items:
Sellers can create new store or delete existing stores. For each store, seller can manage items in it, including adding new item, updating item, deleting item. For each item, seller should assign the fundamental attributes like name, amount, price, description etc. 

  c.	(Customer) Shopping
Customers can view all items in the platform, or select the key words to search for the very items. Once decided, customer can add items into his shopping cart. In the shopping cart, customer can also edit the items selected and see the total price of this cart. Once checked out, it will automatically generate an order, and the cart will be cleared at the same time. Also, the remaining amount of those items will be updated.

  d.	View Orders
After customers check out, orders will be generated. Both customers and sellers can view orders that they are involved. The date and total price will be added too. 
 
  e.	(Customer) Give review
While customers viewing their orders, they can give reviews to all the items that they bought. The review contains rate and feedback. Customers can give a rate from 1 to 5 and some words as feedback to evaluate the experience. Once submitted, the rate and feedback will be applied to the item information page that other customers and sellers can view.

  f.	Others
All the stores/items/carts realized CRUD functions. Also, the numbers should be validated as meaningful, like price and amount can’t be negative numbers, The item amount checked out can’t be larger than the amount remains. Moreover, the input area is validated by interceptors.
