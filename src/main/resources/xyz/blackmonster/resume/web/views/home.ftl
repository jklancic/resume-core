<#-- @ftlvariable name="" type="xyz.blackmonster.resume.web.views.HomeView" -->
<html>
<head>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>
        axios.get('https://api.chucknorris.io/jokes/random')
                .then(function (response) {
                    var joke = response.data["value"];
                    document.getElementById("joke").innerHTML = joke;
                })
                .catch(function (error) {
                    document.getElementById("joke").innerHTML = "No joke today :(";
                    console.log(error);
                });
    </script>
</head>
<body>
<h1>Random Chuck Norris fact</h1>
<p id="joke"></p>
</body>
</html>
