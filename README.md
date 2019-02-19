# Wildlife-Tracker

## Project Author :

KEVIN MOKORO

## Project description :

Its an app that keeps track of wildlife,noting sightings of animals incluiding endangered species.

### Technologies Used :

In developing this site the following were used:
-   Java


### Recreating the DATABASE :

In PSQL:

    CREATE DATABASE wildlife_tracker;
    CREATE TABLE animals (id serial PRIMARY KEY, name varchar, age varchar, health varchar,endangered boolean);
    CREATE TABLE sightings (id serial PRIMARY KEY, location varchar,rangerName varchar,date_sighted timestamp);
    CREATE TABLE animals_sightings(animal_id int, sighting_id int)


## Setup link to page :

Use the link below to _VISIT SITE_ : [heroku](http://damp-mountain-79857.herokuapp.com/)

### usage
Keeping wildlife check.

#### BDD

1.Get user's input.
2.If name save it to ranger name.
3.Add animal inputted.
4.If animal inputted is saved to records,if sighted can be registered to sightings,with details like place,time,and if endangered or general anaimal.

### support and contact details :

klosvoke1@gmail.com or send message to 0701957703

#### Contributing

If you notice any changes that are necessary,discuss the changes with the email above.Any argument is welcome for discussion.

#### Copyright and license

Copyright <2018> <KEVIN MOKORO>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
