var express = require('express');
var app = express();
var path = require('path');

app.use(express.static('public'));
app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.get('/articles/:page', function(req, res){
	var page = req.params.page;
	console.log("requested page n:"+page);

	var articles = [];
	for (var i = (page*100 - 100); i < page*100; i++) {
		var randomNumber = Math.floor(Math.random() * 100) + 1;
		var article = {
			"name": "article" + randomNumber
		};
		if(i%2==0){
			article.url = "/img/img1.jpg"
		} else if(i%5==0) {
			article.url = "/img/img2.jpg"
		} else {
			article.url = "/img/img3.jpg"
		}
		articles.push(article);
	}

	res.send(articles);
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});