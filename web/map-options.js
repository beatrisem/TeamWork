(function (window, google, mapster) {

    mapster.MAP_OPTIONS = {
        center: {
            lat: 56.95149,
            lng: 24.1133
        },
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

}(window, google, window.Mapster || (window.Mapster = {})));