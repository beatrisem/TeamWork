const baseApiUrl = 'http://localhost:8080/api/';
const timeout = 3000;

let id;
let name;
let city;
let address;
let district;
let freeTables;
let maxTables;
let lat;
let lng;
let homepage;
let phone;
let username;


function editField() {
    $('.editbtn').click(function () {
        var currentTD = $(this).parents('tr').find('td');
        if ($(this).text().trim() == 'Edit') {
            currentTD = $(this).parents('tr').find($("td").not(":nth-child(1)"));
            $.each(currentTD, function () {
                $(this).prop('contenteditable', true).css({
                    'background': '#fff',
                    'color': '#000'
                })
            });
        } else {
            $.each(currentTD, function () {
                $(this).prop('contenteditable', false).removeAttr("style");
            });
        }

        $(this).html($(this).html() == 'Edit' ? 'Save' : 'Edit')
        if ($(this).html() == 'Save') {
            $(this).prop('contenteditable', false)
            $('.editbtn').click(function () {
                let freeTablesSaved = document.getElementById("editfreetables").innerHTML;
                let maxTablesSaved = document.getElementById("editmaxtables").innerHTML;
                let homepageSaved = document.getElementById("edithomepage").innerHTML;
                let phoneSaved = document.getElementById("editphone").innerHTML;
                let nameSaved = document.getElementById("editname").innerHTML;
                let addressSaved = document.getElementById("editaddress").innerHTML;
                let citySaved = document.getElementById("editcity").innerHTML;


                let restaurant = {
                    id,
                    name: nameSaved,
                    city: citySaved,
                    address: addressSaved,
                    district,
                    freeTables: freeTablesSaved,
                    maxTables: maxTablesSaved,
                    lat,
                    lng,
                    homepage: homepageSaved,
                    phone: phoneSaved,
                    username,
                };

                var settings = {
                    "url": baseApiUrl + 'restaurants/' + id,
                    "method": "PUT",
                    "timeout": timeout,
                    "headers": {
                        "Content-Type": "application/json"
                    },
                    "data": JSON.stringify(restaurant),
                };

                $.ajax(settings).done(function (response) {
                    loadRest();
                });

            })
        }
    });
};


function loadRest() {
    var urlParams = new URLSearchParams(window.location.search);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                const parsedJson = JSON.parse(xhr.responseText);

                id = parsedJson.id;
                name = parsedJson.name;
                city = parsedJson.city;
                address = parsedJson.address;
                district = parsedJson.district;
                freeTables = parsedJson.freeTables;
                maxTables = parsedJson.maxTables;
                lat = parsedJson.lat;
                lng = parsedJson.lng;
                homepage = parsedJson.homepage;
                phone = parsedJson.phone;
                username = parsedJson.username;

                document.getElementById("name").innerHTML = name;
                document.getElementById("editname").innerHTML = name;
                document.getElementById("editaddress").innerHTML = address;
                document.getElementById("editcity").innerHTML = city;
                document.getElementById("edithomepage").innerHTML = homepage;
                document.getElementById("editphone").innerHTML = phone;
                document.getElementById("editfreetables").innerHTML = freeTables;
                document.getElementById("editmaxtables").innerHTML = maxTables;

            }
        }
    }
    xhr.open("GET", baseApiUrl + 'restaurants/user/' + urlParams.get('user'), false);
    xhr.send(null);
}

function editRestaurant() {

}


$(document).ready(function () {
    loadRest();
    editField();
});
