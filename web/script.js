(function (window, mapster) {

    // map options
    var options = mapster.MAP_OPTIONS,
        element = document.getElementById('map-canvas'),
        // map
        map = mapster.create(element, options);

    var restList = [];

    $.getJSON("http://localhost:8080/api/restaurants/", function (data) {
        restList = JSON.parse(JSON.stringify(data));
        for (var i = 0; i < restList.length; i++) {
            const restaraunt = restList[i];
            if (restaraunt.freeTables !== 0) {
                var markerText = '<p><b>' + restaraunt.name + '</b></p>' +
                    '<p>Has ' + restaraunt.freeTables + ' available tables!</p>' +
                    '<p>Address: ' + restaraunt.address + ", " + restaraunt.city + '</p>';
                if (restaraunt.phone !== null)
                    markerText += '<p>' + restaraunt.phone + "</p>";
                if (restaraunt.homepage !== null)
                    markerText += '<p>' + restaraunt.homepage + '</p>';

                map.addMarker({
                    lat: restList[i].lat,
                    lng: restList[i].lng,
                    id: restList[i].id,
                    title: restList[i].name,
                    icon: 'welcome.png',
                    content: markerText
                });
            }
        }

    });


}(window, window.Mapster));