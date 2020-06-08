const baseApiUrl = 'http://localhost:8080/api/';
const timeout = 3000;

function loadRest() {
    var urlParams = new URLSearchParams(window.location.search);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                //  console.log("response text: " + xhr.responseText);
                const parsedJson = JSON.parse(xhr.responseText);

                let id = parsedJson.id;
                let name = parsedJson.name;
                let city = parsedJson.city;
                let address = parsedJson.address;
                let district = parsedJson.district;
                let freeTables = parsedJson.freeTables;
                let maxTables = parsedJson.maxTables;
                let lat = parsedJson.lat;
                let lng = parsedJson.lng;
                let homepage = parsedJson.homepage;
                let phone = parsedJson.phone;
                let username = parsedJson.username;

                document.getElementById("name").innerHTML = name;
                document.getElementById("editname").innerHTML = name;
                document.getElementById("editaddress").innerHTML = address;
                document.getElementById("editcity").innerHTML = city;
                document.getElementById("edithomepage").innerHTML = homepage;
                document.getElementById("editphone").innerHTML = phone;
                document.getElementById("editfreetables").innerHTML = freeTables;
                document.getElementById("editmaxtables").innerHTML = maxTables;

                console.log("name" + name);
                let plusfreeTables = freeTables + 1;

                function plusTable() {
                    var settings = {
                        "url": baseApiUrl + 'restaurants/' + id,
                        "method": "PUT",
                        "timeout": timeout,
                        "headers": {
                            "Content-Type": "application/json"
                        },
                        "data": JSON.stringify({
                            "id": id,
                            "name": name,
                            "city": city,
                            "address": address,
                            "district": district,
                            "freeTables": plusfreeTables,
                            "maxTables": maxTables,
                            "lat": lat,
                            "lng": lng,
                            "homepage": homepage,
                            "phone": phone,
                            "username": username
                        }),
                    };

                    $.ajax(settings).done(function (response) {
                        console.log(response);
                    });
                }
            }
        }
    }
    xhr.open("GET", baseApiUrl + 'restaurants/user/' + urlParams.get('user'), false);
    xhr.send(null);

}


$(document).ready(function () {
    loadRest();


});