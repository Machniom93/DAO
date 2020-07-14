# DAO
I create this project after passing second level of course in CodersLab. \
This project brings together various skills from __OOP__ and __MySQL__.
## What programme is that?
My program is _User_ data management.\
The program is based on a combination of object-oriented programming with a database called _workshop2_. \
You can perform actions on the database using the methods of the ___UserDao___ class.
## How it works?
_Workshop2_ database has table _users_. One row in table _users_ represents one object of _User_ class.\
In _MainDao_ class you can create object of _UserDao_ class.\
On this object you can call its methods:
1) __create__ - this method accepts one parameter, the User class object. The object is saved as a new row in the database;
2) __read__ - the method takes one parameter, row id to load. It reads this row from the database and turns it into an object. \
The method returns a User or null class object if the given id does not exist in our database;
3) __update__ - changes user data that already exists in the database. This method accepts one parameter, the User class object, which will be modified. \
To use this method, you must first call the ___read___ method.  The object thus loaded can be modified using setters. \
Then the changed user data can be saved to the database by calling the ___update___ method;
4) __delete__ - the method accepts one parameter, row id to be deleted. Removes user data from the database;
5) __findAll__ - This method does not take parameters, but returns an array of objects of the _User_ class. \
The ___findAll___ method reads all rows from the database and turns them into objects.