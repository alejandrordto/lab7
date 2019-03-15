var app = (function () {
    var cinema;
    var functions;
    var getCinema =function(){
        var name= $("#cinema").val(); 
        var callback= function(cine){
                     var table= $("table tbody").find('input[name="table"]');
                     
		}
        var funciones =function(cine){
            
             return cine[0].functions;
        }
        var func = apimock.getCinemaByName(name,funciones);
           console.log()
        }
       
    return{
    getCinema:getCinema
};
    
})();