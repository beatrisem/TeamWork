(function (window, google, List) {

    var lastOpenedInfoWindow = null;

    var Mapster = (function () {
        function Mapster(element, opts) {
            this.gMap = new google.maps.Map(element, opts);
            this.markers = List.create();

        }

        Mapster.prototype = {

            addMarker: function (opts) {
                var infowindow = new google.maps.InfoWindow({
                    content: opts.content
                });

                var marker;
                opts.position = {
                    lat: opts.lat,
                    lng: opts.lng,
                }

                marker = this._createMarker(opts);
                marker.addListener('click', function () {
                    if (lastOpenedInfoWindow !== null){
                        lastOpenedInfoWindow.close();
                    }
                    infowindow.open(this.gMap, marker);
                    lastOpenedInfoWindow = infowindow;
                });
              this.markers.add(marker);
                return marker;
            },

            findBy: function(callback) {
               return  this.markers.find(callback);
            },

            _createMarker:
                function (opts) {
                    opts.map = this.gMap;
                    return new google.maps.Marker(opts);
                }
        } ;
        return Mapster;
    }());

    Mapster.create = function (element, opts) {
        return new Mapster(element, opts);
    };

    window.Mapster = Mapster;

}(window, google, List));