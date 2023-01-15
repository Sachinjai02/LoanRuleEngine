Below API will be exposed after the deployment of this spring boot App.

POST:
http://localhost:8080/loan/offers

Sample request body:
```
{
    "userName": "Sachin Jain",
    "panCard":"ABCDEF",
    "age":30,
    "salary":90000
}
```
Negative request case:
```
{
"userName": "Sachin Jain",
"panCard":"",
"age":30,
"salary":90000
}
```



