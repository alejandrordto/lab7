var app = (function () {
    var cinema;
    var functions = [];
    function recargar(cine) {
        for (var i = 0; i < functions.length; i++) {
            var numsit= count(functions[i].seats)
            var flag = "<tr><td>" + cine[0].name + "</td><td>" +
                    functions[i].movie.name + "</td><td>" + numsit + "</td><td>" +
                    functions[i].date + "</td></tr>";
            $("#tabla").append(flag);
        }
        function count(sit) {
            var cont = 0;
            for (var i = 0; i < sit.length; i++) {
                for (var j = 0; j < sit[i].length; j++) {
                    if (sit[i][j] == true) {
                        cont++;
                    }
                }
            }
            return cont;
        }
    }
    var getCinema = function () {
        var name = $("#cinema").val();
        var callback = function (cine) {
            cinema = cine;

        }
        var funciones = function (cine) {
            functions = cine[0].functions;
            recargar(cine);
            return functions;
        }
        var func = apimock.getCinemaByName(name, funciones);
        console.log()
    }

    return{
        getCinema: getCinema
    };

})();