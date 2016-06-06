(function () {
    function loadScript() {
        if (arguments.length > 0) {
            var baseURL = "js/CebuAir/WebAccessibilitySSB/";
            for (var o in arguments) {
                if (arguments[o]["FolderName"] != undefined && arguments[o]["Scripts"] != undefined) {
                    for (var i = 0; i < arguments[o].Scripts.length; i++) {
                        var script = document.createElement("script");
                        script.type = "text/javascript";
                        script.src = baseURL + arguments[o].FolderName + "/" + arguments[o].Scripts[i];
                        document.getElementsByTagName("head")[0].appendChild(script);
                    }
                }
            }
        }
    }

    var cLaneScripts = {
        "FolderName": "CLane",
        "Scripts": [
            "27088.js"
            , "27195.js"
            , "27303.js"
            , "27487.js"
            , "27524.js"
            , "27525.js"
            , "27526.js"
            , "27527.js"
            , "27528.js"
            , "27545.js"
            , "27553.js"
            , "27554.js"
            , "27556.js"
            , "27557.js"
            , "27558.js"
        ]
    };

    var hJungelScripts = {
        "FolderName": "HJungel",
        "Scripts": [
            "alternativeText.js"
            , "boardingpassformlabels.js"
            , "colorContrast_css_script.js"
            , "guestformlabels.js"
            , "hotelavailability-roomInfo-span.js"
            , "img-payment.js"
            , "list-indentation.js"
            , "mealplannerformlabels.js"
            , "member_alternativeText.js"
            , "member_booking_taborder_table.js"
            , "member_formLabels.js"
            , "member_linktext.js"
            , "navdiv.js"
            , "paymentformlabels.js"
            , "skipnav.js"
            , "hotelAvailability-links.js"
            , "monthlyLinkContainer-arrows.js"
            , "mealplanner_dialog.js"
            , "member_errormessages.js"
        ]
    };

    loadScript(cLaneScripts, hJungelScripts); //FolderName|Scripts
})();