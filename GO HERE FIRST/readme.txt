this project was made for the application entry in belatrix. please mind the errors, every comment in how to improve is well received :D

the project works in spring boot and creates the files with a POST request to the "root/urlparser/process" URI. if you´re running it locally the url would be localhost:8080/urlparser/process, i did my testing
using postman.

the raw body of the request is something like this:
{
"path":"C:\\Users\\welco\\Desktop\\urls.txt",
"filter":{"mention":"true", "properName":"true", "hashtag":"true"}
}
NOTE: neither the path or the filters are mandatory, they are optional, if the path is not defined it takes the default folder´s file (C://belatrix/urls.txt) you have to copy the belatrix folder in C://
in case the filters are not defined it uses all 3(hashtag, twitter and proper names)

hope u guys like my work, i´m looking forward to learn a lot from you.