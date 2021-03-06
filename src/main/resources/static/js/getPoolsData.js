//The very first version included reading APIs via frontend. Switched to backend

function getPoolsData(){
$.getJSON('../json/networks/Turtlecoin/turtlecoin-pools.json', function(data) {

    var date_from = new Date();
    var pools_hashrates = [];
    var pool_hashrates_list = {"date_from" : date_from};

data.pools.forEach(function(pool){

    var api_url = pool.api;
    var poolName = pool.name;

    if(pool.type == "forknote"){

        $.getJSON(api_url + 'stats', function(data) {

                var poolHashrate = data.pool.hashrate;

                pools_hashrates.push({"poolName" : poolName, "hashrate" : poolHashrate});

                console.log("Pool name: " + poolName + " Pool hashrate: " + parseInt(poolHashrate));
        });
    }
    else{
        $.getJSON(api_url + 'pool/stats', function(data) {

                var poolHashrate = data.pool_statistics.hashRate;

                console.log("Pool name: " + poolName + " Pool hashrate: " + parseInt(poolHashrate));

                pools_hashrates.push({"poolName" : poolName, "hashrate" : poolHashrate});

        });
    }
});

$.extend(pool_hashrates_list, {"pools: " : pools_hashrates});

console.log(pool_hashrates_list);

var myTimer = setInterval(function(){
    if (jQuery.active == 0){
        $.ajax({
              type: "POST",
              contentType : 'application/json; charset=utf-8',
              dataType : 'json',
              url: "/savePoolsData",
              data: JSON.stringify(pool_hashrates_list),
              success :function(result) {
                  console.log("Success!");
             }
          });
        clearInterval(myTimer);
    }
}, 1000);
});
}